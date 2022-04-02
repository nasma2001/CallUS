package com.example.callus.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.callus.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;


public class MainFragment extends Fragment{
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    public static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String[]{LOCATION_PERMISSION}, 1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        return v;
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), LOCATION_PERMISSION) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{LOCATION_PERMISSION}, 1);
        }
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fcvMap);
                if (supportMapFragment != null) {
                    supportMapFragment.getMapAsync(googleMap -> {
                        LatLng latLng = new LatLng(location.getLatitude()
                                , location.getLongitude());
                        //add marker
                        MarkerOptions options = new MarkerOptions().position(latLng)
                                .title("My Location");
                        //to zoom the map
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        googleMap.addMarker(options);
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions[0].equals(LOCATION_PERMISSION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else
                requestPermissions(new String[]{LOCATION_PERMISSION}, 1);
        }
    }


}