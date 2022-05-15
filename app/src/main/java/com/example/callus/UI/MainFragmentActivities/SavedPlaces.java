package com.example.callus.UI.MainFragmentActivities;

import static com.example.callus.ReusableFunctions.ReusableFunctions.actionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Adapters.SavedPlaceAdapter;
import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.Database.SavedPlacesModel;
import com.example.callus.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class SavedPlaces extends AppCompatActivity {
    //views
    Toolbar toolbar;
    TextView tvAddPlace;
    RecyclerView rvShowPlaces;
    EditText etCity, etStreet, etHome;
    View bottomSheet;
    BottomSheetDialog sheetDialog;
    Button btnSetOnMap, btnSave;
    ArrayList<SavedPlacesModel> favPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);

        // inflate
        inflate();

        //initialize listeners
        initListeners();

        prepareRecycler();

        setSupportActionBar(toolbar);
        actionBar("Choose a saved place", getSupportActionBar(),true,
                toolbar.findViewById(R.id.toolbar_title));
    }


    private void prepareRecycler() {
        SavedPlaceAdapter adapter = new SavedPlaceAdapter(this,favPlaces);
        rvShowPlaces.setAdapter(adapter);

        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        rvShowPlaces.setLayoutManager(l);
    }

    private void initListeners() {
        tvAddPlace.setOnClickListener(v -> showBottomSheet());
    }

    private void showBottomSheet() {
        inflateForBottomSheet();

        BtmSheetListeners();
        sheetDialog.setContentView(bottomSheet);
        sheetDialog.show();
    }

    private void BtmSheetListeners() {
        btnSetOnMap.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChooseYourLocation.class);
            intent.putExtra("definer","btnSetOnMap");
            startActivity(intent);
            sheetDialog.dismiss();
        });
        btnSave.setOnClickListener(view -> {
            String city = etCity.getText().toString();
            String street = etStreet.getText().toString();
            String home = etHome.getText().toString();

            SavedPlacesModel placesModel = new SavedPlacesModel(city,street,home);
            favPlaces.add(placesModel);
            sheetDialog.dismiss();
        });
    }

    private void inflateForBottomSheet() {
        bottomSheet = LayoutInflater.from(this)
                .inflate(R.layout.bottom_sheet_saved_places, findViewById(R.id.bottomSheetContainer));

        sheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);

        btnSetOnMap = bottomSheet.findViewById(R.id.btnSetOnMap);
        btnSave = bottomSheet.findViewById(R.id.btnSave);

        etCity = bottomSheet.findViewById(R.id.etCity);
        etStreet = bottomSheet.findViewById(R.id.etStreet);
        etHome = bottomSheet.findViewById(R.id.etHome);
    }

    private void inflate() {
        tvAddPlace = findViewById(R.id.tvAddSavedPlace);
        toolbar = findViewById(R.id.toolBar);
        rvShowPlaces = findViewById(R.id.rvSaved_places);
    }

    @Override
    public  boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}