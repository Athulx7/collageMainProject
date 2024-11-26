package com.syntax.instantfuel.USER.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.USER.User;
import com.syntax.instantfuel.Utility;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class Feedback extends Fragment {

    String SUB,DES,RATING="0";
    EditText sub,des;
    Button send;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.feedback_fragment, container, false);

        sub=(EditText)root.findViewById(R.id.userfeedback_subject);
        des=(EditText)root.findViewById(R.id.userfeedback_details);
        send=(Button)root.findViewById(R.id.userfeedback_btnsend);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SUB=sub.getText().toString().trim();
                DES=des.getText().toString().trim();


                if (SUB.isEmpty()) {
                    sub.requestFocus();
                    sub.setError("ENTER VALID SUBJECT");
                } else if (DES.isEmpty()) {
                    des.requestFocus();
                    des.setError("ENTER VALID DETAILS");
                } else{
                    addFeedBack(SUB,DES);
                }
            }
        });


        return root;
    }

    public void addFeedBack(final String ...arr)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    Toast.makeText(getContext(), "Feedback Added ..!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), User.class));

                }
                else
                {
                    Toast.makeText(getContext(), "eroor in register", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");
                final String type = prefs.getString("type", "No logid");//"No name defined" is the default value.
                map.put("key","addFeedBack");
                map.put("uid",uid);
                map.put("subject",arr[0]);
                map.put("description",arr[1]);
                map.put("type",type);



                return map;
            }
        };
        queue.add(request);
    }
}
