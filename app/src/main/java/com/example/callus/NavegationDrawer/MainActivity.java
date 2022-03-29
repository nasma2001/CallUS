package com.example.callus.NavegationDrawer;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.callus.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);

        //جربت هاي الطريقة برضو مزبطتش
//        navigationView.setNavigationItemSelectedListener(menuItem -> {

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id=menuItem.getItemId();
            Fragment fragment= null;
            switch (id)
            {
                case R.id.nav_Nearby:
                    fragment=new nearlyFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_Scan_QR_code:
                    fragment=new scan_QR_codeFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_My_Profile:
                    fragment=new My_ProfileFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_My_Location:
                    fragment=new MY_LocationFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_booking:
                    fragment=new BookingFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_Services:
                    fragment=new ServicesFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_contact_Us:
                    fragment=new contact_UsFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_About_Us:
                    fragment=new About_UsFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_Setting:
                    fragment=new SettingFragment();
                    loadFragment(fragment);
                    break;
                case R.id.nav_Log_Out:
                    fragment=new Log_OutFragment();
                    loadFragment(fragment);
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
        fragmentTransaction.addToBackStack(null);
    }

    private class contact_UsFragment extends Fragment {
    }
}

