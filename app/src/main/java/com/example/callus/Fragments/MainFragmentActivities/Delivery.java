package com.example.callus.Fragments.MainFragmentActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;

public class Delivery extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliver_with_us);
        // inflate
        inflate();
        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar("Deliver with us",getSupportActionBar());
    }
    private void inflate(){
        toolbar = findViewById(R.id.toolBar);
    }
}