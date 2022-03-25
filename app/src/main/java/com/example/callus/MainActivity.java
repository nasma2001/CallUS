package com.example.callus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PlanFragment planFragment = new PlanFragment();
        ft.replace(R.id.flContainer, planFragment);
        ft.addToBackStack(null);
        ft.commit();

        checkIfTheFirstTime();

    }

    private void checkIfTheFirstTime(){
        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("firstRun", true);

        if (firstRun) // first install the app
            startActivity(new Intent(MainActivity.this, SendVCode.class));

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("firstRun", false).apply();
    }
}