package com.example.callus.UI.MainFragmentActivities;

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
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.callus.Adapters.SpinnerAdapter;
import com.example.callus.Database.MyViewModel;
import com.example.callus.Database.Requests;
import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

public class Delivery extends AppCompatActivity {
    //views
    Toolbar toolbar;
    Spinner spnWays;
    EditText etFrom, etTo;
    TextView tvSetTime;

    //views for btm sheet
    TextView tvBtmSheetDate, tvBtmSheetTime,tvDistance,tvPrice;
    BottomSheetDialog sheetDialog;
    View bottomSheet;
    Button btnSetTime, btnRequest;

    //vars
    String[] arrNames = new String[4];
    Integer[] arrImages = new Integer[4];
    MyViewModel model;
    String from, to, way, date, time;
    double startLongitude, startLatitude, finalLongitude, finalLatitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_with_us);
        // inflate
        inflate();

        //data for spinner
        preparingDataForSpinner();

        //listeners
        listeners();

        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar(getResources().getString(R.string.deliver), getSupportActionBar(),
                true, toolbar.findViewById(R.id.toolbar_title));
    }

    private void preparingDataForSpinner() {
        arrNames[0] = getResources().getString(R.string.motor);
        arrNames[1] = getResources().getString(R.string.car);
        arrNames[2] = getResources().getString(R.string.van);
        arrNames[3] = getResources().getString(R.string.bus);

        arrImages[0] = R.drawable.ic_motorbike;
        arrImages[1] = R.drawable.ic_car;
        arrImages[2] = R.drawable.ic_van;
        arrImages[3] = R.drawable.ic_bus;


        //to change the arrow color
        spnWays.getBackground().
                setColorFilter(getResources().
                        getColor(R.color.grayLines), PorterDuff.Mode.SRC_ATOP);

        //create adapter
        createAdapter();
    }

    private void createAdapter() {
        SpinnerAdapter spinner = new SpinnerAdapter(this, R.id.spnWays, arrNames, arrImages);
        spinner.setDropDownViewResource(R.layout.custom_spinner);
        spnWays.setAdapter(spinner);
    }

    public void inflateForBottomSheet() {
        bottomSheet = LayoutInflater.from(this)
                .inflate(R.layout.bottom_sheet_departures, this
                        .findViewById(R.id.bottomSheetContainerDepartures));

        sheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);

        tvBtmSheetDate = bottomSheet.findViewById(R.id.tvDate);
        tvBtmSheetTime = bottomSheet.findViewById(R.id.tvTimeBottomSheet);
        btnSetTime = bottomSheet.findViewById(R.id.btnSetTime);
    }

    private void listeners() {
        etFrom.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, CURRENT_LOCATION_CHOOSE_LOCATION);
            startActivityForResult(intent, 102);
        });
        etTo.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChooseYourLocation.class);
            intent.putExtra(DEFINE_CHOOSE_LOCATION, WHERE_TO_CHOOSE_LOCATION);
            startActivityForResult(intent, 103);
        });
        spnWays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                way = arrNames[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                way = arrNames[0];
            }
        });
        tvSetTime.setOnClickListener(view -> showBottomSheet());
        btnRequest.setOnClickListener(view -> {
            if (from != null && to != null && way != null && date != null && time != null) {

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
                    Requests requests = new Requests(from, to, way, FirebaseAuth.getInstance()
                            .getCurrentUser().getPhoneNumber(), date, time,calculatePrice(result[0]));
                    Toast.makeText(this, from + way, Toast.LENGTH_SHORT).show();
                    model.insertRequest(requests);
                }
            } else
                Toast.makeText(this, "Ensure filling the fields", Toast.LENGTH_SHORT).show();
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
        tvBtmSheetDate.setOnClickListener(view -> popDatePicker(tvBtmSheetDate, this));
        tvBtmSheetTime.setOnClickListener(view -> popTimePicker(tvBtmSheetTime, this));
        btnSetTime.setOnClickListener(view -> {
            date = tvBtmSheetDate.getText().toString();
            time = tvBtmSheetTime.getText().toString();
            String date = tvBtmSheetDate.getText() + ",  at " + tvBtmSheetTime.getText();
            tvSetTime.setText(date);
            sheetDialog.dismiss();
        });
    }

    private void inflate() {
        spnWays = findViewById(R.id.spnWays);
        toolbar = findViewById(R.id.toolBar);
        etTo = findViewById(R.id.etTo);
        etFrom = findViewById(R.id.etForm);
        tvSetTime = findViewById(R.id.tvWhen);
        btnRequest = findViewById(R.id.btnRequest);
        tvDistance = findViewById(R.id.tvDistanceDelivery);
        tvPrice = findViewById(R.id.tvPriceDelivery);
        model = new ViewModelProvider(this).get(MyViewModel.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            LatLng latLng = new LatLng(bundle.getDouble(RESULT_LAT), bundle.getDouble(RESULT_LNG));
            String result = getAddressFromLatLng(latLng, this)[0] + ", " + getAddressFromLatLng(latLng, this)[1];

            if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
                startLatitude = latLng.latitude;
                startLongitude = latLng.longitude;
                from = result;
                etFrom.setText(result);
            } else if ((requestCode == 103 && resultCode == Activity.RESULT_OK)) {
                to = result;
                etTo.setText(result);
                finalLatitude = latLng.latitude;
                finalLongitude = latLng.longitude;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}