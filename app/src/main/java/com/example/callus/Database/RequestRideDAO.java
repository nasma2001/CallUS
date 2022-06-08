package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RequestRideDAO {
    @Insert
    void insertRequest(RequestRide... requests);
    @Update
    void updateRequest(RequestRide... requests);
    @Delete
    void deleteRequest(RequestRide... requests);
    @Query("select * from RequestRide")
    LiveData<List<RequestRide>> getAllRequestRides();
}
