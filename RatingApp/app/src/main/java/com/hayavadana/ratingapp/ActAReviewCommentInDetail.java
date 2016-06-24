package com.hayavadana.ratingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hayavadana.ratingapp.R;


public class ActAReviewCommentInDetail extends AppCompatActivity {

    int reviewCommentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_areview_comment_in_detail);

        Bundle tempextras = getIntent().getExtras();
        if (tempextras != null) {
               reviewCommentIndex = tempextras.getInt("REVIEW_INDEX");
                TextView tvComments = (TextView) findViewById(R.id.textViewAReviewComment);
            tvComments.setText(QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments().get(reviewCommentIndex).getCommentString());

        }

        Button nextButton = (Button)findViewById(R.id.btnNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewCommentIndex ++;
                if(reviewCommentIndex >= QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments().size()){
                    reviewCommentIndex = 0;
                }
                TextView tvComments = (TextView) findViewById(R.id.textViewAReviewComment);
                tvComments.setText(QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments().get(reviewCommentIndex).getCommentString());
            }
        });

        Button prevButton = (Button)findViewById(R.id.btnPrev);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewCommentIndex --;
                if(reviewCommentIndex < 0 ){
                    reviewCommentIndex = QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments().size()-1;
                }
                TextView tvComments = (TextView) findViewById(R.id.textViewAReviewComment);
                tvComments.setText(QueryInputs.selectedDetailedBusienessEntity.getBusinessReviewComments().get(reviewCommentIndex).getCommentString());
            }
        });

        Button closeButton = (Button)findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_areview_comment_in_detail, menu);
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
