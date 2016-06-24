package com.hayavadana.ratingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMovieDetails extends AppCompatActivity {

    final Context context = this;
    JSONObject submitRatingObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        populateMovieDetails();

        //setListnerForCapturingEmailId();

        setListnerToListReviewComments();

        ImageView imgEnterRevCmnts = (ImageView)findViewById(R.id.imgWriteComments) ;
        imgEnterRevCmnts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actInt = new Intent(ActivityMovieDetails.this, ActivityPopupReviewEditComments.class);
                if (submitRatingObj != null){
                    try {
                        String tempComments = "";
                        tempComments = (String) submitRatingObj.get("userComments");
                        if (tempComments.length()>0){
                            Bundle mBundle = new Bundle();
                            mBundle.putString("commentsEntered", tempComments);
                            actInt.putExtras(mBundle);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                startActivityForResult(actInt,1);

            }
        });

        Button btnSubmitRating = (Button) findViewById(R.id.buttonSubmitRating);
        btnSubmitRating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RestServiceInvoker rInvoker = new RestServiceInvoker();
                WebServiceHost webSrvUrlObj = WebServiceHost.getInstance();
                String svcUrlStr = webSrvUrlObj.getWebserviceURL();
                rInvoker.setOperationType("SUBMIT_RATING");
                rInvoker.setHttpMethod("POST");

                if(submitRatingObj == null) {
                    submitRatingObj = new JSONObject();
                }

                String android_id = Settings.Secure.getString(ActivityMovieDetails.this.getContentResolver(), Settings.Secure.ANDROID_ID);

                Log.d("Android","Sreeni Android ID : "+android_id);

                //sample data
                try {
                    RatingBar ratingObj = (RatingBar)findViewById(R.id.ratingBarUserRatingMovie);
                    float usrRating = ratingObj.getRating();
                    //submitRatingObj.put("emailId","rateonapp@gmail.com");
                    submitRatingObj.put("phoneNumber", "000000");
                    submitRatingObj.put("userRate",usrRating);

                    submitRatingObj.put("deviceInfo", android_id);
                    submitRatingObj.put("movieId", Integer.toString(QueryInputs.selectedDetailedMovieEntity.getMovieId()));
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Exception while preparing the JSON object for submit rating");
                }

                rInvoker.setPostRequestParams(submitRatingObj.toString());
                rInvoker.setCurActivity(ActivityMovieDetails.this);
                rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityMovieDetails.class);
                rInvoker.setCurrentContext(ActivityMovieDetails.this);
                String completeSvcSubmitRating = svcUrlStr+"/rest/userRating/movie/send/";
                System.out.println("Sreeni : completeSvcSubmitRating is " + completeSvcSubmitRating);
                rInvoker.setURL(completeSvcSubmitRating);
                rInvoker.execute();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void populateMovieDetails(){
        /*String tempBusinessTitle = "";
        String businessDetailsString = "<html>\n" +
                "<body>\n" +
                "\t  \t<font face=\"comic sans ms\" size=\"5\"><font color=\"red\" size=\"8\"><b><i>Daspalla</font></b></i>, a Four Star Hotel in the heart of <font color=\"blue\" size=\"8\"><b>Hitech City</font></b>, better known as the Silicon Valley of India. <br>Built\n" +
                " to mark a renaissance in standards of service and style, the 110 \n" +
                "exquisitely-furnished guest rooms and suites are the finest in \n" +
                "hospitality. Daspalla Hotel serves as a great business hotel for the \n" +
                "discerning business traveler. The hotel is also equipped with \n" +
                "state-of-the art business and conference facilities as well as a \n" +
                "selection of refined cuisine options.&nbsp; </font>\t  \n" +
                "</body>\n" +
                "</html>";
*/

        TextView movieTitle = (TextView) findViewById(R.id.TextViewMovieTitle);
        movieTitle.setText(QueryInputs.selectedDetailedMovieEntity.getMovieName());

        ImageView imgView = (ImageView) findViewById(R.id.img_movie);
       String strBase64 = QueryInputs.selectedDetailedMovieEntity.getMovieLogoBase64();
        byte[] decodedString = Base64.decode(strBase64.getBytes(),Base64.DEFAULT);
       Bitmap bitmapObj = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
       imgView.setImageBitmap(bitmapObj);
        WebView webviewMovieDesc = (WebView)this.findViewById(R.id.webViewMovieDesc);
        String movieDesc = QueryInputs.selectedDetailedMovieEntity.getMovieDescription();
        System.out.println("sreeni: movieDesc1 " + movieDesc);
        webviewMovieDesc.loadData(movieDesc, "text/html", "UTF-8");

        RatingBar movRatingObj = (RatingBar)findViewById(R.id.ratingBarMovieRating);
        String avgRatingStr = QueryInputs.selectedDetailedMovieEntity.getAverageRating();
        float ratingFloat;
        try
        {
            ratingFloat  = Float.valueOf(avgRatingStr).floatValue();
            movRatingObj.setRating(ratingFloat);

        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        movRatingObj.setEnabled(false);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle tempBundle = data.getExtras();
                if (tempBundle != null) {
                    String result = tempBundle.getString("userComments");
                    if (submitRatingObj == null) {
                        submitRatingObj = new JSONObject();
                    }
                    try {
                        submitRatingObj.put("userComments", result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

  /*  private void setListnerForCapturingEmailId(){
        Button btnCaptureEmail = (Button)findViewById(R.id.btnEmail);
        btnCaptureEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.capture_emailid, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.etEmailId);

                //If the email id is already set, set it to the text box in the dialog
                if (submitRatingObj != null){

                    if (submitRatingObj.optString("emailId").toString().length()>0) {
                        String tempEmailId = submitRatingObj.optString("emailId").toString();
                        userInput.setText(tempEmailId);
                    }

                }

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to email id of the json object to be sent to submit rating
                                        if (submitRatingObj == null) {
                                            submitRatingObj = new JSONObject();
                                        }
                                        try {
                                            submitRatingObj.put("emailId", userInput.getText());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

    }
*/
    private void setListnerToListReviewComments(){


        ImageView imgView = (ImageView)findViewById(R.id.imgListRevComments);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestServiceInvoker rInvoker = new RestServiceInvoker();
                WebServiceHost webSrvUrlObj = WebServiceHost.getInstance();
                String svcUrlStr = webSrvUrlObj.getWebserviceURL();
                rInvoker.setOperationType("GET_ALLMOVIEREVIEWS");
                rInvoker.setHttpMethod("GET");
                rInvoker.setCurActivity(ActivityMovieDetails.this);
                rInvoker.setCurrentContext(ActivityMovieDetails.this);
                rInvoker.setNextActivity(ActivityAllReviewsOfMovies.class);
                String tempMovieId = Integer.toString(QueryInputs.selectedDetailedMovieEntity.getMovieId());
                String completeSvcBusCatUrl = svcUrlStr+"/rest/userRating/getReviewComments/movie/"+ tempMovieId;
                System.out.println("inside Review comments" + completeSvcBusCatUrl);
                rInvoker.setURL(completeSvcBusCatUrl);
                rInvoker.execute();
            }
        });

    }




}
