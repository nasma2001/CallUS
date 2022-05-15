package com.example.callus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "userID", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class PaymentMethod {
    @PrimaryKey
    int id;
    @NonNull
    String type;
    @NonNull
    long cardNum;
    @NonNull
    long userID;

    public PaymentMethod(int id, @NonNull String type, long cardNum, long userID) {
        this.id = id;
        this.type = type;
        this.cardNum = cardNum;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public long getCardNum() {
        return cardNum;
    }

    public void setCardNum(long cardNum) {
        this.cardNum = cardNum;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
