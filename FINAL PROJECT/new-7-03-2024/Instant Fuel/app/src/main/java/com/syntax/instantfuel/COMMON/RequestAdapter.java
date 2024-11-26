package com.syntax.instantfuel.COMMON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.syntax.instantfuel.R;

import java.util.List;

public class RequestAdapter extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public RequestAdapter(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.fuel_station_request_view, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.fuel_station_request_view, null, true);


        TextView fuleStationName = (TextView) view.findViewById(R.id.fuleStationName);
        TextView fuelStationOwner = (TextView) view.findViewById(R.id.fuelStationOwner);
        TextView fuelStationAddress = (TextView) view.findViewById(R.id.fuelStationAddress);
        TextView fuelStationPhone = (TextView) view.findViewById(R.id.fuelStationPhone);
        TextView fuelStationEmail = (TextView) view.findViewById(R.id.fuelStationEmail);
        TextView fuelStationDistrict = (TextView) view.findViewById(R.id.fuelStationDistrict);
        TextView dateofrqst = (TextView) view.findViewById(R.id.dateofrqst);


        fuleStationName.setText(rest_List.get(position).getS_name());
        fuelStationOwner.setText(rest_List.get(position).getS_owner());
        fuelStationAddress.setText(rest_List.get(position).getS_address());
        fuelStationPhone.setText(rest_List.get(position).getS_phone());
        fuelStationEmail.setText(rest_List.get(position).getS_email());
        fuelStationDistrict.setText(rest_List.get(position).getS_district());
        dateofrqst.setText(rest_List.get(position).getS_doj());

        return view;
    }
}
