package com.hayavadana.ratingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ActivityPopupReviewEditComments extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_activity_review_edit_comments);
        Button btnClear = (Button)findViewById(R.id.btnClear);
        Button btnOK = (Button)findViewById(R.id.btnOK);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        EditText etEditComments = (EditText)findViewById(R.id.etEditComments);

        Bundle tempextras = getIntent().getExtras();
        if (tempextras != null) {
            String commentsEntered = tempextras.getString("commentsEntered");
            if (commentsEntered.length() > 0) {
                EditText etComments = (EditText) findViewById(R.id.etEditComments);
                etComments.setText(commentsEntered);

            }
        }
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etEditComments = (EditText)findViewById(R.id.etEditComments);
                etEditComments.setText("");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                EditText etEditComments = (EditText)findViewById(R.id.etEditComments);
                Bundle aBundle = new Bundle();
                aBundle.putString("userComments",(String)etEditComments.getText().toString());
                returnIntent.putExtras(aBundle);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popup_activity_review_edit_comments, menu);
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
