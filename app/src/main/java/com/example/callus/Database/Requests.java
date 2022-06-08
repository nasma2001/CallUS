package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserInfo.class,parentColumns = "phone",childColumns = "phone"
        ,onDelete = ForeignKey.CASCADE ,onUpdate = ForeignKey.CASCADE)})
public class Requests {
    @PrimaryKey(autoGenerate = true)
    int id ;

    String from , to, date,time;
    String phone,way;
    int price;

    public Requests(String from, String to,String way, String phone,String date,String time,int price) {
        this.from = from;
        this.to = to;
        this.phone = phone;
        this.way = way;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
