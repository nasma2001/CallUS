package com.example.callus.ReusableFunctions;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;

import androidx.appcompat.app.ActionBar;

public class ReusableFunctions {

    public static void actionBar(String title , ActionBar actionBar){
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
