package com.hayavadana.ratingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityMovieList extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/



        setTitle(QueryInputs.selectedMovieCategory.langcategory);

        MovieListItemAdapter movieListItemAdapter = new
                MovieListItemAdapter(ActivityMovieList.this,QueryCache.getMovieEntityArrayList());
        list=(ListView)findViewById(R.id.movieList);
        list.setAdapter(movieListItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                QueryInputs.selectedMovieEntity = QueryCache.getMovieEntityArrayList().get(position);
                RestServiceInvoker rInvoker = new RestServiceInvoker();
                rInvoker.setOperationType("GET_SELECTEDMOVIEDETAILS");
                rInvoker.setHttpMethod("GET");
                rInvoker.setCurActivity(ActivityMovieList.this);
                rInvoker.setNextActivity(com.hayavadana.ratingapp.ActivityMovieDetails.class);
                rInvoker.setCurrentContext(ActivityMovieList.this);
                String svcUrlStr = WebServiceHost.getInstance().getWebserviceURL();
                String completeSvcMovieDetailsUrl = svcUrlStr+"/rest/movieService/movieDetails/movie/"+Integer.toString(QueryInputs.selectedMovieEntity.getMovieId());
                System.out.println("Sreeni : completeSvcMovieDetailsUrl is " + completeSvcMovieDetailsUrl);
                rInvoker.setURL(completeSvcMovieDetailsUrl);
                rInvoker.execute();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_movies_list, menu);
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
