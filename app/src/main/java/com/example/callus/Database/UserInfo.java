package com.example.callus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class UserInfo {
    @PrimaryKey
    @NonNull
    String phone;
    long dateJoined;

    public UserInfo(@NonNull String phone) {
        this.phone = phone;
        this.dateJoined = new Date().getTime();
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public long getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(long dateJoined) {
        this.dateJoined = dateJoined;
    }

}
