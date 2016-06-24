package com.hayavadana.ratingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ActivityCityList extends AppCompatActivity {

    ListView list;

    public final static String EXTRA_MESSAGE = "com.example.kh499.ActivityCityList.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        CityListItemAdapter cityListItemAdapter = new
                CityListItemAdapter(ActivityCityList.this, QueryCache.getCityEntityArrayList());
        list=(ListView)findViewById(R.id.citiesList);
        list.setAdapter(cityListItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Clear area if different city is selected
                if(QueryInputs.selectedCity != null){
                    if(QueryInputs.selectedCity.cityCode != QueryCache.getCityEntityArrayList().get(position).cityCode){
                        QueryInputs.selectedArea = null;
                        QueryInputs.selectedArea = null;
                    }
                }

                QueryInputs.selectedCity = QueryCache.getCityEntityArrayList().get(position);
                finish();


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_city_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(this,"aaaa",Toast.LENGTH_LONG).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
