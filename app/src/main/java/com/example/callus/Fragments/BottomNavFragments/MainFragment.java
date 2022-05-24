package com.example.callus.Fragments.BottomNavFragments;

import static com.example.callus.ReusableFunctions.Constants.FINE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.REQUEST_CHECK_CODE;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.callus.R;
import com.example.callus.UI.MainFragmentActivities.Delivery;
import com.example.callus.UI.MainFragmentActivities.SavedPlaces;
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
import com.google.android.gms.tasks.Task;


public class MainFragment extends Fragment {
    //views
    TextView tvDelivery, tvSavedPlaces;

    //vars
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    Boolean permission = false;
    LocationRequest locationRequest;
    LocationManager locationManager;
    LocationListener locationListener;
    double longitude, latitude;
    String provider;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requestPermissions(new String[]{FINE_LOCATION}, 1);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        // inflate views
        inflate(v);

        //initiate listeners
        tvSavedPlaces.setOnClickListener(view -> startActivity(new Intent(getActivity(), SavedPlaces.class)));
        tvDelivery.setOnClickListener(view -> startActivity(new Intent(getActivity(), Delivery.class)));

        return v;
    }


    private void inflate(View v) {
        tvDelivery = v.findViewById(R.id.tvDeliver);
        tvSavedPlaces = v.findViewById(R.id.tvChooseSavedPlace);
    }

    private void initMap() {
        if (getContext() != null && getActivity() != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{FINE_LOCATION}, 1);
            }

            prepareSettingForGPS();

            //update location
            updateLocation();
            locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);

            client = LocationServices.getFusedLocationProviderClient(getActivity());
            Location mLocation = locationManager.getLastKnownLocation(provider);
            if (mLocation != null) {
                supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fcvMap);
                if (supportMapFragment != null) {
                    supportMapFragment.getMapAsync(googleMap -> {
                        LatLng latLng = new LatLng(mLocation.getLatitude()
                                , mLocation.getLongitude());
                        googleMap.setMyLocationEnabled(true);

                        //to zoom the map
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    });
                }
            }
        }
    }

    private void updateLocation() {
        locationListener = location -> {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        };
    }

    private void prepareSettingForGPS() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria crta = new Criteria();
        crta.setAccuracy(Criteria.ACCURACY_FINE);
        crta.setAltitudeRequired(true);
        crta.setBearingRequired(true);
        crta.setCostAllowed(true);
        crta.setPowerRequirement(Criteria.POWER_LOW);
        provider = locationManager.getBestProvider(crta, true);
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
        task.addOnCompleteListener(task1 -> {
            try {
                LocationSettingsResponse response = task1.getResult(ApiException.class);
                LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                //device location is on
                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                    initMap();
            }

            //device location is off
            catch (ApiException e) {
                if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                    //off
                    try {
                        ResolvableApiException exception = (ResolvableApiException) e;
                        exception.startResolutionForResult(getActivity(), REQUEST_CHECK_CODE);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions[0].equals(FINE_LOCATION)&& permissions.length>0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission = true;
            } else
                requestPermissions(new String[]{FINE_LOCATION}, 1);
        }
    }


}


