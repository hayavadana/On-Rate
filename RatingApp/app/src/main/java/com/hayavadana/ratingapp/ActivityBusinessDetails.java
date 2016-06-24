package com.hayavadana.ratingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityBusinessDetails extends AppCompatActivity {

    final Context context = this;
    JSONObject submitRatingObj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        //Set color to rating bar
//        RatingBar ratingObj = (RatingBar)findViewById(R.id.ratingBarUserRating);
//        Drawable drawable = ratingObj.getProgressDrawable();
//        drawable.setColorFilter(Color.parseColor("#FFFDEC00"), PorterDuff.Mode.SRC_ATOP);


        populateBusinessDetails();

        setListnerForCapturingEmailId();

        setListnerToListReviewComments();

        ImageView imgEnterRevCmnts = (ImageView)findViewById(R.id.imgWriteComments) ;
        imgEnterRevCmnts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actInt = new Intent(ActivityBusinessDetails.this, ActivityPopupReviewEditComments.class);
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

            String android_id = Settings.Secure.getString(ActivityBusinessDetails.this.getContentResolver(), Secure.ANDROID_ID);

            Log.d("Android","Sreeni Android ID : "+android_id);

            //sample data
            try {
                RatingBar ratingObj = (RatingBar)findViewById(R.id.ratingBarUserRating);
                float usrRating = ratingObj.getRating();
                //submitRatingObj.put("emailId","rateonapp@gmail.com");
                submitRatingObj.put("phoneNumber", "000000");
                submitRatingObj.put("userRate",usrRating);

                submitRatingObj.put("deviceInfo", android_id);
                submitRatingObj.put("businessId", Integer.toString(QueryInputs.selectedDetailedBusienessEntity.getBusinessId()));
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Exception while preparing the JSON object for submit rating");
            }

            rInvoker.setPostRequestParams(submitRatingObj.toString());
            rInvoker.setCurActivity(ActivityBusinessDetails.this);
            rInvoker.setNextActivity(com.hayavadana.ratingapp.CaptureUserInputs.class);
            rInvoker.setCurrentContext(ActivityBusinessDetails.this);
            String completeSvcSubmitRating = svcUrlStr+"/rest/userRating/send/";
            System.out.println("Sreeni : completeSvcSubmitRating is " + completeSvcSubmitRating);
            rInvoker.setURL(completeSvcSubmitRating);
            rInvoker.execute();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_business_details, menu);
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
    void populateBusinessDetails(){
        String tempBusinessTitle = "Das Palla Restaurant";
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


        TextView businessTitle = (TextView) findViewById(R.id.TextViewBusinessTitle);
        businessTitle.setText(QueryInputs.selectedDetailedBusienessEntity.getBusinessName());
        WebView webviewBusinessDesc = (WebView)this.findViewById(R.id.webViewBusinessDesc);
        String businessDesc = QueryInputs.selectedDetailedBusienessEntity.getBusinessDescription();
        System.out.println("sreeni: businessDesc1 " + businessDesc);
        webviewBusinessDesc.loadData(businessDesc, "text/html", "UTF-8");

        RatingBar busRatingObj = (RatingBar)findViewById(R.id.ratingBarBusinessRating);
        String avgRatingStr = QueryInputs.selectedDetailedBusienessEntity.getAverageRating();
        float ratingFloat;
        try
        {
            ratingFloat  = Float.valueOf(avgRatingStr).floatValue();
            busRatingObj.setRating(ratingFloat);

        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        busRatingObj.setEnabled(false);

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

    private void setListnerForCapturingEmailId(){
        Button btnCaptureEmail = (Button)findViewById(R.id.btnEmail);
        btnCaptureEmail.setOnClickListener(new OnClickListener() {
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

    private void setListnerToListReviewComments(){


        ImageView imgView = (ImageView)findViewById(R.id.imgListRevComments);
        imgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RestServiceInvoker rInvoker = new RestServiceInvoker();
                WebServiceHost webSrvUrlObj = WebServiceHost.getInstance();
                String svcUrlStr = webSrvUrlObj.getWebserviceURL();
                rInvoker.setOperationType("GET_ALLREVIEWS");
                rInvoker.setHttpMethod("GET");
                rInvoker.setCurActivity(ActivityBusinessDetails.this);
                rInvoker.setCurrentContext(ActivityBusinessDetails.this);
                rInvoker.setNextActivity(ActAllReviews.class);
                String tempBusinessId = Integer.toString(QueryInputs.selectedDetailedBusienessEntity.getBusinessId());
                String completeSvcBusCatUrl = svcUrlStr+"/rest/userRating/getReviewComments/business/"+ tempBusinessId;
                rInvoker.setURL(completeSvcBusCatUrl);
                rInvoker.execute();
            }
        });

    }
}
