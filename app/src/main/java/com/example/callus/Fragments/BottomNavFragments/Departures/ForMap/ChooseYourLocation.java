package com.example.callus.Fragments.BottomNavFragments.Departures.ForMap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.callus.Adapters.PlaceAutoSuggestAdapter;
import com.example.callus.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class ChooseYourLocation extends AppCompatActivity {
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int ZOOM = 15;
    private static final int REQUEST_CODE = 111;
    ImageView ivGps;
    AutoCompleteTextView search;
    LatLng latLngMyLocation;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap mMap;
    String apiKey;
    boolean isLocationGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_location);
        ensureLocationPermission();
        inflate();
        search.setAdapter(new PlaceAutoSuggestAdapter(this, android.R.layout.simple_list_item_1));

        search.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Address : ", search.getText().toString());
            LatLng latLng = getLatLngFromAddress(search.getText().toString());
            if (latLng != null) {
                Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                Address address = getAddressFromLatLng(latLng);
                if (address != null) {
                    Log.d("Address : ", "" + address.toString());
                    Log.d("Address Line : ", "" + address.getAddressLine(0));
                    Log.d("Phone : ", "" + address.getPhone());
                    Log.d("Pin Code : ", "" + address.getPostalCode());
                    Log.d("Feature : ", "" + address.getFeatureName());
                    Log.d("More : ", "" + address.getLocality());
                } else {
                    Log.d("Adddress", "Address Not Found");
                }
            } else {
                Log.d("Lat Lng", "Lat Lng Not Found");
            }

        });

    }
    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(ChooseYourLocation.this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList!=null){
                Address singleAddress=addressList.get(0);
                return new LatLng(singleAddress.getLatitude(),singleAddress.getLongitude());
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    private Address getAddressFromLatLng(LatLng latLng){
        Geocoder geocoder=new Geocoder(ChooseYourLocation.this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if(addresses!=null){
                return addresses.get(0);
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    private void inflate() {
        ivGps = findViewById(R.id.ivGps);
        apiKey= getString(R.string.Api_Key);
        search = findViewById(R.id.tvAutoComplete);
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
                        moveCamera(latLngMyLocation, 15, "My Location");
                    });
                }
            }
        });
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        //add marker
        MarkerOptions options = new MarkerOptions().position(latLng)
                .title(title);
        mMap.addMarker(options);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
            initMap();
        }
    }
}