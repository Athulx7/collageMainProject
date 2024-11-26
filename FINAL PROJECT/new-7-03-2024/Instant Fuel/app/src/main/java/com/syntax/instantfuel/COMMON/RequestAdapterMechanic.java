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

public class RequestAdapterMechanic extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public RequestAdapterMechanic(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.fuel_mech_view, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.fuel_mech_view, null, true);


        TextView mech_name = (TextView) view.findViewById(R.id.mech_NAME);
        TextView mech_phone = (TextView) view.findViewById(R.id.mech_PHONE);
        TextView mech_email = (TextView) view.findViewById(R.id.mech_EMAIL);
        TextView mech_STATUS = (TextView) view.findViewById(R.id.mech_STATUS);


        mech_name.setText(rest_List.get(position).getMech_name());
        mech_phone.setText(rest_List.get(position).getMech_phone());
        mech_email.setText(rest_List.get(position).getMech_email());
        mech_STATUS.setText("Adhar : "+rest_List.get(position).getAdhar());



        return view;
    }
}
