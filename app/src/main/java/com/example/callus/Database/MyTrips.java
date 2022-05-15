package com.example.callus.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity( foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "userID", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = PaymentMethod.class,parentColumns = "id",childColumns = "id"
        ,onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
public class MyTrips {
    int id;
    String placeFrom, placeTo;
    long paymentID,userID;

    public MyTrips(String placeFrom, String placeTo, long paymentID, long userID) {
        this.placeFrom = placeFrom;
        this.placeTo = placeTo;
        this.paymentID = paymentID;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPaymentID(long paymentID) {
        this.paymentID = paymentID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
