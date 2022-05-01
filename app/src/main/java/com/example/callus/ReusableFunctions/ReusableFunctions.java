package com.example.callus.ReusableFunctions;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;

public class ReusableFunctions {

    public static void actionBar(String title , ActionBar actionBar){
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public static boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission: PERMISSIONS){

                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

}
