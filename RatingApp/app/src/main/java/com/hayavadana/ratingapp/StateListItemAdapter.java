package com.hayavadana.ratingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;


public class StateListItemAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<StateEntity> statesList;

    public StateListItemAdapter(Activity context,
                                ArrayList<StateEntity> stateInfo) {
        super(context, R.layout.state_list_item,stateInfo);
        this.context = context;
        this.statesList = stateInfo;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        System.out.println("NAMPELLI SREENIVASU 000:");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.state_list_item, null, true);
        TextView txtStateName = (TextView) rowView.findViewById(R.id.state_name);

        JSONObject jsonChildNode = null;


        System.out.println("NAMPELLI SREENIVASU:");
        String tempstr = statesList.get(position).stateDesc;
        System.out.println("NAMPELLI SREENIVASU:"+tempstr);

        txtStateName.setText(tempstr);
        return rowView;
    }
}
