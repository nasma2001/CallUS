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
public interface UserInfoDAO {
    @Insert
    void insertUserInfoe(UserInfo... userInfo);
    @Update
    void updateUserInfo(UserInfo... userInfo);
    @Delete
    void deleteUserInfo(UserInfo... userInfo);
    @Query("select * from UserInfo")
    LiveData<List<UserInfo>> getAllUserInfo();

}
