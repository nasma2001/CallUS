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
import android.content.Intent;
import android.os.Bundle;

import com.example.callus.Fragments.Departures;
import com.example.callus.Fragments.MainFragment;
import com.example.callus.NavigationDrawerFragments.About_UsFragment;
import com.example.callus.NavigationDrawerFragments.BookingFragment;
import com.example.callus.NavigationDrawerFragments.Log_OutFragment;
import com.example.callus.NavigationDrawerFragments.MY_LocationFragment;
import com.example.callus.NavigationDrawerFragments.My_ProfileFragment;
import com.example.callus.NavigationDrawerFragments.ServicesFragment;
import com.example.callus.NavigationDrawerFragments.SettingFragment;
import com.example.callus.NavigationDrawerFragments.contact_UsFragment;
import com.example.callus.NavigationDrawerFragments.NearbyFragment;
import com.example.callus.NavigationDrawerFragments.scan_QR_codeFragment;
import com.example.callus.ReusableFunctions.ReusableFunctions;
import com.example.callus.VerifyPhone.SendVCode;
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
        //check if the first time installing the app
        checkIfTheFirstTime();
        //load the main fragment first
        loadFragment(new MainFragment());
        //inflate
        inflate();
        //nav drawer
        navDrawer();

        //changing the color of the action bar
        ReusableFunctions.actionBar("Call US",getSupportActionBar());


    }
    private void inflate(){
        bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(bottomNav);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
    }
    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener bottomNav= item -> {
        switch (item.getItemId()){
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
    private void navDrawer(){
        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(toggle);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId())
            {
                case R.id.nav_Nearby:
                    loadFragmentFromNav(new NearbyFragment());
                    break;
                case R.id.nav_Scan_QR_code:
                    loadFragmentFromNav(new scan_QR_codeFragment());
                    break;
                case R.id.nav_My_Profile:
                    loadFragmentFromNav(new My_ProfileFragment());
                    break;
                case R.id.nav_My_Location:
                    loadFragmentFromNav(new MY_LocationFragment());
                    break;
                case R.id.nav_booking:
                    loadFragmentFromNav(new BookingFragment());
                    break;
                case R.id.nav_Services:
                    loadFragmentFromNav(new ServicesFragment());
                    break;
                case R.id.nav_contact_Us:
                    loadFragmentFromNav(new contact_UsFragment());
                    break;
                case R.id.nav_About_Us:
                    loadFragmentFromNav(new About_UsFragment());
                    break;
                case R.id.nav_Setting:
                    loadFragmentFromNav(new SettingFragment());
                    break;
                case R.id.nav_Log_Out:
                    loadFragmentFromNav(new Log_OutFragment());
                    break;
                default:
                    return true;
            }
            return true;
        });
    }
    private void loadFragmentFromNav(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
    }
    private void checkIfTheFirstTime(){
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun)
            startActivity(new Intent(this, SendVCode.class));
        else
            startActivity(new Intent(this, MainActivity.class));

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
    }

}