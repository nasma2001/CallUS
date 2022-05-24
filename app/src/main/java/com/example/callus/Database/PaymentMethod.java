package com.example.callus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "phone", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class PaymentMethod {
    @PrimaryKey
    int id;
    @NonNull
    String type;
    @NonNull
    long cardNum;
    @NonNull
    String phone;

    public PaymentMethod(int id, @NonNull String type, long cardNum, String phone) {
        this.id = id;
        this.type = type;
        this.cardNum = cardNum;
        this.phone = phone;
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

    public String getUserID() {
        return phone;
    }

    public void setUserID(String phone) {
        this.phone = phone;
    }
}
