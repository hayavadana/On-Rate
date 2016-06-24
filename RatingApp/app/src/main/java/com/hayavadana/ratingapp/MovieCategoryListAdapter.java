package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;


public class MovieCategoryListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<MovieCategoryEntity> movieCategoryList;

    public MovieCategoryListAdapter(Activity context,
                                       ArrayList<MovieCategoryEntity> movieCategoryInfo) {
        super(context, R.layout.moviecategory_list_item,movieCategoryInfo);
        this.context = context;
        this.movieCategoryList = movieCategoryInfo;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("NAMPELLI SREENIVASU 000:");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.moviecategory_list_item, null, true);
        TextView txtMovieCategoryName = (TextView) rowView.findViewById(R.id.movie_category_name);

        JSONObject jsonChildNode = null;


        System.out.println("NAMPELLI SREENIVASU:");
        String tempstr = movieCategoryList.get(position).langcategory;
        System.out.println("NAMPELLI SREENIVASU:"+tempstr);

        txtMovieCategoryName.setText(tempstr);
        return rowView;
    }
}
