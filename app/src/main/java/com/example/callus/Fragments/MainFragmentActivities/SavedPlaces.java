package com.example.callus.Fragments.MainFragmentActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;

import java.util.ArrayList;

public class SavedPlaces extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_places);
        // inflate
        inflate();
        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar("Deliver with us",getSupportActionBar());

        ArrayList<savedplaceData> savedplaceData = new ArrayList<>();

        savedplaceData sd1 = new savedplaceData("Aya Elagha","Al-Aqsa University");
        savedplaceData.add(sd1);
        RecyclerView rvsaved_places = findViewById(R.id.rvsaved_places);

        savedplaceAdepter savedplaceAdepter = new savedplaceAdepter(this, savedplaceData);
        rvsaved_places.setAdapter(savedplaceAdepter);
        LinearLayoutManager llm = new
                LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        rvsaved_places.setLayoutManager(llm);



    }
    private void inflate(){
        toolbar = findViewById(R.id.toolBar);
    }
}