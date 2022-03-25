package com.example.callus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendVCode extends AppCompatActivity {
    Button btnContinue;
    EditText etPhoneNumber;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_number);


        //inflate views
        inflate();

        //when button continue clicked , it will send u a verification code
        btnContinue.setOnClickListener(view -> {
            if (etPhoneNumber.getText().toString().length() == 9) {
                String phone = etPhoneNumber.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.INVISIBLE);
                firebasePhoneNumAuth(phone,SendVCode.this,mCallbacks);
            } else
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();

        });

        // hide action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

    }

    private void inflate() {
        btnContinue = findViewById(R.id.btnContinuePhone);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        progressBar = findViewById(R.id.pbContinue);
    }

    public void firebasePhoneNumAuth(String phone, Activity activity
    ,    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber("+972" + phone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    //mcallback method
    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            progressBar.setVisibility(View.GONE);
            btnContinue.setVisibility(View.VISIBLE);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            btnContinue.setVisibility(View.VISIBLE);
            Toast.makeText(SendVCode.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            progressBar.setVisibility(View.GONE);
            btnContinue.setVisibility(View.VISIBLE);
            Intent i = new Intent(SendVCode.this, VerifyVCode.class);
            i.putExtra("phone", etPhoneNumber.getText().toString());
            i.putExtra("verificationId", verificationId);
            startActivity(i);
        }
    };
}

