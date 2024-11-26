package com.syntax.instantfuel.USER.ui;

import static android.content.Context.MODE_PRIVATE;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.util.HashMap;
import java.util.Map;

public class BankDetailsFragment extends Fragment {

    EditText cardno, cardcvv, cardpin, cardbal;
    Button btn;
    String bankID,uID;
    String Scardnum, Scvv, Spin, Sbal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bank_details, container, false);

        cardno = root.findViewById(R.id.linkbank_cardno);
        cardcvv = root.findViewById(R.id.linkbank_cvv);
        cardpin = root.findViewById(R.id.linkbank_pin);
        cardbal = root.findViewById(R.id.linkbank_balance);
        btn = root.findViewById(R.id.linkbank_btnadd);

        getACcountDetails();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Scardnum = cardno.getText().toString().trim();
                Scvv = cardcvv.getText().toString().trim();
                Spin = cardpin.getText().toString().trim();
                Sbal = cardbal.getText().toString().trim();

//                Toast.makeText(getContext(), " " + Scardnum + " " + Scvv + " " + " " + Spin, Toast.LENGTH_SHORT).show();

                if ((cardno.getText().toString().isEmpty())) {

                    cardno.setError("Number");
                } else if ((cardcvv.getText().toString().isEmpty())) {

                    cardcvv.setError("CVV Please");
                } else if ((cardpin.getText().toString().isEmpty())) {

                    cardpin.setError("PIN required");
                } else if ((cardbal.getText().toString().isEmpty())) {

                    cardbal.setError("Enter Balance");
                } else {

                    //Toast.makeText(getContext(), "ividethi", Toast.LENGTH_SHORT).show();

                    addACcount();

                }


            }
        });


        return root;
    }

    public void addACcount() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******1", response);

                // Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();

                try {
                    if (!response.trim().equals("failed")) {

                        String data = response;

                        Toast.makeText(getContext(), "Account Added Successfully", Toast.LENGTH_LONG).show();


                        Fragment fragment = new BankDetailsFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.nav_host_fragment, fragment)
                                .commit();


                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");//"No name defined" is the default value.                map.put("key", "add_account");

                map.put("key", "newaccount");
                map.put("cardnum", Scardnum);
                map.put("cvv", Scvv);
                map.put("pin", Spin);
                map.put("balance", Sbal);
                map.put("uid", uid);


                return map;
            }
        };
        queue.add(request);
    }

    public void getACcountDetails() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                // Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();

                try {
                    if (!response.trim().equals("failed")) {

                        String data[] = response.trim().split("#");

                        bankID=data[0];
                        uID=data[1];

                        SharedPreferences preferences = getContext().getSharedPreferences("bank_details", 0);
                        SharedPreferences.Editor myEdit = preferences.edit();
                        myEdit.putString("b_id",bankID);
                        myEdit.putString("u_id",uID);
                        myEdit.commit();

                        cardno.setText(data[2]);
                        cardcvv.setText(data[3]);
                        cardpin.setText(data[4]);
                        cardbal.setText(data[5]);

                        cardno.setFocusableInTouchMode(false);
                        cardcvv.setFocusableInTouchMode(false);
                        cardpin.setFocusableInTouchMode(false);
                        btn.setText("Update Balance");

                    } else {
                        //Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                        cardno.setFocusableInTouchMode(true);
                        cardcvv.setFocusableInTouchMode(true);
                        cardpin.setFocusableInTouchMode(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String u_id = prefs.getString("u_id", "No logid");//"No name defined" is the default value.                map.put("key", "add_account");
                map.put("key", "getACcountDetails");
                map.put("uid", u_id);


                return map;
            }
        };
        queue.add(request);
    }


}