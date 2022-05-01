package com.example.callus.Models;

import java.io.Serializable;

public class SavedPlaceData implements Serializable {

    String txtView1;
    String txtView2;


    public SavedPlaceData(String txtview1, String txtview2) {
        this.txtView1 = txtview1;
        this.txtView2 = txtview2;
    }

    public SavedPlaceData() {
    }

    public String getTxtView1() {
        return txtView1;
    }

    public void setTxtView1(String txtView1) {
        this.txtView1 = txtView1;
    }

    public String getTxtView2() {
        return txtView2;
    }

    public void setTxtView2(String txtView2) {
        this.txtView2 = txtView2;
    }

}
