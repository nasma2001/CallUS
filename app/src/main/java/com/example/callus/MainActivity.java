package com.example.callus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.example.callus.VerifyPhone.SendVCode;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if the first time installing the app
        checkIfTheFirstTime();

        //changing the color of the action bar
        ReusableFunctions.actionBar("Call US",getSupportActionBar());


    }
    private void checkIfTheFirstTime(){
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun)
            startActivity(new Intent(MainActivity.this, SendVCode.class));

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
    }
}