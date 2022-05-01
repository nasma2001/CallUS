package com.example.callus.Fragments.MainFragmentActivities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.callus.Adapters.SpinnerAdapter;
import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;

public class Delivery extends AppCompatActivity {
    Toolbar toolbar;
    Spinner spnWays;
    String[] arrNames = new String[4];
    Integer[] arrImages = new Integer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliver_with_us);
        // inflate
        inflate();

        //data for spinner
        preparingDataForSpinner();

        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar("Deliver with us", getSupportActionBar());
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

    private void inflate() {
        spnWays = findViewById(R.id.spnWays);
        toolbar = findViewById(R.id.toolBar);
    }

}