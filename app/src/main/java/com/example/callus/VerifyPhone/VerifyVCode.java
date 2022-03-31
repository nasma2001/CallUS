package com.example.callus.VerifyPhone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callus.MainActivity;
import com.example.callus.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyVCode extends AppCompatActivity {
    private EditText code1,code2,code3,code4,code5,code6;
    private TextView number;
    Button btnVerify;
    ProgressBar progressBar;
    String verificationId;
    TextView resend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);
        //inflate views
        inflate();

        //phone appearance
        number.setText(String.format("+972-%s",getIntent().getStringExtra("phone")));
        verificationId = getIntent().getStringExtra("verificationId");

        addVerificationInputs();

        //when verify button clicked
        btnVerify.setOnClickListener(view -> {
            if(code1.getText().toString().isEmpty()||code2.getText().toString().isEmpty()||
                    code3.getText().toString().isEmpty()||code4.getText().toString().isEmpty()||
            code5.getText().toString().isEmpty()||code6.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter valid code", Toast.LENGTH_SHORT).show();
            }
            if(verificationId !=null){
               progressBar.setVisibility(View.VISIBLE);
               btnVerify.setVisibility(View.INVISIBLE);
               verifyCode(code1.getText().toString()+
                         code2.getText().toString()+
                         code3.getText().toString()+
                         code4.getText().toString()+
                         code5.getText().toString()+
                         code6.getText().toString());
            }
       });

        //when resend tv is clicked
        resend.setOnClickListener(view -> {
            String phone = getIntent().getStringExtra("phone");
            new SendVCode().firebasePhoneNumAuth(phone,VerifyVCode.this,mmCallbacks);
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();


    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mmCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(VerifyVCode.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String newVerificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            verificationId = newVerificationId;
            Toast.makeText(VerifyVCode.this, "code sent", Toast.LENGTH_SHORT).show();
        }
    };
    private void verifyCode(String code){
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);
        signINByCredential(phoneAuthCredential);
    }
    private void signINByCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(phoneAuthCredential).
                addOnCompleteListener(task -> {

                    progressBar.setVisibility(View.GONE);
                    btnVerify.setVisibility(View.VISIBLE);

                    if (task.isSuccessful()){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                .edit().putBoolean("hasVerified",true).apply();
                        Intent i = new Intent(this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(this, "Invalid verification code", Toast.LENGTH_SHORT).show();
                });
    }

    private void inflate(){
        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        code5 = findViewById(R.id.code5);
        code6 = findViewById(R.id.code6);
        number = findViewById(R.id.num);
        resend = findViewById(R.id.tvResend);
        progressBar=findViewById(R.id.pbVerify);
        btnVerify = findViewById(R.id.btnVerifyPhone);
    }
    private void addVerificationInputs(){
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                    code2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                    code3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                    code4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                    code5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty())
                    code6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}