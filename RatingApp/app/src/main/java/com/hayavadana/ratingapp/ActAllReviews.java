package com.hayavadana.ratingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class ActAllReviews extends AppCompatActivity {

    ListView reviewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_act_all_reviews);

        //Pushing the temporary data into reviews.Remove later
//        ArrayList<ReviewComment> templist = new ArrayList<ReviewComment>();
//        ReviewComment aComment = new ReviewComment("Wonderful experience. I would love to refer this business.","4.5");
//        templist.add(0,aComment);
//        aComment = new ReviewComment("Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to serviceBad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service.Bad experience. No respect to customers. Takes long time to service","2.5");
//        templist.add(1,aComment);
//        QueryInputs.selectedDetailedBusienessEntity.setBusinessReviewComments(templist);

        setTitle(QueryInputs.selectedDetailedBusienessEntity.getBusinessName());
        ReviewCommentListItemAdapter reviewCommentListItemAdapter = new
                ReviewCommentListItemAdapter(ActAllReviews.this,QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments());
        reviewsList=(ListView)findViewById(R.id.listViewAllReviews);
        reviewsList.setAdapter(reviewCommentListItemAdapter);
        reviewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Bundle mBundle = new Bundle();
                mBundle.putInt("REVIEW_INDEX", position);
                Intent myIntent = new Intent(ActAllReviews.this,ActAReviewCommentInDetail.class);
                myIntent.putExtras(mBundle);
                startActivity(myIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_all_reviews, menu);
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
