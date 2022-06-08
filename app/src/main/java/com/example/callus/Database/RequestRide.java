package com.example.callus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserInfo.class,parentColumns = "phone",childColumns = "phone"
        ,onDelete = ForeignKey.CASCADE ,onUpdate = ForeignKey.CASCADE)})
public class RequestRide {
    @PrimaryKey(autoGenerate = true)
    int id ;
    @NonNull
    String currentLocation , whereTo;
    String phone,time,date;
    int price;

    public RequestRide(@NonNull String currentLocation, @NonNull String whereTo, String phone,
                       String time, String date, int price) {
        this.currentLocation = currentLocation;
        this.whereTo = whereTo;
        this.phone = phone;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(@NonNull String currentLocation) {
        this.currentLocation = currentLocation;
    }

    @NonNull
    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(@NonNull String whereTo) {
        this.whereTo = whereTo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
