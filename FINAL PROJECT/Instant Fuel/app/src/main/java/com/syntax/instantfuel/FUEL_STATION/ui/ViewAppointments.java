package com.syntax.instantfuel.FUEL_STATION.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syntax.instantfuel.COMMON.RequestAdapterUser;
import com.syntax.instantfuel.COMMON.RequestPojo;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ViewAppointments extends Fragment {

    ListView listView;
    ImageView nodata;
    List<RequestPojo> requestPojoList;
    String pID, rqst_id;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.view_appointments, container, false);

        listView = root.findViewById(R.id.seller_appointmentView);
        nodata = root.findViewById(R.id.noData);

        getAppointments();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to track customer Request")
                        .setCancelable(false)
                        .setPositiveButton("Track", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

//                                Log.d("#########", p_lat + "" + p_long);

                                Bundle bundle = new Bundle();
                                RequestPojo requestPojo = requestPojoList.get(position);
                                bundle.putParcelable("clicked_item", requestPojo);
                                TrackMapUserRqst product = new TrackMapUserRqst();
                                product.setArguments(bundle);
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.nav_host_fragment, product).commit();

//                                rqst_id = requestPojoList.get(position).getC_rqst_id();
//                                showCustomDialog();


                            }
                        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("FuelSpot");
                alert.show();
            }
        });

        return root;
    }

    public void getAppointments() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));

                    RequestAdapterUser adapter = new RequestAdapterUser(getActivity(), requestPojoList);
                    listView.setAdapter(adapter);
                    registerForContextMenu(listView);
                } else {
                    nodata.setBackgroundResource(R.drawable.no_data);
                    Toast.makeText(getContext(), "No Appointments Added", Toast.LENGTH_LONG).show();
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
                map.put("key", "getAppointmentsStation");
                map.put("s_id", uid);

                return map;
            }
        };
        queue.add(request);
    }





}
