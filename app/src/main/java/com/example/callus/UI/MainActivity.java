package com.example.callus.UI;

import static com.example.callus.ReusableFunctions.ReusableFunctions.actionBar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.callus.Database.MyViewModel;
import com.example.callus.Fragments.BottomNavFragments.Departures.Departures;
import com.example.callus.Fragments.BottomNavFragments.MainFragment;
import com.example.callus.Fragments.BottomNavFragments.NearbyFragment;
import com.example.callus.ProfileInfo;
import com.example.callus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    //views
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    TextView appbarTitle;
    ImageView profile;



    @Override
    protected void onStart() {
        super.onStart();

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load the main fragment first
        loadFragment(new MainFragment());
        //inflate
        inflate();

        profile.setImageDrawable(getDrawable(R.drawable.ic_user_profile));
        setSupportActionBar(toolbar);
        actionBar(getResources().getString(R.string.app_name),getSupportActionBar()
                ,false,appbarTitle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        //initiate listeners
        initListeners();

    }

    private void initListeners() {
        profile.setOnClickListener(view -> {
            startActivity(new Intent(this, ProfileInfo.class));
        });
    }

    private void inflate() {
        bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(bottomNav);
        toolbar = findViewById(R.id.toolBar);
        appbarTitle = toolbar.findViewById(R.id.toolbar_title);
        profile = toolbar.findViewById(R.id.ivProfile);


    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener bottomNav = item -> {
        switch (item.getItemId()) {
            case R.id.home:
                loadFragment(new MainFragment());
                break;
            case R.id.nearby:
                loadFragment(new NearbyFragment());
                break;
            case R.id.depart:
                loadFragment(new Departures());
                break;
        }
        return true;
    };

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }




}
