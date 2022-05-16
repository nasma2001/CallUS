package com.example.callus.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class Repository {
    UserInfoDAO userInfoDAO;
    SavedPlacesModelDAO savedPlacesModelDAO;
    PaymentMethodDOA paymentMethodDOA;
    MyTripsDAO myTripsDAO;

    public Repository(Application application) {
        MyRoomBatabase db = MyRoomBatabase.getDatabase(application);
        userInfoDAO = db.userInfoDAO();
        savedPlacesModelDAO = db.savedPlacesModelDAO();
        paymentMethodDOA = db.paymentMethodDOA();
        myTripsDAO = db.myTripsDAO();
    }

    // UserInfo DAO
    public void insertUserInfoe(UserInfo... userInfo) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userInfoDAO.insertUserInfoe(userInfo);
            }
        });
    }

    public void updateUserInfo(UserInfo... userInfo) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userInfoDAO.updateUserInfo(userInfo);
            }
        });
    }

    public void deleteUserInfo(UserInfo... userInfo) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userInfoDAO.deleteUserInfo(userInfo);
            }
        });
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return userInfoDAO.getAllUserInfo();
    }

    // SavedPlacesModel DAO
    public void insertSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                savedPlacesModelDAO.insertSavedPlacesModel(SavedPlacesModel);
            }
        });
    }

    public void updateSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                savedPlacesModelDAO.updateSavedPlacesModel(SavedPlacesModel);
            }
        });
    }

    public void deleteSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                savedPlacesModelDAO.deleteSavedPlacesModel(SavedPlacesModel);
            }
        });
    }

    public LiveData<List<SavedPlacesModel>> getAllSavedPlacesModel() {
        return savedPlacesModelDAO.getAllSavedPlacesModel();
    }

    // PaymentMethod DOA
    public void insertPaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                paymentMethodDOA.insertPaymentMethod(PaymentMethod);
            }
        });
    }

    public void updatePaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                paymentMethodDOA.updatePaymentMethod(PaymentMethod);
            }
        });
    }

    public void deletePaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                paymentMethodDOA.deletePaymentMethod(PaymentMethod);
            }
        });
    }

    public LiveData<List<PaymentMethod>> getAllPaymentMethod() {
        return paymentMethodDOA.getAllPaymentMethod();

    }

    // MyTripsDAO
    public void insertMyTrips(MyTrips... MyTrips) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myTripsDAO.insertMyTrips(MyTrips);
            }
        });
    }

    public void updateMyTrips(MyTrips... MyTrips) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myTripsDAO.updateMyTrips(MyTrips);
            }
        });
    }

    public void deleteMyTrips(MyTrips... MyTrips) {
        MyRoomBatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myTripsDAO.deleteMyTrips(MyTrips);
            }
        });
    }

    public LiveData<List<MyTrips>> getAllMyTrips() {
        return myTripsDAO.getAllMyTrips();
    }


}
