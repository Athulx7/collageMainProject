package com.syntax.instantfuel.USER.ui;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.instantfuel.COMMON.RequestPojo;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Pay2_frag extends Fragment {

    String amountval, user_id, count;

    public Pay2_frag() {
        // Required empty public constructor
    }

    //    String cnoo,cnamee,pin,yearr,cvvs,arr[];
////
    String CARDNO, CVVNO, PINVAL;
    String date1, cart_date, cart_id, pro_quantity, dt[], pro_id, from_date, to_date, ven_id, u_id, arr[], balance;
    String bID,uID;

    ImageView img;
    EditText card, name, cvv, pin, amount;
    Button pay, amt;
    RequestPojo requestPojo;
    String BAL,AMT, fuelRqstd, fuelPrice, reqID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pay2_frag, container, false);

        final Bundle b = this.getArguments();
        requestPojo = b.getParcelable("clicked_item");
        reqID = b.getString("reqID");
        fuelRqstd = b.getString("fuelRqstd");
        fuelPrice = b.getString("fuelPrice");

        double fuel = Double.parseDouble(fuelRqstd);
        double price = Double.parseDouble(fuelPrice);
        final double tot = fuel * price;

        SharedPreferences preferences = getContext().getSharedPreferences("bank_details", 0);
        bID = preferences.getString("b_id","No u_id");
        uID = preferences.getString("u_id","No u_id");


        img = root.findViewById(R.id.pay_img_logo_cart);
        card = root.findViewById(R.id.pay_edt_cardno_cart);
        name = root.findViewById(R.id.pay_edt_name_cart);
        cvv = root.findViewById(R.id.pay_edt_cvv_cart);
        pin = root.findViewById(R.id.pay_edt_pin_cart);
        amount = root.findViewById(R.id.pay_amount);
        pay = root.findViewById(R.id.btn_payment_cart);


        volly_account_check();


        Date apply_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date1 = sdf.format(apply_date);

//        user_id=requestPojo.getUser_id();
        amount.setText(tot+"");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String CARD = card.getText().toString().trim();
                final String CVV = cvv.getText().toString().trim();
                final String PIN = pin.getText().toString().trim();

                double bal=Double.parseDouble(balance);
                double amt=tot;

                 if (bal<amt) {
                     Toast.makeText(getContext(), "Your account balance less than "+amt+" Please update balance", Toast.LENGTH_SHORT).show();
                }
                else if (!CARD.equals(CARDNO)) {
                    card.requestFocus();
                    card.setError("Cardno not match");
                } else if (!CVV.equals(CVVNO)) {
                    cvv.requestFocus();
                    cvv.setError("CVV not match");
                } else if (!PIN.equals(PINVAL)) {
                    pin.requestFocus();
                    pin.setError("Pin not match");
                } else {
                     BAL=""+(bal-amt);
                     //Toast.makeText(getContext(), ""+BAL, Toast.LENGTH_SHORT).show();
                    volly_make_pay();
//                        volly_payment();


                }
            }
        });

        return root;
    }

    private void swapFragment() {

        Fragment fragment = new ApprovedAppointments();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void volly_make_pay() {

        final ProgressDialog pd;
        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setTitle("Loading...");
        //  pd.show();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
//                Toast.makeText(getApplicationContext(), "Cmon' " + response, Toast.LENGTH_SHORT).show();

                pd.dismiss();

                if (!response.trim().equals("failed")) {


//                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("reg_id", response.trim()).apply();

                    Toast.makeText(getContext(), "Successfully Paid", Toast.LENGTH_LONG).show();
                    swapFragment();


                } else {
                    Toast.makeText(getContext(), "Failed !", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences preferences = getContext().getSharedPreferences("SharedData", 0);
                u_id = preferences.getString("u_id", "No logid");

                map.put("key", "makepayment");
                map.put("user_id", u_id.trim());
                map.put("amt", amount.getText().toString().trim());
                map.put("req_Id", reqID.trim());
                map.put("b_id", bID);
                map.put("bal", BAL);

                return map;
            }
        };
        queue.add(request);
    }

    public void volly_account_check() {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
//                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    if (response.trim().matches("Add Your Account Details")) {
                        Toast.makeText(getContext(), "Add Your Account Details", Toast.LENGTH_SHORT).show();
                        BankDetailsFragment fragment = new BankDetailsFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.nav_host_fragment, fragment)
                                .commit();
                    } else {
                        arr = response.trim().split("#");
                        CARDNO = arr[1];
                        CVVNO = arr[2];
                        PINVAL = arr[3];
                        balance = arr[4];


                    }
                } else {
                    Toast.makeText(getContext(), "You Dont have Enough Balance", Toast.LENGTH_SHORT).show();
                    BankDetailsFragment fragment = new BankDetailsFragment();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment)
                            .commit();
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
                SharedPreferences preferences = getContext().getSharedPreferences("SharedData", 0);
                u_id = preferences.getString("u_id", "No logid");
                map.put("key", "accountCheck");
                map.put("uid", u_id);


                return map;
            }
        };
        queue.add(request);
    }
}

