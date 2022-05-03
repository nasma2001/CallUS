package com.example.callus.Fragments.MainFragmentActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.Models.SavedPlacesModel;
import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SavedPlaces extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvAddPlace;
    RecyclerView rvShowPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);

        // inflate
        inflate();

        //initialize listeners
        initListeners();

        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar("Choose a saved place", getSupportActionBar());
    }

    private void initListeners() {
        tvAddPlace.setOnClickListener(v -> {
            //show bottom sheet
            showBottomSheet();
        });
    }

    private void showBottomSheet() {
        BottomSheetDialog sheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheet = LayoutInflater.from(this)
                .inflate(R.layout.bottom_sheet_saved_places, findViewById(R.id.bottomSheetContainer));

        bottomSheet.findViewById(R.id.btnSetOnMap).setOnClickListener(view -> {
            startActivity(new Intent(this, ChooseYourLocation.class));
            sheetDialog.dismiss();
        });

        bottomSheet.findViewById(R.id.btnSave).setOnClickListener(view -> {
            EditText etCity = bottomSheet.findViewById(R.id.etCity);
            String city = etCity.getText().toString();

            EditText etStreet = bottomSheet.findViewById(R.id.etStreet);
            String street = etStreet.getText().toString();

            EditText etHome = bottomSheet.findViewById(R.id.etHome);
            String home = etHome.getText().toString();

            SavedPlacesModel placesModel = new SavedPlacesModel();
            placesModel.setCity(city);
            placesModel.setStreet(street);
            placesModel.setHome(home);

            sheetDialog.dismiss();
        });
        sheetDialog.setContentView(bottomSheet);
        sheetDialog.show();
    }

    private void inflate() {
        tvAddPlace = findViewById(R.id.tvAddSavedPlace);
        toolbar = findViewById(R.id.toolBar);
        rvShowPlaces = findViewById(R.id.rvSaved_places);
    }

}