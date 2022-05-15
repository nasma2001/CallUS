package com.example.callus.ReusableFunctions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReusableFunctions {

    public static void actionBar(String title , ActionBar actionBar,boolean backIsEnabled,TextView textView){
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(backIsEnabled);
            textView.setText(title);
            actionBar.setElevation(4);
        }
    }
    public static String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("HH:mm");
        return mdFormat.format(Calendar.getInstance().getTime());
    }
    public static String makeDateString(int day, int month, int year) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("MMMM-d");
        Date d = new Date(year, month, day);
        return mdFormat.format(d);
    }
    public static String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdFormat = new SimpleDateFormat("MMMM-d");
        Date d = Calendar.getInstance().getTime();
        return mdFormat.format(d);
    }
    public static void popDatePicker(TextView textView, Context context) {
        DatePickerDialog.OnDateSetListener listener =
                (datePicker, year, month, day) -> textView.setText(makeDateString(day, month, year));

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public static void popTimePicker(TextView textView, Context context) {
        final int[] hour = {0};
        final int[] min = {0};
        TimePickerDialog.OnTimeSetListener listener = (timePicker, hours, minutes) -> {
            String amPm;
            hour[0] =hours;
            min[0] = minutes;
            if (hours < 12)
                amPm = "AM";
            else {
                amPm = "PM";
                hours -= 12;
            }
            String time = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes) + " " + amPm;
            textView.setText(time);
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(context
                , listener, hour[0], min[0], false);
        timePickerDialog.show();
    }

    public static String[] getAddressFromLatLng(LatLng latLng , Activity activity){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());
        String result=null;
        String knownName = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            result = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return new String[]{result,knownName} ;
    }


}
