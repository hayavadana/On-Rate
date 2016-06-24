package com.hayavadana.ratingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivityAllReviewsOfMovies extends AppCompatActivity {


    ListView reviewsListMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews_of_movies);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        setTitle(QueryInputs.selectedDetailedMovieEntity.getMovieName());
        ReviewCommentListItemAdapter reviewCommentListItemAdapter = new
                ReviewCommentListItemAdapter(ActivityAllReviewsOfMovies.this,QueryInputs.selectedDetailedMovieEntity.getMovieReviewComments());
        reviewsListMovies=(ListView)findViewById(R.id.listViewAllMovieReviews);
        reviewsListMovies.setAdapter(reviewCommentListItemAdapter);
        reviewsListMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Bundle mBundle = new Bundle();
                mBundle.putInt("REVIEW_INDEX", position);
                Intent myIntent = new Intent(ActivityAllReviewsOfMovies.this,ActivityAMovieReviewCommentInDetail.class);
                myIntent.putExtras(mBundle);
                startActivity(myIntent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_all_reviews_of_movies, menu);
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
