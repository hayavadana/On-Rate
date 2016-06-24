package com.hayavadana.ratingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class ActivityStatesList extends AppCompatActivity {

    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_list);

        StateListItemAdapter stateListItemAdapter = new
                StateListItemAdapter(ActivityStatesList.this, QueryCache.getStateEntityArrayList());
        list=(ListView)findViewById(R.id.statesList);
        list.setAdapter(stateListItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(ActivityBusinessList.this, "You Clicked at " + businessNameStrings[position], Toast.LENGTH_SHORT).show();

                //intent1.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent1);

                //Clear other selections (city, area ) if different state is selected
                if(QueryInputs.selectedState != null){
                    if(!QueryInputs.selectedState.stateCode.equals(QueryCache.getStateEntityArrayList().get(position).stateCode)){
                        QueryInputs.selectedArea = null;
                        QueryInputs.selectedCity = null;
                    }
                }

                QueryInputs.selectedState = QueryCache.getStateEntityArrayList().get(position);

                finish();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_states_list, menu);
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
