package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;


public class CityListItemAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<CityEntity> citiesList;

    public CityListItemAdapter(Activity context,
                                ArrayList<CityEntity> cityInfo) {
        super(context, R.layout.city_list_item,cityInfo);
        this.context = context;
        this.citiesList = cityInfo;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("NAMPELLI SREENIVASU 000:");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.city_list_item, null, true);
        TextView txtCityName = (TextView) rowView.findViewById(R.id.city_name);

        JSONObject jsonChildNode = null;


        System.out.println("NAMPELLI SREENIVASU:");
        String tempstr = citiesList.get(position).cityDesc;
        System.out.println("NAMPELLI SREENIVASU:"+tempstr);

        txtCityName.setText(tempstr);
        return rowView;
    }
}
