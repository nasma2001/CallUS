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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Departures extends Fragment {
    //Views
    EditText etCurrentLocation, etWhereTo;
    Button btnSearch;
    TextView tvTime, tvBtmSheetDate, tvBtmSheetTime;
    BottomSheetDialog sheetDialog;
    View bottomSheet;
    Button btnSetTime;

    //Vars
    Bundle bundle;

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
        getDateFromBundle();
    }

    private void getDateFromBundle() {
        bundle = getArguments();
        if(bundle != null){
            Toast.makeText(getActivity(), "0000", Toast.LENGTH_SHORT).show();
            double longitude = bundle.getDouble("lang");
            double latitude = bundle.getDouble("lat");
            String view = bundle.getString("view");
            String res = longitude+", "+latitude;
            if(view.equals(CURRENT_LOCATION_CHOOSE_LOCATION))
                etCurrentLocation.setText(res);
            else if (view.equals(WHERE_TO_CHOOSE_LOCATION))
                etWhereTo.setText(res);
        }
    }

    private void listeners() {
        etCurrentLocation.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, CURRENT_LOCATION_CHOOSE_LOCATION);
            startActivityForResult(intent,100);
        });
        etWhereTo.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, WHERE_TO_CHOOSE_LOCATION);
            startActivityForResult(intent,101);
        });

        tvTime.setOnClickListener(view -> showBottomSheet());
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
        tvBtmSheetDate.setOnClickListener(view -> popDatePicker(tvBtmSheetDate,getContext()));
        tvBtmSheetTime.setOnClickListener(view -> popTimePicker(tvBtmSheetTime,getContext()));
        btnSetTime.setOnClickListener(view -> {
            String date = tvBtmSheetDate.getText() + ",  at " + tvBtmSheetTime.getText();
            tvTime.setText(date);
            sheetDialog.dismiss();
        });
    }

    private void inflate(View v) {
        etCurrentLocation = v.findViewById(R.id.etCurrentLocation);
        etWhereTo = v.findViewById(R.id.etWhereTo);
        btnSearch = v.findViewById(R.id.btnSearch);
        tvTime = v.findViewById(R.id.tvTimeDeparting);
    }

     public void inflateForBottomSheet() {
        bottomSheet = LayoutInflater.from(getContext())
                .inflate(R.layout.bottom_sheet_departures, getActivity()
                        .findViewById(R.id.bottomSheetContainerDepartures));

        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);

        tvBtmSheetDate = bottomSheet.findViewById(R.id.tvDate);
        tvBtmSheetTime = bottomSheet.findViewById(R.id.tvTimeBottomSheet);
        btnSetTime = bottomSheet.findViewById(R.id.btnSetTime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle=data.getExtras();
        LatLng latLng = new LatLng(bundle.getDouble(RESULT_LAT),bundle.getDouble(RESULT_LNG));
        String result = getAddressFromLatLng(latLng, getActivity())[0]+", "+ getAddressFromLatLng(latLng,getActivity())[1];

        if(requestCode == 100 && resultCode == Activity.RESULT_OK) {
            etCurrentLocation.setText(result);
        }
        else if ((requestCode == 101 && resultCode == Activity.RESULT_OK)){
            etWhereTo.setText(result);
        }
    }

}