package com.syntax.instantfuel.USER.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackMap extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match

    private GoogleMap mMap;
    RequestPojo requestPojo;
    double sel_lati, sel_longi;
    String SERVICE_TYPE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tack_map, container, false);


        final Bundle b = this.getArguments();
        requestPojo = b.getParcelable("clicked_item");
        SERVICE_TYPE = b.getString("serviceType");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return root;
    }

//    ************


    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        sel_lati = Double.parseDouble(requestPojo.getP_lat());
        sel_longi = Double.parseDouble(requestPojo.getP_long());

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

        if(SERVICE_TYPE.trim().equals("FUEL_STATION")) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(sel_lati, sel_longi)).title(requestPojo.getS_name()).snippet(requestPojo.getS_phone()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        }else  if(SERVICE_TYPE.trim().equals("SERVICE_CENTER")){
            mMap.addMarker(new MarkerOptions().position(new LatLng(sel_lati, sel_longi)).title(requestPojo.getS_name()).snippet(requestPojo.getS_phone()).icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)));
        }
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(sel_lati, sel_longi)).zoom(18).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                String phoneNumber = requestPojo.getS_phone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                startActivity(intent);
//                int position=Integer.parseInt(marker.getId().replaceAll("[m]",""))-1;
//                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
