package com.example.callus.ReusableFunctions;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;

import androidx.appcompat.app.ActionBar;

public class ReusableFunctions {

    public static void actionBar(String title , ActionBar actionBar){
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#3d6d79"));
        if (actionBar != null) {
            actionBar.setTitle(Html.fromHtml("<font color='#e0e2ee'>" +title+ "</font>"));
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }
}
