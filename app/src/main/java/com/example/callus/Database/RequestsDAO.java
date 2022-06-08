package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RequestsDAO {
    @Insert
    void insertRequest(Requests... requests);
    @Update
    void updateRequest(Requests... requests);
    @Delete
    void deleteRequest(Requests... requests);
    @Query("select * from Requests")
    LiveData<List<Requests>> getAllRequests();
}
