package com.example.callus.UI.profile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.google.android.gms.wallet.PaymentsClient;

public class AddPayment extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvCredit, tvPaypal, gPay;

    PaymentsClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        //inflate
        inflate();


        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar(getResources().getString(R.string.payment), getSupportActionBar(),
                true,toolbar.findViewById(R.id.toolbar_title));
    }



    private void inflate() {
        toolbar = findViewById(R.id.toolBar);
        tvCredit = findViewById(R.id.creditDebit);
        tvPaypal = findViewById(R.id.tvPayPal);
        gPay = findViewById(R.id.tvGPay);

    }
}