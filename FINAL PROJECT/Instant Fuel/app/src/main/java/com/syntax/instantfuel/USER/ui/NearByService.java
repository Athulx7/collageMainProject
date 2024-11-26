package com.syntax.instantfuel.USER.ui;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.syntax.instantfuel.COMMON.RequestPojo;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.Utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByService extends Fragment implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    // TODO: Rename parameter arguments, choose names that match

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    List<RequestPojo> requestPojoList, requestPojoList2;
    double serviceLat, serviceLong;

    String fuelID, centerID;

    static int position_fuel, position_service_center;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_view_near_by, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.emermap);
        mapFragment.getMapAsync(this);

        volly_call_getNearByList();

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    public void volly_call_getNearByList() {


        final ProgressDialog pd;
        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setTitle("Logging in...");
        //  pd.show();

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******NearBy********", response);
                // Toast.makeText(getApplicationContext(), "Cmon' " + response, Toast.LENGTH_SHORT).show();

                pd.dismiss();

                if (!response.trim().equals("failed")) {

                    String data[] = response.trim().split("#");

                    Log.d("Splitted First Array*********", "onResponse: " + data[0]);
                    Log.d("Splitted Second Array*********", "onResponse: " + data[1]);

                    Gson gson = new Gson();
                    requestPojoList = Arrays.asList(gson.fromJson(data[0], RequestPojo[].class));
                    requestPojoList2 = Arrays.asList(gson.fromJson(data[1], RequestPojo[].class));
//

                    int height = 120;
                    int width = 120;
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.fuel);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                    int height2 = 160;
                    int width2 = 160;
                    BitmapDrawable bitmapdraw2 = (BitmapDrawable) getResources().getDrawable(R.drawable.service_center);
                    Bitmap b2 = bitmapdraw2.getBitmap();
                    Bitmap smallMarker2 = Bitmap.createScaledBitmap(b2, width2, height2, false);


                    if (requestPojoList.size() != 0) {

                        for (int i = 0; i < requestPojoList.size(); i++) {

                            position_fuel = i;

                            if (requestPojoList.get(i).getServiceType().trim().equals("FUEL_STATION")) {
                                serviceLat = Double.parseDouble(requestPojoList.get(i).getP_lat());
                                serviceLong = Double.parseDouble(requestPojoList.get(i).getP_long());

                                mMap.addMarker(new MarkerOptions().position(new LatLng(serviceLat, serviceLong)).title(requestPojoList.get(i).getS_name()).snippet(requestPojoList.get(i).getS_phone()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                            }


                        }

                    } else {
                        Toast.makeText(getContext(), "No Fuel Stations Available!", Toast.LENGTH_SHORT).show();
                    }

//                    if (requestPojoList2.size() != 0) {
//
//                        for (int i = 0; i < requestPojoList2.size(); i++) {
//                            position_service_center = i;
//
//                            if (requestPojoList2.get(i).getServiceType().trim().equals("SERVICE_CENTER")) {
//                                serviceLat = Double.parseDouble(requestPojoList2.get(i).getP_lat());
//                                serviceLong = Double.parseDouble(requestPojoList2.get(i).getP_long());
//
//                                mMap.addMarker(new MarkerOptions().position(new LatLng(serviceLat, serviceLong)).title(requestPojoList2.get(i).getS_name()).snippet(requestPojoList2.get(i).getS_phone()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)));
//
//                            }
//
//
//                        }
//
//                    } else {
//                        Toast.makeText(getContext(), "No Service Centers Available!", Toast.LENGTH_SHORT).show();
//                    }

//                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(serviceLat, serviceLat)).zoom(18).build();
//                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                } else {
                    Toast.makeText(getContext(), "no data !", Toast.LENGTH_LONG).show();

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
                SharedPreferences prefs = getContext().getSharedPreferences("SharedData", MODE_PRIVATE);
                final String uid = prefs.getString("u_id", "No logid");//"No name defined" is the default value.
                Map<String, String> map = new HashMap<String, String>();
                map.put("key", "getNearByServices");
                map.put("p_lid", uid);

                return map;
            }
        };
        queue.add(request);
    }


}
