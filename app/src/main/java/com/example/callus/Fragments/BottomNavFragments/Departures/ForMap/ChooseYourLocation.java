package com.example.callus.Fragments.BottomNavFragments.Departures.ForMap;

import static com.example.callus.ReusableFunctions.Constants.COARSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.CURRENT_LOCATION_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.DEFINE_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.DELIVER_TO_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.DELIVER_WHERE_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.FINE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.REQUEST_CODE;
import static com.example.callus.ReusableFunctions.Constants.RESULT_LAT;
import static com.example.callus.ReusableFunctions.Constants.RESULT_LNG;
import static com.example.callus.ReusableFunctions.Constants.RESULT_SAVED_PLACES;
import static com.example.callus.ReusableFunctions.Constants.SET_ON_MAP_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.WHERE_TO_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.ZOOM;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.callus.R;
import com.example.callus.UI.MainActivity;
import com.example.callus.UI.MainFragmentActivities.Delivery;
import com.example.callus.UI.MainFragmentActivities.SavedPlaces;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class ChooseYourLocation extends AppCompatActivity {
    //views
    ImageView ivGps;
    AutoCompleteTextView search;
    Button btnSetLocation;
    //
    Marker marker;
    LatLng latLngMyLocation,finalLocationForCurrent,finalLocationForWhereTo,
            finalLocationForSavedPlaces,finalLocationForWhereDeliver,finalLocationForToDeliver;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap mMap;
    String apiKey, definer;

    //flags
    boolean isLocationGranted = false;

    @Override
    protected void onStart() {
        super.onStart();
        ensureLocationPermission();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_location);

        inflate();

        defineTheContextComeFrom();

        initListeners();


    }

    private void defineTheContextComeFrom() {
        definer = getIntent().getStringExtra(DEFINE_CHOOSE_LOCATION);
    }

    private void initListeners() {
        goToMyCurrentLocation();
        whenFinishTheActivity();
    }

    private void whenFinishTheActivity() {
        btnSetLocation.setOnClickListener(view -> {
            Intent intent;
            Bundle bundle = new Bundle();

            switch (definer) {
                case CURRENT_LOCATION_CHOOSE_LOCATION:

                    bundle.putDouble(RESULT_LAT,finalLocationForCurrent.latitude);
                    bundle.putDouble(RESULT_LNG,finalLocationForCurrent.longitude);

                    intent = new Intent(this, MainActivity.class);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    break;

                case WHERE_TO_CHOOSE_LOCATION:
                    bundle.putDouble(RESULT_LAT,finalLocationForWhereTo.latitude);
                    bundle.putDouble(RESULT_LNG,finalLocationForWhereTo.longitude);

                    intent = new Intent(this, MainActivity.class);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    break;
                case SET_ON_MAP_CHOOSE_LOCATION:
                    finish();
                    intent = new Intent(this, SavedPlaces.class);
                    intent.putExtra(RESULT_SAVED_PLACES, finalLocationForSavedPlaces);
                    startActivity(intent);
                    break;
                case DELIVER_WHERE_CHOOSE_LOCATION:
                    bundle.putDouble(RESULT_LAT,finalLocationForWhereDeliver.latitude);
                    bundle.putDouble(RESULT_LNG , finalLocationForWhereDeliver.longitude);

                    intent = new Intent(this, Delivery.class);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    break;

                case DELIVER_TO_CHOOSE_LOCATION:
                    bundle.putDouble(RESULT_LAT,finalLocationForToDeliver.latitude);
                    bundle.putDouble(RESULT_LNG , finalLocationForToDeliver.longitude);

                    intent = new Intent(this, Delivery.class);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    break;

            }
        });

    }

    private void goToMyCurrentLocation() {
        ivGps.setOnClickListener(view -> {
            moveCamera(latLngMyLocation, ZOOM, "here");
        });
    }
//        search.setAdapter(new PlaceAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));

//        search.setOnItemClickListener((parent, view, position, id) -> {
//            Log.d("Address : ", search.getText().toString());
//            LatLng latLng = getLatLngFromAddress(search.getText().toString());
//            if (latLng != null) {
//                Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
//                Address address = getAddressFromLatLng(latLng);
//                if (address != null) {
//                    Log.d("Address : ", "" + address);
//                    Log.d("Address Line : ", "" + address.getAddressLine(0));
//                    Log.d("Phone : ", "" + address.getPhone());
//                    Log.d("Pin Code : ", "" + address.getPostalCode());
//                    Log.d("Feature : ", "" + address.getFeatureName());
//                    Log.d("More : ", "" + address.getLocality());
//                } else {
//                    Log.d("Adddress", "Address Not Found");
//                }
//            } else {
//                Log.d("Lat Lng", "Lat Lng Not Found");
//            }
//
//        });
//    private LatLng getLatLngFromAddress(String address){
//
//        Geocoder geocoder=new Geocoder(ChooseYourLocation.this);
//        List<Address> addressList;
//
//        try {
//            addressList = geocoder.getFromLocationName(address, 1);
//            if(addressList!=null){
//                Address singleAddress=addressList.get(0);
//                return new LatLng(singleAddress.getLatitude(),singleAddress.getLongitude());
//            }
//            else{
//                return null;
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    private Address getAddressFromLatLng(LatLng latLng){
//        Geocoder geocoder=new Geocoder(ChooseYourLocation.this);
//        List<Address> addresses;
//        try {
//            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
//            if(addresses!=null){
//                return addresses.get(0);
//            }
//            else{
//                return null;
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//
//    }


    private void inflate() {
        ivGps = findViewById(R.id.ivGps);
        apiKey = getString(R.string.Api_Key);
        search = findViewById(R.id.tvAutoComplete);
        btnSetLocation = findViewById(R.id.btnSetLocation);
    }

    private void initMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        client = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.currentMap);
                if (supportMapFragment != null) {
                    supportMapFragment.getMapAsync(googleMap -> {
                        mMap = googleMap;
                        latLngMyLocation = new LatLng(location.getLatitude()
                                , location.getLongitude());

                        //to zoom the map
                        googleMap.setMyLocationEnabled(true);
                        moveCamera(latLngMyLocation, 15, "My Location");

                        //to change the marker when click
                        changeMarkerPosition();
                    });
                }
            }

        });
    }

    private void changeMarkerPosition() {
        mMap.setOnMapClickListener(latLng -> {
            MarkerOptions options = new MarkerOptions().position(latLng);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            if (marker != null)
                marker.remove();
            marker = mMap.addMarker(options);
            switch (definer) {
                case CURRENT_LOCATION_CHOOSE_LOCATION:
                    finalLocationForCurrent = latLng;
                    break;
                case WHERE_TO_CHOOSE_LOCATION:
                    finalLocationForWhereTo = latLng;
                    break;
                case SET_ON_MAP_CHOOSE_LOCATION:
                    finalLocationForSavedPlaces = latLng;
                    break;
                case DELIVER_WHERE_CHOOSE_LOCATION:
                    finalLocationForWhereDeliver = latLng;
                    break;
                case DELIVER_TO_CHOOSE_LOCATION:
                    finalLocationForToDeliver = latLng;
                    break;
            }
        });
    }


    private void moveCamera(LatLng latLng, float zoom, String title) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//        addMarker(latLng);
    }


    private void ensureLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                isLocationGranted = true;
                initMap();
            } else {
                String[] permissions = {FINE_LOCATION, COARSE_LOCATION};
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    isLocationGranted = false;
                    return;
                }
            }
            isLocationGranted = true;
        }
    }
}