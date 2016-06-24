package com.hayavadana.ratingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieLangSelect extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_lang_select);
        final TextView textView = (TextView) findViewById(R.id.tvMovie);

        final Spinner spinner = (Spinner) findViewById(R.id.mSpinner);
        Button button = (Button) findViewById(R.id.btnGo);

        Toast.makeText(getApplicationContext(),"oncreate of MovieLangSelect",Toast.LENGTH_LONG).show();


        // Spinner Drop down elements
        List<String> LanguagesList = new ArrayList<String>();
        LanguagesList.add("Telugu");
        LanguagesList.add("Hindi");
        LanguagesList.add("English");


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LanguagesList);

       // arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Click Go",Toast.LENGTH_LONG).show();

                Intent intent= new Intent(MovieLangSelect.this,ActivityMovieList.class);
                intent.putExtra("data",String.valueOf(spinner.getSelectedItem()));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final Spinner spinner = (Spinner) findViewById(R.id.mSpinner);

        Intent intent= new Intent(MovieLangSelect.this,ActivityMovieList.class);
        intent.putExtra("data",String.valueOf(spinner.getSelectedItem()));
        startActivity(intent);

        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(),"Plz Select a language" ,Toast.LENGTH_LONG).show();

    }
}
