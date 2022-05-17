package com.example.callus.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserInfo.class , SavedPlacesModel.class , MyTrips.class , PaymentMethod.class}
                    , version = 1, exportSchema = false)
public abstract class MyRoomBatabase extends RoomDatabase {

    public abstract UserInfoDAO userInfoDAO();
    public abstract SavedPlacesModelDAO savedPlacesModelDAO();
    public abstract MyTripsDAO myTripsDAO();
    public abstract PaymentMethodDOA paymentMethodDOA();

    private static volatile MyRoomBatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyRoomBatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomBatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyRoomBatabase.class, "MyRoomBatabase")
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
    // يتم استدعائها عند انشاء DB لاول مرة
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                Log.e("TAG", "Create a RoomDatabase " );

            });
        }
    };

}
