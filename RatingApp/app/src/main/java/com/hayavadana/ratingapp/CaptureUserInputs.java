package com.hayavadana.ratingapp;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CaptureUserInputs extends ActRateOnCustomWindowTitle {

    final Context myContext = CaptureUserInputs.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_user_inputs);
        registerButtons();


        setButtonProperties();


        //Button btnProceed = (Button) findViewById(R.id.btnProceed);

        //btnProceed.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {

          //      Intent intentBusinessList = new Intent(CaptureUserInputs.this, com.hayavadana.ratingapp.ActivityBusinessList.class);
            //    startActivity(intentBusinessList);


        //    }
  //      });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capture_user_inputs, menu);
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



    public void registerButtons(){
        register(R.id.btnCountry);
        register(R.id.btnState);
        register(R.id.btnCity);
        register(R.id.btnArea);
        register(R.id.btnBusinessCategory);
        register(R.id.btnProceed);
        register(R.id.btnGo);
        //register(R.id.btnMovie);

    }

    private void setButtonProperties(){

        Button  btnCity = (Button) findViewById(R.id.btnCity);
        Button  btnArea = (Button) findViewById(R.id.btnArea);
        Button  btnBusinessCategory = (Button) findViewById(R.id.btnBusinessCategory);
        Button  btnProceed = (Button) findViewById(R.id.btnProceed);
        Button btnGo = (Button) findViewById(R.id.btnGo);


        if(QueryInputs.selectedState == null){
            btnCity.setEnabled(false);
            btnCity.setBackgroundResource(R.drawable.shape_disable_btn);


        }
        else {
            btnCity.setEnabled(true);
            btnCity.setBackgroundResource(R.drawable.shape);



        }

        if(QueryInputs.selectedCity == null){
            btnCity.setText(R.string.capture_input_select_city);
            btnArea.setEnabled(false);
            btnArea.setBackgroundResource(R.drawable.shape_disable_btn);

        }
        else {
            btnArea.setEnabled(true);
            btnArea.setBackgroundResource(R.drawable.shape);
        }

        if (QueryInputs.selectedArea == null) {
            btnArea.setText(R.string.capture_input_select_area);
        }

        if((QueryInputs.selectedCity == null)||(QueryInputs.selectedBusinessCategory == null)) {
            btnProceed.setEnabled(false);
            btnProceed.setBackgroundResource(R.drawable.shape_disable_btn);
        }
        else{
            btnProceed.setEnabled(true);
            btnProceed.setBackgroundResource(R.drawable.shape_for_proceed_btn);
        }

        }

    private void register(int buttonResourceId){
        findViewById(buttonResourceId).setOnClickListener(buttonClickListener);
    }

    private OnClickListener buttonClickListener = new OnClickListener() {

        @Override
        public void onClick(View v){
            RestServiceInvoker rInvoker = new RestServiceInvoker();
            WebServiceHost webSrvUrlObj = WebServiceHost.getInstance();
            String svcUrlStr = webSrvUrlObj.getWebserviceURL();

            switch (v.getId()) {
                case R.id.btnCountry:
                    // TODO
                    break;
                case R.id.btnState:
                    rInvoker.setOperationType("GET_STATES");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(CaptureUserInputs.this);

                    rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityStatesList.class);
                   // rInvoker.execute("http://192.168.1.12:8080/");
                    String completeSvcStateUrl = svcUrlStr+"/rest/masterServices/stateList/country/ind";
                    System.out.println("Sreeni : completeSvcStateUrl is "+completeSvcStateUrl);
                    rInvoker.setURL(completeSvcStateUrl);
                    rInvoker.execute();

                    //Intent intentState = new Intent(CaptureUserInputs.this, com.hayavadana.ratingapp.ActivityStatesList.class);
                    //startActivity(intentState);
                    break;
                case R.id.btnCity:
                    rInvoker.setOperationType("GET_CITIES");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(myContext);
                    rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityCityList.class);
                    String completeSvcCityUrl = svcUrlStr+"/rest/masterServices/cityList/country/ind/state/"+QueryInputs.selectedState.stateCode;
                    System.out.println("Sreeni : completeSvcCityUrl is " + completeSvcCityUrl);
                    rInvoker.setURL(completeSvcCityUrl);
                    rInvoker.execute();

                    //Intent intentCity = new Intent(CaptureUserInputs.this, com.hayavadana.ratingapp.ActivityCityList.class);
                    //startActivity(intentCity);
                    break;
                case R.id.btnArea:
                    rInvoker.setOperationType("GET_AREAS");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(myContext);
                    rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityAreaList.class);
                    String completeSvcAreasUrl = svcUrlStr+"/rest/masterServices/areaList/city/"+QueryInputs.selectedCity.cityCode;
                    System.out.println("Sreeni : completeSvcAreasUrl is " + completeSvcAreasUrl);
                    rInvoker.setURL(completeSvcAreasUrl);
                    rInvoker.execute();

                    break;
                case R.id.btnBusinessCategory:
                    rInvoker.setOperationType("GET_BUSINESSCATEGORIES");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(myContext);
                    rInvoker.setNextActivity(com.hayavadana.ratingapp.BusinessCategoryAct.class);
                    String completeSvcBusCatUrl = svcUrlStr+"/rest/masterServices/categoriesList";
                    //System.out.println("Sreeni : completeSvcBusCatUrl is " + completeSvcBusCatUrl);
                    rInvoker.setURL(completeSvcBusCatUrl);
                    rInvoker.execute();
                    break;

                case R.id.btnProceed:
                    rInvoker.setOperationType("GET_BUSINESSLIST");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(myContext);
                    rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityBusinessList.class);
                   // http://localhost:8080/Rating/rest/businessService/businessList/country/IND/state/TS/city/HYD/category/CAT-1
                    String completeSvcBusListUrl = "";
                    if ((QueryInputs.selectedArea !=null) && (QueryInputs.selectedArea.areaDesc.length() > 0)) {
                        completeSvcBusListUrl = svcUrlStr + "/rest/businessService/businessList" + "/category/" + QueryInputs.selectedBusinessCategory.categoryCode + "/country/IND/" + "state/" + QueryInputs.selectedState.stateCode +
                                "/city/" + QueryInputs.selectedCity.cityCode + "/area/" + QueryInputs.selectedArea.areaCode;
                    }
                    else {
                        completeSvcBusListUrl = svcUrlStr + "/rest/businessService/businessList" + "/category/" + QueryInputs.selectedBusinessCategory.categoryCode + "/country/IND/" + "state/" + QueryInputs.selectedState.stateCode +
                                "/city/" + QueryInputs.selectedCity.cityCode ;

                    }

                    //Using the below hard coded service params until its ready from server side
                    //String completeSvcBusListUrl = svcUrlStr+"/Rating/rest/businessService/businessList/country/IND/state/TS/city/HYD/category/Hospital";
                    System.out.println("Sreeni : completeSvcBusListUrl is " + completeSvcBusListUrl);
                    rInvoker.setURL(completeSvcBusListUrl);
                    rInvoker.execute();
                    break;
                case R.id.btnGo:
                    rInvoker.setOperationType("GET_MOVIELIST");
                    rInvoker.setHttpMethod("GET");
                    rInvoker.setCurActivity(CaptureUserInputs.this);
                    rInvoker.setCurrentContext(CaptureUserInputs.this);

                    rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityMovieList.class);
                    // rInvoker.execute("http://192.168.1.12:8080/");

                    final Spinner spinner = (Spinner) findViewById(R.id.mSpinner);
                    String selLang = spinner.getSelectedItem().toString();
                    QueryInputs.selectedMovieCategory = new MovieCategoryEntity();
                    QueryInputs.selectedMovieCategory.langcategory = selLang;

                    Toast.makeText(CaptureUserInputs.this, "Spinner item " + selLang, Toast.LENGTH_SHORT).show();

                    String completeSvcMovieUrl = svcUrlStr+"/rest/movieService/movieList/language/" +selLang;
                    System.out.println("Sreeni : completeSvcMovieUrl is "+completeSvcMovieUrl);
                    rInvoker.setURL(completeSvcMovieUrl);
                    rInvoker.execute();

                    //Intent intentState = new Intent(CaptureUserInputs.this, com.hayavadana.ratingapp.ActivityStatesList.class);
                    //startActivity(intentState);
                    break;

            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        if(QueryInputs.selectedState != null) {
            Button btnStateObj = (Button) findViewById(R.id.btnState);
            btnStateObj.setText(QueryInputs.selectedState.stateDesc);
        }
        if(QueryInputs.selectedCity != null) {
            Button btnCityObj = (Button) findViewById(R.id.btnCity);
            btnCityObj.setText(QueryInputs.selectedCity.cityDesc);
        }
        if(QueryInputs.selectedArea != null) {
            Button btnAreaObj = (Button) findViewById(R.id.btnArea);
            btnAreaObj.setText(QueryInputs.selectedArea.areaDesc);
        }
        if(QueryInputs.selectedBusinessCategory != null) {
            Button btnBusCatObj = (Button) findViewById(R.id.btnBusinessCategory);
            btnBusCatObj.setText(QueryInputs.selectedBusinessCategory.categoryDesc);
        }
        setButtonProperties();
    }

}
