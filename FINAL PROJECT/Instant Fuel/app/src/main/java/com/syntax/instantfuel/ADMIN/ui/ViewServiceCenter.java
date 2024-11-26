package com.syntax.instantfuel.ADMIN.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.syntax.instantfuel.COMMON.RequestAdapter;
import com.syntax.instantfuel.COMMON.RequestPojo;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewServiceCenter extends Fragment {

    ListView listView;
    ImageView nodata;
    String sID, rqst_id;

    List<RequestPojo> requestPojoList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.admin_view_service_center, container, false);

        listView = root.findViewById(R.id.admin_view_serviceCenter);
        nodata = root.findViewById(R.id.noData);

        viewServiceCenter();

        return root;
    }

    public void viewServiceCenter() {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(response, RequestPojo[].class));

                    RequestAdapter adapter = new RequestAdapter(getActivity(), requestPojoList);
                    listView.setAdapter(adapter);
                    registerForContextMenu(listView);
                } else {
//                    nodata.setBackgroundResource(no_data_found);
                    Toast.makeText(getContext(), "No Data", Toast.LENGTH_LONG).show();
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
                map.put("key", "viewSeviceCenter");
                map.put("fs_id", uid);

                return map;
            }
        };
        queue.add(request);
    }
}
