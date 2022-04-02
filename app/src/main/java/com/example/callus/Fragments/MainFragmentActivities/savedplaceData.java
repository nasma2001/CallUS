package com.example.callus.Fragments.MainFragmentActivities;

import java.io.Serializable;

public class savedplaceData implements Serializable {

    String txtview1;
    String txtview2;

    public savedplaceData(String txtview1, String txtview2) {
        this.txtview1 = txtview1;
        this.txtview2 = txtview2;
    }

    public savedplaceData() {
    }

    public String getTxtview1() {
        return txtview1;
    }

    public void setTxtview1(String txtview1) {
        this.txtview1 = txtview1;
    }

    public String getTxtview2() {
        return txtview2;
    }

    public void setTxtview2(String txtview2) {
        this.txtview2 = txtview2;
    }

    @Override
    public String toString() {
        return "savedplaceData{" +
                "txtview1='" + txtview1 + '\'' +
                ", txtview2='" + txtview2 + '\'' +
                '}';
    }
}
