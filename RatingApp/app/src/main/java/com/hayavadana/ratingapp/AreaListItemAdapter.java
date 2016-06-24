package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;


public class AreaListItemAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<AreaEntity> areasList;

    public AreaListItemAdapter(Activity context,
                               ArrayList<AreaEntity> areaInfo) {
        super(context, R.layout.area_list_item,areaInfo);
        this.context = context;
        this.areasList = areaInfo;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("NAMPELLI SREENIVASU 000:");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.area_list_item, null, true);
        TextView txtAreaName = (TextView) rowView.findViewById(R.id.area_name);

        JSONObject jsonChildNode = null;


        System.out.println("NAMPELLI SREENIVASU:");
        String tempstr = areasList.get(position).areaDesc;
        System.out.println("NAMPELLI SREENIVASU:"+tempstr);

        txtAreaName.setText(tempstr);
        return rowView;
    }
}
