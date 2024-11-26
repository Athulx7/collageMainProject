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

public class RequestAdapterFeedback extends ArrayAdapter<RequestPojo> {
    Activity context;
    List<RequestPojo> rest_List;

    public RequestAdapterFeedback(Activity context, List<RequestPojo> rest_List) {
        super(context, R.layout.feedback_view_admin, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.feedback_view_admin, null, true);


        TextView user_name = (TextView) view.findViewById(R.id.Name);
        TextView user_type = (TextView) view.findViewById(R.id.user_type);
        TextView feedback = (TextView) view.findViewById(R.id.complaintA);
        TextView description = (TextView) view.findViewById(R.id.descriptionA);


        user_name.setText(rest_List.get(position).getName());
        user_type.setText(rest_List.get(position).getType());
        feedback.setText(rest_List.get(position).getSubject());
        description.setText(rest_List.get(position).getDescription());

        return view;
    }
}
