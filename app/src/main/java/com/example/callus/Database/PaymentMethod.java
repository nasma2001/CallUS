package com.example.callus.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserInfo.class,
        parentColumns = "phone", childColumns = "phone", onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class PaymentMethod {
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    String type;
    @NonNull
    String cardNum;
    @NonNull
    String phone;
    int totalMoney;

    public PaymentMethod( @NonNull String type, @NonNull String cardNum, @NonNull String phone) {
        this.type = type;
        this.cardNum = cardNum;
        this.phone = phone;
        totalMoney = 1000;
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

    @NonNull
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getUserID() {
        return phone;
    }

    public void setUserID(String phone) {
        this.phone = phone;
    }
}
