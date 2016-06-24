package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewCommentListItemAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<ReviewComment> reviewsList;

    public ReviewCommentListItemAdapter(Activity context,
                               ArrayList<ReviewComment> allReviews) {
        super(context, R.layout.reviewcomment_list_item,allReviews);
        this.context = context;
        this.reviewsList = allReviews;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.reviewcomment_list_item, null, true);
        TextView txtReviewComment = (TextView) rowView.findViewById(R.id.textViewAReviewComment);

        String tempstr = reviewsList.get(position).getCommentString();

        txtReviewComment.setText(tempstr);
        RatingBar busRatingObj = (RatingBar)rowView.findViewById(R.id.RatingBarARating);
        String avgRatingStr = reviewsList.get(position).getRating();
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


        return rowView;
    }
}
