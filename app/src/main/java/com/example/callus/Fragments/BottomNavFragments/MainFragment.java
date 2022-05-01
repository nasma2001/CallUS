package com.example.callus.Fragments.BottomNavFragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.callus.Fragments.MainFragmentActivities.Delivery;
import com.example.callus.Fragments.MainFragmentActivities.SavedPlaces;
import com.example.callus.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainFragment extends Fragment {
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    TextView tvDelivery, tvSavedPlaces;
    public static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    Boolean permission = false;
    public static final int REQUEST_CHECK_CODE = 1001;
    LocationRequest locationRequest;
    double longitude,latitude;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        //request turn on the location
        createLocationRequest();

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
        // inflate views
        inflate(v);
        tvSavedPlaces.setOnClickListener(view -> startActivity(new Intent(getActivity(), SavedPlaces.class)));
        tvDelivery.setOnClickListener(view -> startActivity(new Intent(getActivity(), Delivery.class)));

        return v;
    }

    private void inflate(View v) {
        tvDelivery = v.findViewById(R.id.tvDeliver);
        tvSavedPlaces = v.findViewById(R.id.tvChooseSavedPlace);
    }

    private void getCurrentLocation() {
        if (getContext() != null && getActivity() != null){
            if (ActivityCompat.checkSelfPermission(getContext(), LOCATION_PERMISSION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{LOCATION_PERMISSION}, 1);
            }
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria crta = new Criteria();
            crta.setAccuracy(Criteria.ACCURACY_FINE);
            crta.setAltitudeRequired(true);
            crta.setBearingRequired(true);
            crta.setCostAllowed(true);
            crta.setPowerRequirement(Criteria.POWER_LOW);
            String provider = locationManager.getBestProvider(crta, true);
            Log.d("","provider : "+provider);

            // String provider = LocationManager.GPS_PROVIDER;
            LocationListener locationListener = location -> {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            };
            locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);
            client = LocationServices.getFusedLocationProviderClient(getActivity());
            Location mlocation = locationManager.getLastKnownLocation(provider);
            if (mlocation != null) {
                supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fcvMap);
                if (supportMapFragment != null) {
                    supportMapFragment.getMapAsync(googleMap -> {

                        LatLng latLng = new LatLng(mlocation.getLatitude()
                                , mlocation.getLongitude());
                        //add marker
                        MarkerOptions options = new MarkerOptions().position(latLng)
                                .title("My Location");
                        //to zoom the map
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        googleMap.addMarker(options);
                    });
                }
            }
        }

    }
    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //check if location is turned on or not
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        Task<LocationSettingsResponse> task = LocationServices.getSettingsClient(getActivity())
                .checkLocationSettings(builder.build());

        //initListeners
        initListeners(task);

    }
    private void initListeners(Task<LocationSettingsResponse> task) {
        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    //device location is on
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                    if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                        getCurrentLocation();

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fcvContainer,new MainFragment());
                }
                //device location is off
                catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            //off
                            try {
                                ResolvableApiException exception = (ResolvableApiException) e;
                                exception.startResolutionForResult(getActivity(), REQUEST_CHECK_CODE);
                            } catch (IntentSender.SendIntentException sendIntentException) {
                                sendIntentException.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //when theres no location for the device
                            break;
                    }
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
                permission = true;
            } else
                requestPermissions(new String[]{LOCATION_PERMISSION}, 1);
        }
    }



}


