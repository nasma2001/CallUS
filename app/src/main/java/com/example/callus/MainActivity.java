package com.example.callus;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;

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

        bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setOnItemSelectedListener(bottomNav);
        //nav drawer
        navDrawer();
        //check if the first time installing the app
        checkIfTheFirstTime();

        //changing the color of the action bar
        ReusableFunctions.actionBar("Call US",getSupportActionBar());


    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener bottomNav= item -> {
        switch (item.getItemId()){
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

    @SuppressLint("NonConstantResourceId")
    private void navDrawer(){
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id=menuItem.getItemId();
            Fragment fragment= null;
            switch (id)
            {
                case R.id.nav_Nearby:
                    loadFragment(new NearbyFragment());
                    break;
                case R.id.nav_Scan_QR_code:
                    loadFragment(new scan_QR_codeFragment());
                    break;
                case R.id.nav_My_Profile:
                    loadFragment(new My_ProfileFragment());
                    break;
                case R.id.nav_My_Location:
                    loadFragment(new MY_LocationFragment());
                    break;
                case R.id.nav_booking:
                    loadFragment(new BookingFragment());
                    break;
                case R.id.nav_Services:
                    loadFragment(new ServicesFragment());
                    break;
                case R.id.nav_contact_Us:
                    loadFragment(new contact_UsFragment());
                    break;
                case R.id.nav_About_Us:
                    loadFragment(new About_UsFragment());
                    break;
                case R.id.nav_Setting:
                    loadFragment(new SettingFragment());
                    break;
                case R.id.nav_Log_Out:
                    loadFragment(new Log_OutFragment());
                    break;
                default:
                    return true;
            }
            return true;
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void checkIfTheFirstTime(){
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun)
            startActivity(new Intent(MainActivity.this, SendVCode.class));

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
    }
}