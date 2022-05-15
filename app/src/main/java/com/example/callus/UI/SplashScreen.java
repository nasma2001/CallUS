package com.example.callus.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.callus.R;
import com.example.callus.UI.VerifyPhone.SendVCode;


@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen();

    }
    private void splashScreen() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(SplashScreen.this, MainActivity.class));
//            finish();
//        }, 2500);
        new Handler().postDelayed(this::checkIfTheFirstTime, 2500);

    }
//    }
        private void checkIfTheFirstTime () {
            boolean hasLoggedIn = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("hasVerified", false);

            if (hasLoggedIn) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, SendVCode.class));
            }
            finish();
        }

    }
