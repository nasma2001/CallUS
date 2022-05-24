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



}
