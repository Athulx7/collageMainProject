package com.syntax.instantfuel.COMMON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syntax.instantfuel.R;

import java.util.List;

public class RequestAdapterUser extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public RequestAdapterUser(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.user_reg_view, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.user_reg_view, null, true);


        TextView user_name = (TextView) view.findViewById(R.id.userName);
        TextView user_address = (TextView) view.findViewById(R.id.userAddress);
        TextView user_phone = (TextView) view.findViewById(R.id.userPhone);
        TextView user_email = (TextView) view.findViewById(R.id.userEmail);
        TextView user_doj = (TextView) view.findViewById(R.id.user_dateofrqst);
        TextView fuelRqstTo = (TextView) view.findViewById(R.id.fuelRqstTo);
        TextView fuelRqstPayment = (TextView) view.findViewById(R.id.fuelRqstPayment);
        TextView paidIcon = (TextView) view.findViewById(R.id.paidIcon);
        LinearLayout layoutUserRqst = (LinearLayout) view.findViewById(R.id.layoutUserRqst);


        user_name.setText(rest_List.get(position).getC_name());
        user_address.setText(rest_List.get(position).getC_address());
        user_phone.setText(rest_List.get(position).getC_phone());
        user_email.setText(rest_List.get(position).getC_email());
        user_doj.setText(rest_List.get(position).getC_doj());

        if (rest_List.get(position).getRqstStatus().trim().equals("REQUESTED")){
            layoutUserRqst.setVisibility(View.VISIBLE);
            fuelRqstPayment.setVisibility(View.GONE);
            fuelRqstTo.setText(rest_List.get(position).getFuelRqstd()+"ltr");
            user_doj.setText(rest_List.get(position).getRqst_time());
        }else if (rest_List.get(position).getRqstStatus().trim().equals("APPROVED")){
            layoutUserRqst.setVisibility(View.VISIBLE);
            fuelRqstPayment.setVisibility(View.VISIBLE);
            fuelRqstTo.setText(rest_List.get(position).getFuelRqstd()+"ltr");
            user_doj.setText(rest_List.get(position).getRqst_time());
        } else if (rest_List.get(position).getRqstStatus().trim().equals("PAID")){
            layoutUserRqst.setVisibility(View.VISIBLE);
            fuelRqstPayment.setVisibility(View.VISIBLE);
            fuelRqstTo.setText(rest_List.get(position).getFuelRqstd()+"ltr");
            user_doj.setText(rest_List.get(position).getRqst_time());
            fuelRqstPayment.setText("SUCCESSFULLY "+rest_List.get(position).getRqstStatus());
            paidIcon.setVisibility(View.VISIBLE);
            fuelRqstPayment.setTextColor(Color.parseColor("#020169"));
        }else{
            layoutUserRqst.setVisibility(View.GONE);
            fuelRqstPayment.setVisibility(View.GONE);
            paidIcon.setVisibility(View.GONE);
        }


        return view;
    }
}
