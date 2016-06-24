package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;


public class BusinessCategoryListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<BusinessCategoryEntity> businessCategoryList;

    public BusinessCategoryListAdapter(Activity context,
                               ArrayList<BusinessCategoryEntity> businessCategoryInfo) {
        super(context, R.layout.businesscategory_list_item,businessCategoryInfo);
        this.context = context;
        this.businessCategoryList = businessCategoryInfo;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("NAMPELLI SREENIVASU 000:");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.businesscategory_list_item, null, true);
        TextView txtBusinessCategoryName = (TextView) rowView.findViewById(R.id.business_category_name);

        JSONObject jsonChildNode = null;


        System.out.println("NAMPELLI SREENIVASU:");
        String tempstr = businessCategoryList.get(position).categoryDesc;
        System.out.println("NAMPELLI SREENIVASU:"+tempstr);

        txtBusinessCategoryName.setText(tempstr);
        return rowView;
    }
}
