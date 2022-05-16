package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface MyTripsDAO {
    @Insert
    void insertMyTrips(MyTrips... MyTrips);
    @Update
    void updateMyTrips(MyTrips... MyTrips);
    @Delete
    void deleteMyTrips(MyTrips... MyTrips);
    @Query("select * from MyTrips")
    LiveData<List<MyTrips>> getAllMyTrips();

}
