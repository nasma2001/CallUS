package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface SavedPlacesModelDAO {
    @Insert
    void insertSavedPlacesModel(SavedPlacesModel... SavedPlacesModel);
    @Update
    void updateSavedPlacesModel(SavedPlacesModel... SavedPlacesModel);
    @Delete
    void deleteSavedPlacesModel(SavedPlacesModel... SavedPlacesModel);
    @Query("select * from `Saved Places`")
    LiveData<List<SavedPlacesModel>> getAllSavedPlacesModel();
    @Query("delete from `Saved Places` where id = :id")
    void deletePlaceById(int id);


}
