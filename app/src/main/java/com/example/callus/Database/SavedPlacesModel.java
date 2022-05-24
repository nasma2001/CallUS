package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Saved Places", foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "phone", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class SavedPlacesModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String city, street, home, phone;


    public SavedPlacesModel(String city, String street, String home, String phone) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
