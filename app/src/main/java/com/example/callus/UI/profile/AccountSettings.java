package com.example.callus.UI.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callus.R;
import com.example.callus.UI.VerifyPhone.SendVCode;
import com.google.firebase.auth.FirebaseAuth;

public class AccountSettings extends AppCompatActivity {
    TextView tvPhoneNum, tvSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //inflate
        inflate();
        tvPhoneNum.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

        //initiate listeners
        initListeners();
    }

    private void initListeners() {
        tvSignOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, SendVCode.class));
        });
    }

    private void inflate() {
        tvPhoneNum = findViewById(R.id.tvSettingPhoneNum);
        tvSignOut = findViewById(R.id.tvSignOut);
    }
}