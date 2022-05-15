package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Saved Places", foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "userID", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class SavedPlacesModel {

    private String city, street, home;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private long userID;

    public SavedPlacesModel(String city, String street, String home,long userID) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.userID = userID;
    }

    public SavedPlacesModel() {
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

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
