package com.example.callus.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserInfoDAO {
    @Insert
    void insertUserInfo(UserInfo... userInfo);
    @Update
    void updateUserInfo(UserInfo... userInfo);
    @Delete
    void deleteUserInfo(UserInfo... userInfo);
    @Query("select * from UserInfo")
    LiveData<List<UserInfo>> getAllUserInfo();

}
