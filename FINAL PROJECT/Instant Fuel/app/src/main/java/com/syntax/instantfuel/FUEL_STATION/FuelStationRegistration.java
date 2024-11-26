package com.syntax.instantfuel.FUEL_STATION;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.syntax.instantfuel.COMMON.GPSTracker;
import com.syntax.instantfuel.LoginActivity;
import com.syntax.instantfuel.R;
import com.syntax.instantfuel.RegistrationActivity;
import com.syntax.instantfuel.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FuelStationRegistration extends AppCompatActivity {

    Button register;
    TextView login;
    EditText name, owner, address, phone, email, password;
    TextView LAT, LONG, mapaddress;

    Spinner district;
    String emailPattern, PROLAT, PROLONG;

    String d_name, d_owner, d_address, d_phone, d_email, d_pswd, d_district, d_dateOfjoin;
    String[] districtList;

    ImageView location_marker;
    String ADDRESS = "";
    GPSTracker gps;
    double latitude;
    double longitude;
    Button bookingButton, btn;

    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_registration);
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        // method to get the location
        getLastLocation();

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm");
        d_dateOfjoin = ft.format(dNow);

        name = findViewById(R.id.input_name);
        owner = findViewById(R.id.input_owner_name);
        address = findViewById(R.id.input_address);
        LAT = (TextView) findViewById(R.id.fuelstationLat);
        LONG = (TextView) findViewById(R.id.fuelstationLong);
        phone = findViewById(R.id.input_phone);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        district = findViewById(R.id.input_district);
        districtList = getResources().getStringArray(R.array.array_district);
        register = findViewById(R.id.seller_registerButton);
        login = findViewById(R.id.link_login);
        location_marker = findViewById(R.id.locationIcon);


        location_marker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                PopMap(LAT, LONG, address, "address");


                return false;
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, districtList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(aa);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                d_district = district.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        finish();
    }

    //    *****************

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            double lat = location.getLatitude();
                            double longi = location.getLongitude();
                            latitude = lat;
                            longitude = longi;
                            Toast.makeText(getApplicationContext(), "Location Updated", Toast.LENGTH_SHORT).show();
//                            latitudeTextView.setText(location.getLatitude() + "");
//                            longitTextView.setText(location.getLongitude() + "");
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            double lat = mLastLocation.getLatitude();
            double longi = mLastLocation.getLongitude();
            latitude = lat;
            longitude = longi;
            Toast.makeText(getApplicationContext(), "Location Updated", Toast.LENGTH_SHORT).show();
//            latitudeTextView.setText("Latitude: " + mLastLocation.getLatitude() + "");
//            longitTextView.setText("Longitude: " + mLastLocation.getLongitude() + "");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(FuelStationRegistration.this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

//    ***********************


    private void validate() {

        d_name = name.getText().toString();
        d_owner = owner.getText().toString();
        d_address = address.getText().toString();
        d_phone = phone.getText().toString();
        d_email = email.getText().toString();
        d_pswd = password.getText().toString();
        PROLAT = LAT.getText().toString().trim();
        PROLONG = LONG.getText().toString().trim();
//        d_pswd = password.getText().toString();

        if (d_name.isEmpty()) {
            name.requestFocus();
            name.setError("enter a username");
        } else if (d_owner.isEmpty()) {
            owner.requestFocus();
            owner.setError("enter your age");
        } else if (d_address.isEmpty()) {
            address.requestFocus();
            address.setError("enter your address");
        } else if (PROLAT.isEmpty()) {
            LAT.requestFocus();
            LAT.setError("Enter Latitude");
        } else if (PROLONG.isEmpty()) {
            LONG.requestFocus();
            LONG.setError("Enter Longitude");
        } else if (d_phone.isEmpty()) {
            phone.requestFocus();
            phone.setError("enter a mobile number");
        } else if (d_phone.length() != 10) {
            phone.requestFocus();
            phone.setError("enter a valid mobile number");
        } else if (d_email.isEmpty()) {
            email.requestFocus();
            email.setError("enter an email");
        } else if (!d_email.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            email.setError("enter a valid email address");
        } else if (d_pswd.isEmpty()) {
            password.requestFocus();
            password.setError("enter password");
        } else {
            fuelStationRegistration();
        }
    }

    private void fuelStationRegistration() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.SERVERUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (response.trim().equals("Already Exist")) {
                    Toast.makeText(getApplicationContext(), "Account is already Exist", Toast.LENGTH_SHORT).show();
                } else if (!response.trim().equals("failed")) {
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "fuelstation_register");
                map.put("d_name", d_name);
                map.put("d_ownername", d_owner);
                map.put("d_address", d_address);
                map.put("d_phone", d_phone);
                map.put("d_email", d_email);
                map.put("d_pswd", d_pswd);
                map.put("d_dst", d_district);
                map.put("d_doj", d_dateOfjoin);
                map.put("p_lat", PROLAT);
                map.put("p_long", PROLONG);

                return map;
            }
        };
        queue.add(request);
    }


    void PopMap(final TextView lat, final TextView longg, final EditText address, final String ADDTYPE) {
        final Dialog dialog = new Dialog(FuelStationRegistration.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /////make map clear
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(R.layout.custom_map_lattlongg);////your custom content
        MapView mMapView = (MapView) dialog.findViewById(R.id.mapView);
        mapaddress = dialog.findViewById(R.id.custom_map_address);
        btn = dialog.findViewById(R.id.custom_map_btnadd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ADDTYPE.equals("address")) {
                    if (ADDRESS.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Select location", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something gone wrong", Toast.LENGTH_SHORT).show();

                }


            }
        });

        MapsInitializer.initialize(getApplicationContext());

        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {


                LatLng posisiabsen = new LatLng(latitude, longitude); ////your lat lng
                googleMap.addMarker(new MarkerOptions().position(posisiabsen).title("Yout title"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(posisiabsen));
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.5f));


                //                ....
                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {

                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).title("New Location"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latLng.latitude, latLng.longitude)));
                        latitude = latLng.latitude;
                        longitude = latLng.longitude;

                        lat.setText("" + latitude);
                        longg.setText("" + longitude);

                        //getAddress strat
                        try {

                            Geocoder geocoder;
                            List<Address> yourAddresses;
                            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                            yourAddresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                            if (yourAddresses.size() > 0) {
                                String yourAddress = yourAddresses.get(0).getAddressLine(0);
                                String yourCity = yourAddresses.get(0).getAddressLine(1);
                                String yourCountry = yourAddresses.get(0).getAddressLine(2);
                                Log.d("***", yourAddress);
                                if (ADDTYPE.equals("address")) {
                                    ADDRESS = yourAddress;
                                }
                                ADDRESS = yourAddress;
                                address.setText(yourAddress);
                                mapaddress.setText(yourAddress);

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //getAddress end

                    }
                });
//                ....


            }
        });


        dialog.show();
    }

}

