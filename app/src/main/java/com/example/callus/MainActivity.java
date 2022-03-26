package com.example.callus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String checkTheFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfTheFirstTime();

    }
    private void checkIfTheFirstTime(){
        sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        checkTheFirstTime = sharedPreferences.getString("installForTheFirstTime","");
        if (checkTheFirstTime.equals("yes")){
            Intent i = new Intent(this,SendVCode.class);
            startActivity(i);
        }
        else {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("installForTheFirstTime","yes");
            edit.apply();
        }
    }
}