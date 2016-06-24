package com.hayavadana.ratingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MovieListItemAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<MovieEntity> moviesList;

    public MovieListItemAdapter(Activity context,
                                   ArrayList<MovieEntity> movieListParam) {
        super(context, R.layout.movie_list_item, movieListParam);
        this.context = context;
        this.moviesList = movieListParam;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.movie_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.movie_Name);
        TextView txtActorName = (TextView) rowView.findViewById(R.id.actor_Name);
        TextView txtActressName = (TextView) rowView.findViewById(R.id.actress_Name);
        txtTitle.setText(moviesList.get(position).getMovieName());
        txtActorName.setText(moviesList.get(position).getActorName());
        txtActressName.setText(moviesList.get(position).getActressName());

        RatingBar busRatingObj = (RatingBar)rowView.findViewById(R.id.RatingBarMovieRating);
        String avgRatingStr = moviesList.get(position).getAverageRating();
        float ratingFloat;
        try
        {
            ratingFloat  = Float.valueOf(avgRatingStr).floatValue();
            busRatingObj.setRating(ratingFloat);

        }
        catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        busRatingObj.setFocusable(false);
        busRatingObj.setClickable(false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_movie);
        String strBase64 = moviesList.get(position).getMovieLogoBase64();
        byte[] decodedString = Base64.decode(strBase64.getBytes(),Base64.DEFAULT);
        Bitmap bitmapObj = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(bitmapObj);
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

