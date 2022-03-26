package com.example.callus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen();
    }
    private void splashScreen(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        new Handler().postDelayed(() -> {
            Intent i= new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }, 2500);
    }
}