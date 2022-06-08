package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity( foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "phone", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = PaymentMethod.class,parentColumns = "id",childColumns = "id"
        ,onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
public class MyTrips {
    @PrimaryKey(autoGenerate = true)
    int id;
    String placeFrom, placeTo,phone,date;
    int paymentID;
    int price;


    public MyTrips(String placeFrom, String placeTo, int paymentID, String phone,int price,String date) {
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.paymentID = paymentID;
        this.phone = phone;
        this.price = price;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(String placeTo) {
        this.placeTo = placeTo;
    }

    public long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getUserID() {
        return phone;
    }

    public void setUserID(String phone) {
        this.phone = phone;
    }
}
