package com.example.callus.UI.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callus.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileInfo extends AppCompatActivity {
    TextView tvPhoneNum, tvMassages, tvSettings;
    Button btnHelp, btnWallet, btnTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        //inflate views
        inflate();

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            tvPhoneNum.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        }
        //initiate listeners
        initListeners();
    }

    private void initListeners() {
        tvMassages.setOnClickListener(view ->
                startActivity(new Intent(this, Massages.class)));
        tvSettings.setOnClickListener(view ->
                startActivity(new Intent(this, AccountSettings.class)));

        btnHelp.setOnClickListener(view ->
                startActivity(new Intent(this, Help.class)));
        btnWallet.setOnClickListener(view ->
                startActivity(new Intent(this, Wallet.class)));
        btnTrips.setOnClickListener(view ->
                startActivity(new Intent(this, Trips.class)));
    }

    private void inflate() {
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvMassages = findViewById(R.id.tvMassage);
        tvSettings = findViewById(R.id.tvSettings);

        btnHelp = findViewById(R.id.btnHelp);
        btnWallet = findViewById(R.id.btnWallet);
        btnTrips = findViewById(R.id.btnTrips);
    }

}