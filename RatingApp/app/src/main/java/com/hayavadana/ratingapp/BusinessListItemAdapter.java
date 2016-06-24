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

public class BusinessListItemAdapter extends ArrayAdapter{

    private final Activity context;
    private final ArrayList<BusinessEntity> businessesList;

    public BusinessListItemAdapter(Activity context,
                                   ArrayList<BusinessEntity> businessesListParam) {
        super(context, R.layout.business_list_item, businessesListParam);
        this.context = context;
        this.businessesList = businessesListParam;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.business_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.business_name);
        TextView txtBusinessAddress = (TextView) rowView.findViewById(R.id.business_address);
        txtTitle.setText(businessesList.get(position).getBusinessName());
        txtBusinessAddress.setText(businessesList.get(position).getBusinessAddress());

        RatingBar busRatingObj = (RatingBar)rowView.findViewById(R.id.RatingBarBusinessRating);
        String avgRatingStr = businessesList.get(position).getAverageRating();
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

        ImageView imageView = (ImageView) rowView.findViewById(R.id.business_icon);
        String strBase64 = businessesList.get(position).getBusinessLogoBase64();
        byte[] decodedString = Base64.decode(strBase64.getBytes(),Base64.DEFAULT);
        Bitmap bitmapObj = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(bitmapObj);
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}