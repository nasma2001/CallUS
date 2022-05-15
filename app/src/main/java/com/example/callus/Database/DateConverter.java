package com.example.callus.Database;

import androidx.room.TypeConverter;

import java.sql.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromLongToDate(Long time){
        return time == null?null:new Date(time);
    }
    @TypeConverter
    public static Long fromDateToLong(Date date){
        return date == null?null:date.getTime();
    }
}
