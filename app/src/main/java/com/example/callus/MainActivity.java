package com.example.callus;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.callus.Fragments.Departures;
import com.example.callus.Fragments.MainFragment;
import com.example.callus.NavigationDrawerFragments.BookingFragment;
import com.example.callus.NavigationDrawerFragments.LogOutFragment;
import com.example.callus.NavigationDrawerFragments.MyProfileFragment;
import com.example.callus.NavigationDrawerFragments.SettingFragment;
import com.example.callus.NavigationDrawerFragments.ContactUsFragment;
import com.example.callus.Fragments.NearbyFragment;
import com.example.callus.NavigationDrawerFragments.ScanQRFragment;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load the main fragment first
        loadFragment(new MainFragment());
        //inflate
        inflate();
        //nav drawer
        navDrawer();

        //changing the color of the action bar
        ReusableFunctions.actionBar("Call US", getSupportActionBar());


    }

    private void inflate() {
        bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(bottomNav);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener bottomNav = item -> {
        switch (item.getItemId()) {
            case R.id.home:
                loadFragmentFromNav(new MainFragment());
                break;
            case R.id.nearby:
                loadFragmentFromNav(new NearbyFragment());
                break;
            case R.id.depart:
                loadFragmentFromNav(new Departures());
                break;
        }
        return true;
    };

    @SuppressLint("NonConstantResourceId")
    private void navDrawer() {
        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_Nearby:
                    loadFragmentFromNav(new NearbyFragment());
                    break;
                case R.id.nav_Scan_QR_code:
                    loadFragmentFromNav(new ScanQRFragment());
                    break;
                case R.id.nav_My_Profile:
                    loadFragmentFromNav(new MyProfileFragment());
                    break;
                case R.id.nav_booking:
                    loadFragmentFromNav(new BookingFragment());
                    break;
                case R.id.nav_contact_Us:
                    loadFragmentFromNav(new ContactUsFragment());
                    break;
                case R.id.nav_Setting:
                    loadFragmentFromNav(new SettingFragment());
                    break;
                case R.id.nav_Log_Out:
                    loadFragmentFromNav(new LogOutFragment());
                    break;
                default:
                    return true;
            }
            return true;
        });
    }

    private void loadFragmentFromNav(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
    }


}