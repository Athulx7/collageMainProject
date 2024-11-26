package com.syntax.instantfuel.FUEL_STATION.ui;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syntax.instantfuel.COMMON.RequestPojo;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackMapUserRqst extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match

    private GoogleMap mMap;
    RequestPojo requestPojo;
    double sel_lati, sel_longi;
    String rqst_id, FUEL_PRICE;
    Dialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tack_map, container, false);


        final Bundle b = this.getArguments();
        requestPojo = b.getParcelable("clicked_item");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return root;
    }

//    ************


    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        sel_lati = Double.parseDouble(requestPojo.getRqst_lat());
        sel_longi = Double.parseDouble(requestPojo.getRqst_long());

        int height = 120;
        int width = 120;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.customer);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        mMap.addMarker(new MarkerOptions().position(new LatLng(sel_lati, sel_longi)).title(requestPojo.getC_name()).snippet(requestPojo.getC_phone()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(sel_lati, sel_longi)).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to Approve or Reject")
                        .setCancelable(false)
                        .setPositiveButton("Approve", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                rqst_id = requestPojo.getC_rqst_id();
                                showCustomDialog();


                            }
                        }).setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        rqst_id = requestPojo.getC_rqst_id();
                        deleteCustomerRqst();
                    }
                })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("FuelSpot");
                alert.show();


//                int position=Integer.parseInt(marker.getId().replaceAll("[m]",""))-1;
//                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected void showCustomDialog() {
        // TODO Auto-generated method stub
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fuel_station_fuel_price);


        final EditText subject = dialog.findViewById(R.id.subject);
        final Button submit_att = dialog.findViewById(R.id.submit_attendance);

        submit_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FUEL_PRICE = subject.getText().toString();

                if (FUEL_PRICE.isEmpty()) {
                    subject.requestFocus();
                    subject.setError("Enter the Fuel Price / Ltr you want");
                } else {
                    dialog.cancel();
                    approveRequest();
                }

            }
        });

        dialog.show();
    }


    public void deleteCustomerRqst() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                    Toast.makeText(getContext(), "Request Rejected", Toast.LENGTH_SHORT).show();
                    ViewAppointments fragment = new ViewAppointments();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, fragment);
                    transaction.commit();
                } else {
//                    nodata.setBackgroundResource(no_data_found);
                    Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() {
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "deleteUserAppointment");
                map.put("u_id", uid);
                map.put("rqst_id", rqst_id);

                return map;
            }
        };
        queue.add(request);
    }

    public void approveRequest() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                    Toast.makeText(getContext(), "Request Approved", Toast.LENGTH_SHORT).show();
                    ViewAppointments fragment = new ViewAppointments();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, fragment);
                    transaction.commit();

                } else {
//                    nodata.setBackgroundResource(no_data_found);
                    Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() {
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "approveUserAppointment");
                map.put("u_id", uid);
                map.put("rqst_id", rqst_id);
                map.put("fuel_price", FUEL_PRICE);

                return map;
            }
        };
        queue.add(request);
    }


}
