package com.syntax.instantfuel.COMMON;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syntax.instantfuel.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAdapterFuelStation extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public RequestAdapterFuelStation(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.view_fuel_station_adapter, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.view_fuel_station_adapter, null, true);


        CircleImageView CimageX = view.findViewById(R.id.CimageX);
        TextView UFuelAddress = (TextView) view.findViewById(R.id.UFuelAddress);
        TextView UFuelName = (TextView) view.findViewById(R.id.UFuelName);
        TextView UFuelOwnerName = (TextView) view.findViewById(R.id.UFuelOwnerName);
        TextView UFuelDst = (TextView) view.findViewById(R.id.UFuelDst);
        TextView UFuelPhone = (TextView) view.findViewById(R.id.UFuelPhone);
        TextView uFuelEmail = (TextView) view.findViewById(R.id.uFuelEmail);

//      Status Requested
        TextView UFuelrqsted = (TextView) view.findViewById(R.id.UFuelrqsted);
        TextView UFuelRqstStatus = (TextView) view.findViewById(R.id.UFuelRqstStatus);
        LinearLayout layoutRequested = (LinearLayout) view.findViewById(R.id.layoutRequested);

//      Status Approved
        TextView UFuelApvdRqst = (TextView) view.findViewById(R.id.UFuelApvdRqst);
        TextView UFuelTot = (TextView) view.findViewById(R.id.UFuelTot);
        LinearLayout layoutApproved = (LinearLayout) view.findViewById(R.id.layoutApproved);

//      Status Paid
        TextView paidIcon = (TextView) view.findViewById(R.id.paidIcon);

        UFuelAddress.setText(rest_List.get(position).getS_address());
        UFuelName.setText(rest_List.get(position).getS_name());
        UFuelOwnerName.setText("[ " + rest_List.get(position).getS_owner() + " ]");
        UFuelDst.setText(rest_List.get(position).getS_district());
        UFuelPhone.setText(rest_List.get(position).getS_phone());
        uFuelEmail.setText(rest_List.get(position).getS_email());

        if (rest_List.get(position).getRqstStatus().trim().equals("REQUESTED")) {
            layoutRequested.setVisibility(View.VISIBLE);
            UFuelrqsted.setText(rest_List.get(position).getFuelRqstd()+" ltr");
//            UFuelRqstStatus.setText(rest_List.get(position).getRqstStatus());
//            UFuelRqstStatus.setTextColor(Color.parseColor(""));
        } else if (rest_List.get(position).getRqstStatus().trim().equals("APPROVED")) {
            layoutRequested.setVisibility(View.GONE);
            layoutApproved.setVisibility(View.VISIBLE);
            UFuelApvdRqst.setText(rest_List.get(position).getFuelRqstd()+" ltr");
            int totPrice = Integer.parseInt(rest_List.get(position).getFuelRqstd()) * Integer.parseInt(rest_List.get(position).getStation_rate());
            UFuelTot.setText(rest_List.get(position).getStation_rate() +" x "+rest_List.get(position).getFuelRqstd()+" = ₹ "+totPrice);
        } else if (rest_List.get(position).getRqstStatus().trim().equals("PAID")) {
            layoutRequested.setVisibility(View.GONE);
            layoutApproved.setVisibility(View.VISIBLE);
            UFuelApvdRqst.setText(rest_List.get(position).getFuelRqstd()+" ltr");
            int totPrice = Integer.parseInt(rest_List.get(position).getFuelRqstd()) * Integer.parseInt(rest_List.get(position).getStation_rate());
            UFuelTot.setText(rest_List.get(position).getStation_rate() +" x "+rest_List.get(position).getFuelRqstd()+" = ₹ "+totPrice);
            paidIcon.setVisibility(View.VISIBLE);
        } else {
            layoutRequested.setVisibility(View.GONE);
            layoutApproved.setVisibility(View.GONE);
            paidIcon.setVisibility(View.GONE);
        }


        return view;
    }
}
