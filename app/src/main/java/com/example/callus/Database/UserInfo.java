package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class UserInfo {
    @PrimaryKey
    long phone;
    Date dateJoined;
    String address;

    public UserInfo(int phone, String address,Date dateJoined) {
        this.phone = phone;
        this.address = address;
        this.dateJoined = dateJoined;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
