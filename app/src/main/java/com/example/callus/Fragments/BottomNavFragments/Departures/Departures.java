package com.example.callus.Fragments.BottomNavFragments.Departures;

import static com.example.callus.ReusableFunctions.Constants.CURRENT_LOCATION_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.DEFINE_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.Constants.RESULT_LAT;
import static com.example.callus.ReusableFunctions.Constants.RESULT_LNG;
import static com.example.callus.ReusableFunctions.Constants.WHERE_TO_CHOOSE_LOCATION;
import static com.example.callus.ReusableFunctions.ReusableFunctions.getAddressFromLatLng;
import static com.example.callus.ReusableFunctions.ReusableFunctions.getCurrentDate;
import static com.example.callus.ReusableFunctions.ReusableFunctions.getCurrentTime;
import static com.example.callus.ReusableFunctions.ReusableFunctions.popDatePicker;
import static com.example.callus.ReusableFunctions.ReusableFunctions.popTimePicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.callus.Database.MyTrips;
import com.example.callus.Database.MyViewModel;
import com.example.callus.Database.RequestRide;
import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Departures extends Fragment {
    //Views
    EditText etCurrentLocation, etWhereTo;
    Button btnRequest;
    TextView tvTime, tvBtmSheetDate, tvBtmSheetTime,tvDistance,tvPrice;
    BottomSheetDialog sheetDialog;
    View bottomSheet;
    Button btnSetTime;
    String date, time;
    //Vars
    MyViewModel model;
    double startLongitude, startLatitude, finalLongitude, finalLatitude;

    public Departures() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_departures, container, false);

        // inflate views
        inflate(v);

        //initialize listeners
        listeners();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    private void getDateFromBundle() {
//        bundle = getArguments();
//        if (bundle != null) {
//            double longitude = bundle.getDouble("lang");
//            double latitude = bundle.getDouble("lat");
//            String view = bundle.getString("view");
//            String res = longitude + ", " + latitude;
//            if (view.equals(CURRENT_LOCATION_CHOOSE_LOCATION))
//                etCurrentLocation.setText(res);
//            else if (view.equals(WHERE_TO_CHOOSE_LOCATION))
//                etWhereTo.setText(res);
//        }
//    }

    private void listeners() {
        etCurrentLocation.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, CURRENT_LOCATION_CHOOSE_LOCATION);
            startActivityForResult(intent, 100);
        });
        etWhereTo.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, WHERE_TO_CHOOSE_LOCATION);
            startActivityForResult(intent, 101);
        });

        tvTime.setOnClickListener(view -> showBottomSheet());

        btnRequest.setOnClickListener(view -> {
            float [] result = new float[10] ;//in meters
            if (finalLongitude != 0 && finalLatitude != 0 && startLongitude != 0 && startLatitude != 0) {
                Location.distanceBetween(startLatitude,startLongitude,finalLatitude,finalLongitude,result);

                double resultInKM =Math.ceil(result[0])/1000;
                String distance = getResources().getString(R.string.distance)+resultInKM+" KM";
                tvDistance.setText(distance);
                Drawable img = getResources().getDrawable(R.drawable.ic_new_shekel);

                img.setBounds(0, 0, 60, 60);
                tvPrice.setCompoundDrawables(null,null, img, null);

                String price = getResources().getString(R.string.price)+calculatePrice(result[0]);
                tvPrice.setText(price);

                int newTotalMoney = model.getMoneyFromCardNumber("4263982640269299")-calculatePrice(result[0]);
                model.updateMoney("4263982640269299",newTotalMoney);

                RequestRide ride = new RequestRide(etCurrentLocation.getText().toString(),
                        etWhereTo.getText().toString(), Objects.requireNonNull(FirebaseAuth.getInstance()
                        .getCurrentUser()).getPhoneNumber(),
                        time, date,calculatePrice(result[0]));

                MyTrips trips = new MyTrips(etCurrentLocation.getText().toString(),
                                            etWhereTo.getText().toString(),model.getPaymentIDByCardNumber("4263982640269299"),
                                            FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber(),
                                            calculatePrice(result[0]),
                                            date);
                model.insertMyTrips(trips);
                model.insertRequestRide(ride);
            }
        });
    }

    private int calculatePrice(float v) {
        int distance = 200;
        int price = 1;
        for(    ;distance<=v;price++){
            distance+=200;
        }
        return price;
    }

    private void showBottomSheet() {
        inflateForBottomSheet();

        tvBtmSheetTime.setText(getCurrentTime());
        tvBtmSheetDate.setText(getCurrentDate());

        BtmSheetListeners();

        sheetDialog.setContentView(bottomSheet);
        sheetDialog.show();
    }

    private void BtmSheetListeners() {
        tvBtmSheetDate.setOnClickListener(view -> popDatePicker(tvBtmSheetDate, getContext()));
        tvBtmSheetTime.setOnClickListener(view -> popTimePicker(tvBtmSheetTime, getContext()));
        btnSetTime.setOnClickListener(view -> {
            date = tvBtmSheetDate.getText().toString();
            time = tvBtmSheetTime.getText().toString();
            String date = tvBtmSheetDate.getText() + ",  at " + tvBtmSheetTime.getText();
            tvTime.setText(date);
            sheetDialog.dismiss();
        });
    }

    private void inflate(View v) {
        etCurrentLocation = v.findViewById(R.id.etCurrentLocation);
        etWhereTo = v.findViewById(R.id.etWhereTo);
        btnRequest = v.findViewById(R.id.btnRequestNow);
        tvTime = v.findViewById(R.id.tvTimeDeparting);
        tvDistance = v.findViewById(R.id.tvDistance);
        tvPrice = v.findViewById(R.id.tvPrice);

        model = new ViewModelProvider(this).get(MyViewModel.class);
    }

    public void inflateForBottomSheet() {
       if(getActivity()!=null && getContext()!= null){
           bottomSheet = LayoutInflater.from(getContext())
                   .inflate(R.layout.bottom_sheet_departures, getActivity()
                           .findViewById(R.id.bottomSheetContainerDepartures));

           sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);

           tvBtmSheetDate = bottomSheet.findViewById(R.id.tvDate);
           tvBtmSheetTime = bottomSheet.findViewById(R.id.tvTimeBottomSheet);
           btnSetTime = bottomSheet.findViewById(R.id.btnSetTime);
       }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Bundle bundle = data.getExtras();
            LatLng latLng = new LatLng(bundle.getDouble(RESULT_LAT), bundle.getDouble(RESULT_LNG));
            String result = getAddressFromLatLng(latLng, getActivity())[0];
//            +", " + getAddressFromLatLng(latLng, getActivity())[1];

            if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                etCurrentLocation.setText(result);
                startLatitude = latLng.latitude;
                startLongitude = latLng.longitude;
            } else if ((requestCode == 101 && resultCode == Activity.RESULT_OK)) {
                etWhereTo.setText(result);
                finalLatitude = latLng.latitude;
                finalLongitude = latLng.longitude;
            }
        }
    }

}