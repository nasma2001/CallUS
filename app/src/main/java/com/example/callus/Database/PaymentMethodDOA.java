package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface PaymentMethodDOA {
    @Insert
    void insertPaymentMethod(PaymentMethod... PaymentMethod);
    @Update
    void updatePaymentMethod(PaymentMethod... PaymentMethod);
    @Delete
    void deletePaymentMethod(PaymentMethod... PaymentMethod);
    @Query("select * from PaymentMethod ")
    LiveData<List<PaymentMethod>> getAllPaymentMethod();
    @Query("select id from PaymentMethod where cardNum = :cardNum")
    int getPaymentIDByCardNumber(String cardNum);
    @Query("select totalMoney from PaymentMethod where cardNum = :cardNum")
    int getMoneyFromCardNumber(String cardNum);
    @Query("update PaymentMethod set totalMoney = :totalMoney where cardNum =:cardNum")
    void updateMoney(String cardNum, int totalMoney);



}
