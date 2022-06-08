package com.example.callus.UI.profile;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.callus.Database.MyViewModel;
import com.example.callus.Database.PaymentMethod;
import com.example.callus.R;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Wallet extends AppCompatActivity {
    TextView tvAddPaymentMethod,tvMoney;
    Toolbar toolbar;

    MyViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        //inflate
        inflate();

        if(model.getMoneyFromCardNumber("4263982640269299")!=0) {
            tvMoney.setText(String.valueOf(model.getMoneyFromCardNumber("4263982640269299")));
        }
        //initiate Listeners
        initListeners();

        setSupportActionBar(toolbar);
        ReusableFunctions.actionBar(getResources().getString(R.string.wallet), getSupportActionBar(),
                true,toolbar.findViewById(R.id.toolbar_title));
    }

    private void initListeners() {
//        tvAddPaymentMethod.setOnClickListener(view -> startActivity(new Intent(this,AddPayment.class)));
        tvAddPaymentMethod.setOnClickListener(view -> {
            PaymentMethod method = new PaymentMethod("Visa","4263982640269299",
                    Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
            Toast.makeText(this, "payment method Added", Toast.LENGTH_SHORT).show();
            model.insertPaymentMethod(method);
            tvMoney.setText(String.valueOf(model.getMoneyFromCardNumber("4263982640269299")));
        });

    }

    private void inflate() {
        toolbar = findViewById(R.id.toolBar);
        tvAddPaymentMethod = findViewById(R.id.tvAddPayment);
        tvMoney  =findViewById(R.id.tvMoney);
        model = new ViewModelProvider(this).get(MyViewModel.class);
    }
}