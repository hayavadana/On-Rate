package com.hayavadana.ratingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RestServiceInvoker extends AsyncTask<String, Void, Void> {


    private String ContentFrmServer;
    private String ErrorMsg;
    private Activity curActivity;
    private java.lang.Class nextActivityClass;
    private ProgressDialog progress;
    private String operationType;
    private String jsonRespStr = "";
    private String svcURL;
    private String httpMethod;
    private String postRequestParams;
    private Context curContext;
    private boolean connIssue = false;


    public void setPostRequestParams(String postRequestParams) {
        this.postRequestParams = postRequestParams;
    }


    public void setCurrentContext(Context curCntxt) {
        this.curContext = curCntxt;
    }


    public void setHttpMethod(String meth) {
        httpMethod = meth;

    }

    public void setCurActivity(Activity curAct) {

        curActivity = curAct;
    }

    public void setNextActivity(Class nextActClass) {

        nextActivityClass = nextActClass;
    }

    public void setOperationType(String opType) {
        operationType = opType;

    }

    public void setURL(String urlStr) {
        svcURL = urlStr;

    }

    protected void onPreExecute() {

        progress = new ProgressDialog(curActivity);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();

    }

    // Call after onPreExecute method
    protected Void doInBackground(String... urls) {

        System.out.println("nampelli inside the do in background");

        System.out.println("inside background task");

        jsonRespStr = "";
        try {

            URL url = new URL(svcURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod);

            if (httpMethod == "POST") {
                System.out.println("before writing the json object string");
                conn.setRequestProperty("content-type", "application/json; charset=utf-8");
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postRequestParams);
                System.out.println("after writing the json object string");

            }

            //if (conn.getResponseCode() != 200) {
            //  throw new RuntimeException("Failed : HTTP error code : "
            //        + conn.getResponseCode());
            //}
            if (conn.getResponseCode() != 200) {
                System.out.println("Problem getting response from server..");
                connIssue = true;
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");

                while ((output = br.readLine()) != null) {
                    jsonRespStr += output;
                    System.out.println(output);
                }
                System.out.println("SREENI received json string is :" + jsonRespStr);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {
            //showDialog("Unable to connect to server");
            connIssue = true;
            e.printStackTrace();

        }

        return null;


    }

    protected void onPostExecute(Void unused) {
        // NOTE: You can call UI Element here.

        System.out.println("INSIDE ONPOST EXECUTE");
        progress.dismiss();

        if (connIssue) {
            showDialog("Connectivity Issue", "Problem getting response from server..");
            connIssue = false;
            return;
        }
        if (jsonRespStr != "") try {
            processResponse();
            Intent newIntent = new Intent(curActivity, nextActivityClass);
            curActivity.startActivity(newIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void processResponse() throws JSONException {
        System.out.println("Nampelli: Inside process Response");
        if (operationType == "GET_STATES") {
            System.out.println("Nampelli: Inside GET_STATES");

            try {

                JSONArray jsonRespStatesArray = new JSONArray(jsonRespStr);
                QueryCache.setStateInfo(jsonRespStatesArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_CITIES") {
            System.out.println("Nampelli: Inside GET_CITIES: " + jsonRespStr);

            try {
                JSONArray jsonRespCitiesArray = new JSONArray(jsonRespStr);
                QueryCache.setCityInfo(jsonRespCitiesArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_AREAS") {
            System.out.println("Nampelli: Inside GET_AREAS: " + jsonRespStr);

            try {
                JSONArray jsonRespAreasArray = new JSONArray(jsonRespStr);
                QueryCache.setAreasInfo(jsonRespAreasArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_BUSINESSCATEGORIES") {
            System.out.println("Nampelli: Inside GET_BUSINESSCATEGORIES: " + jsonRespStr);

            try {
                JSONArray jsonRespBusinessCategoryArray = new JSONArray(jsonRespStr);
                QueryCache.setBusinessCategoryInfo(jsonRespBusinessCategoryArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_BUSINESSLIST") {
            System.out.println("Nampelli: Inside GET_BUSINESSLIST: " + jsonRespStr);

            try {

                JSONArray jsonRespBusinessListyArray = new JSONArray(jsonRespStr);
                QueryCache.setBusinessEntityList(jsonRespBusinessListyArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_SELECTEDBUSINESSDETAILS") {
            System.out.println("Nampelli: Inside GET_SELECTEDBUSINESSDETAILS: " + jsonRespStr);

            try {

                JSONObject jsonRespBusinessDetails = new JSONObject(jsonRespStr);
                QueryInputs.selectedDetailedBusienessEntity = new DetailedBusinessEntity();

                int businessId = (int) Integer.parseInt(jsonRespBusinessDetails.optString("businessId"));
                QueryInputs.selectedDetailedBusienessEntity.setBusinessId(businessId);

                QueryInputs.selectedDetailedBusienessEntity.setBusinessName(jsonRespBusinessDetails.optString("businessName").toString());

                QueryInputs.selectedDetailedBusienessEntity.setBusinessAddress(jsonRespBusinessDetails.optString("businessAddress").toString());

                QueryInputs.selectedDetailedBusienessEntity.setBusinessLogoUrl(jsonRespBusinessDetails.optString("buinessLogoUrl").toString());

                QueryInputs.selectedDetailedBusienessEntity.setLatitude(jsonRespBusinessDetails.optString("latitude").toString());

                QueryInputs.selectedDetailedBusienessEntity.setLongitude(jsonRespBusinessDetails.optString("longitude").toString());

                QueryInputs.selectedDetailedBusienessEntity.setBusinessDescription(jsonRespBusinessDetails.optString("businessDesc").toString());

                QueryInputs.selectedDetailedBusienessEntity.setAverageRating(jsonRespBusinessDetails.optString("averageRating").toString());
                String businessDesc = QueryInputs.selectedDetailedBusienessEntity.getBusinessDescription();

                System.out.println("sreeni:businessDesc " + businessDesc);


            } catch (Exception e) {
                System.out.println("Nampelli: Inside exception");

                e.printStackTrace();

            }
        }
        else if (operationType == "GET_MOVIECATEGORIES") {
            System.out.println("Nampelli: Inside GET_MOVIECATEGORIES: " + jsonRespStr);

            try {
                JSONArray jsonRespMovieCategoryArray = new JSONArray(jsonRespStr);
                QueryCache.setMovieCategoryInfo(jsonRespMovieCategoryArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        }
        else if (operationType == "GET_MOVIELIST") {
            System.out.println("Nampelli: Inside GET_MOVIELIST: " + jsonRespStr);

            try {

                JSONArray jsonRespMovieListArray = new JSONArray(jsonRespStr);
                QueryCache.setMovieEntityList(jsonRespMovieListArray);
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else if (operationType == "GET_SELECTEDMOVIEDETAILS") {
            System.out.println("Nampelli: Inside GET_SELECTEDMOVIEDETAILS: " + jsonRespStr);

            try {

                JSONObject jsonRespMovieDetails = new JSONObject(jsonRespStr);
                QueryInputs.selectedDetailedMovieEntity = new DetailedMovieEntity();

                /*int movieId = (int) Integer.parseInt(jsonRespMovieDetails.optString("movieId"));
                QueryInputs.selectedDetailedMovieEntity.setMovieId(movieId);*/

                QueryInputs.selectedDetailedMovieEntity.setMovieId(jsonRespMovieDetails.optInt("movie_id"));


                QueryInputs.selectedDetailedMovieEntity.setMovieName(jsonRespMovieDetails.optString("movieName").toString());

                QueryInputs.selectedDetailedMovieEntity.setActorName(jsonRespMovieDetails.optString("actor").toString());
                QueryInputs.selectedDetailedMovieEntity.setMovieLogoBase64(jsonRespMovieDetails.optString("poster").toString());


                // QueryInputs.selectedDetailedMovieEntity.setActressName(jsonRespMovieDetails.optString("actressName").toString());

               // QueryInputs.selectedDetailedMovieEntity.setMovieLogoUrl(jsonRespMovieDetails.optString("movieLogoUrl").toString());

                QueryInputs.selectedDetailedMovieEntity.setMovieDescription(jsonRespMovieDetails.optString("movieDesc").toString());

                QueryInputs.selectedDetailedMovieEntity.setAverageRating(jsonRespMovieDetails.optString("avgRating").toString());

                String movieDesc = QueryInputs.selectedDetailedMovieEntity.getMovieDescription();

                System.out.println("sreeni:movieDesc " + movieDesc);


            } catch (Exception e) {
                System.out.println("Nampelli: Inside exception");

                e.printStackTrace();

            }
        }else if (operationType == "GET_ALLREVIEWS"){

            JSONArray jsonArrAllReviews = new JSONArray(jsonRespStr);
            JSONObject jsonChildNode = null;
            ArrayList<ReviewComment> allReviewsList = new ArrayList<ReviewComment>();

            for (int ind=0;ind<jsonArrAllReviews.length();ind++){
                try {
                    jsonChildNode = jsonArrAllReviews.getJSONObject(ind);
                    String aCommentStr = jsonChildNode.optString("userComments").toString();
                    Double aRate = jsonChildNode.optDouble("userRate");
                    ReviewComment aComment = new ReviewComment(aCommentStr,aRate.toString());
                    allReviewsList.add(0,aComment);
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
            if (QueryInputs.selectedDetailedBusienessEntity != null){
                QueryInputs.selectedDetailedBusienessEntity.setBusinessReviewComments(allReviewsList);
            }

        }
        else if (operationType == "GET_ALLMOVIEREVIEWS") {

            JSONArray jsonArrAllMovieReviews = new JSONArray(jsonRespStr);
            JSONObject jsonChildNode = null;
            ArrayList<ReviewComment> reviewsListMovies = new ArrayList<ReviewComment>();

            for (int ind = 0; ind < jsonArrAllMovieReviews.length(); ind++) {
                try {
                    jsonChildNode = jsonArrAllMovieReviews.getJSONObject(ind);
                    String aCommentStr = jsonChildNode.optString("userComments").toString();
                    Double aRate = jsonChildNode.optDouble("userRate");
                    ReviewComment aComment = new ReviewComment(aCommentStr, aRate.toString());
                    reviewsListMovies.add(0, aComment);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (QueryInputs.selectedDetailedMovieEntity != null) {
                    QueryInputs.selectedDetailedMovieEntity.setMovieReviewComments(reviewsListMovies);
                }


            }
        }
        else if (operationType == "SUBMIT_RATING") {
            System.out.println("Nampelli: Inside SUBMIT_RATING: " + jsonRespStr);
            //showDialog("Success","Your rating has been Submitted");
            Toast.makeText(this.curContext,"Your rating has been Submitted",Toast.LENGTH_LONG).show();


        }
        jsonRespStr = "";
    }

    private void showDialog(String alertTitle, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(curContext).create();

        // Setting Dialog Title
        alertDialog.setTitle(alertTitle);

        // Setting Dialog Message
        alertDialog.setMessage(msg);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                dialog.dismiss();
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }
}