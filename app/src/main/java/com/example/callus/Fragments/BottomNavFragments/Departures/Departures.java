package com.example.callus.Fragments.BottomNavFragments.Departures;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.callus.Fragments.BottomNavFragments.Departures.ForMap.ChooseYourLocation;
import com.example.callus.R;

import java.util.Locale;

public class Departures extends Fragment {
    EditText etCurrentLocation;
    Button btnSearch;
    TextView tvTime;
    int hour, min;

    public Departures() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_departures, container, false);

        // inflate views
        inflate(v);

        //initialize listeners
        listeners();
        return v;
    }

    private void listeners() {
        etCurrentLocation.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), ChooseYourLocation.class));
        });

        tvTime.setOnClickListener(view -> {
            popTimePicker();
        });
    }
    private void popTimePicker(){
        TimePickerDialog.OnTimeSetListener listener = (timePicker, hours, minutes) -> {
            hour = hours;
            min = minutes;
            tvTime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,min));
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext()
                ,listener,hour,min,false);
        timePickerDialog.show();

    }

    private void inflate(View v) {
        etCurrentLocation = v.findViewById(R.id.etCurrentLocation);
        btnSearch = v.findViewById(R.id.btnSearch);
        tvTime = v.findViewById(R.id.tvTimeDeparting);
    }
}