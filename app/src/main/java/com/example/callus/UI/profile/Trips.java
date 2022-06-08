package com.example.callus.UI.profile;

import static com.example.callus.ReusableFunctions.ReusableFunctions.actionBar;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callus.Adapters.TripsAdapter;
import com.example.callus.Database.MyTrips;
import com.example.callus.Database.MyViewModel;
import com.example.callus.R;

import java.util.List;

public class Trips extends AppCompatActivity {
    RecyclerView rvTrips;
    Toolbar toolbar;

    MyViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        //inflate
        inflate();

        getAllTrips();

        setSupportActionBar(toolbar);
        actionBar(getResources().getString(R.string.myTrips), getSupportActionBar(), true,
                toolbar.findViewById(R.id.toolbar_title));
    }

    private void getAllTrips() {
        //the second input is new observer
        model.getAllMyTrips().observe(this, this::prepareRecycler);
    }
    private void prepareRecycler(List<MyTrips> trips) {
        TripsAdapter adapter = new TripsAdapter(this, trips, model);
        rvTrips.setAdapter(adapter);

        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        rvTrips.setLayoutManager(l);
    }

    private void inflate() {
        rvTrips = findViewById(R.id.rvTrips);
        toolbar = findViewById(R.id.toolBar);
        model = new ViewModelProvider(this).get(MyViewModel.class);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}