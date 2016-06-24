package com.hayavadana.ratingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.AdapterView;

public class ActivityBusinessList extends AppCompatActivity {

    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        setTitle(QueryInputs.selectedBusinessCategory.categoryDesc);

         BusinessListItemAdapter businessListItemAdapter = new
                 BusinessListItemAdapter(ActivityBusinessList.this,QueryCache.getBusinessEntityArrayList());
        list=(ListView)findViewById(R.id.businesslist);
        list.setAdapter(businessListItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                QueryInputs.selectedBusinessEntity = QueryCache.getBusinessEntityArrayList().get(position);
                RestServiceInvoker rInvoker = new RestServiceInvoker();
                rInvoker.setOperationType("GET_SELECTEDBUSINESSDETAILS");
                rInvoker.setHttpMethod("GET");
                rInvoker.setCurActivity(ActivityBusinessList.this);
                rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityBusinessDetails.class);
                rInvoker.setCurrentContext(ActivityBusinessList.this);
                String svcUrlStr = WebServiceHost.getInstance().getWebserviceURL();
                String completeSvcBusinessDetailsUrl = svcUrlStr+"/rest/businessService/businessDetails/business/"+Integer.toString(QueryInputs.selectedBusinessEntity.getBusinessId());
                System.out.println("Sreeni : completeSvcBusinessDetailsUrl is " + completeSvcBusinessDetailsUrl);
                rInvoker.setURL(completeSvcBusinessDetailsUrl);
                rInvoker.execute();


            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_business_list, menu);
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
}
