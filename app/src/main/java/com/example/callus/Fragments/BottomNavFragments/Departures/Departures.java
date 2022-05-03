package com.example.callus.Fragments.BottomNavFragments.Departures;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Departures extends Fragment {
    EditText etCurrentLocation;
    Button btnSearch;
    TextView tvTime, tvBtmSheetDate, tvBtmSheetTime;
    BottomSheetDialog sheetDialog;
    View bottomSheet;
    Button btnSetTime;
    int hour, min, day, year, month;
//    String month;

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
        etCurrentLocation.setOnClickListener(view -> startActivity(new Intent(getActivity(), ChooseYourLocation.class)));

        tvTime.setOnClickListener(view -> showBottomSheet());
    }

    private String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("HH:mm");
        return mdFormat.format(Calendar.getInstance().getTime());
    }

    public String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("MMMM-d");
        Date d = Calendar.getInstance().getTime();
        return mdFormat.format(d);
    }

    private void showBottomSheet() {
        inflateForBottomSheet();

        tvBtmSheetTime.setText(getCurrentTime());
        tvBtmSheetDate.setText(getCurrentDate());

        tvBtmSheetDate.setOnClickListener(view -> popDatePicker());

        tvBtmSheetTime.setOnClickListener(view -> popTimePicker());

        btnSetTime.setOnClickListener(view -> {
            String date = tvBtmSheetDate.getText()+",  at "+tvBtmSheetTime.getText();
            tvTime.setText(date);
            sheetDialog.dismiss();
        });

        sheetDialog.setContentView(bottomSheet);
        sheetDialog.show();
    }

    private void popDatePicker() {
        DatePickerDialog.OnDateSetListener listener = (datePicker, year, month, day) -> {
            this.year = year;
            this.month = month + 1;
            this.day = day;
            tvBtmSheetDate.setText(makeDateString(day, month, year));
        };
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), listener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String makeDateString(int day, int month, int year) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("MMMM-d");
        Date d = new Date(year, month, day);
        return mdFormat.format(d);
    }

    private void popTimePicker() {
        TimePickerDialog.OnTimeSetListener listener = (timePicker, hours, minutes) -> {
            String amPm;
            hour = hours;
            min = minutes;
            if (hours <12)
                amPm = "AM";
            else {
                amPm = "PM";
                hour-=12;
            }
            String time = String.format(Locale.getDefault(), "%02d:%02d", hour, min)+" "+amPm;
            tvBtmSheetTime.setText(time);
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext()
                , listener, hour, min, false);
        timePickerDialog.show();
    }

    private void inflate(View v) {
        etCurrentLocation = v.findViewById(R.id.etCurrentLocation);
        btnSearch = v.findViewById(R.id.btnSearch);
        tvTime = v.findViewById(R.id.tvTimeDeparting);
    }

    private void inflateForBottomSheet() {
        bottomSheet = LayoutInflater.from(getContext())
                .inflate(R.layout.bottom_sheet_departures, getActivity()
                        .findViewById(R.id.bottomSheetContainerDepartures));

        sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);

        tvBtmSheetDate = bottomSheet.findViewById(R.id.tvDate);
        tvBtmSheetTime = bottomSheet.findViewById(R.id.tvTimeBottomSheet);
        btnSetTime = bottomSheet.findViewById(R.id.btnSetTime);
    }
}