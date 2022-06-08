package com.example.callus.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    UserInfoDAO userInfoDAO;
    SavedPlacesModelDAO savedPlacesModelDAO;
    PaymentMethodDOA paymentMethodDOA;
    MyTripsDAO myTripsDAO;
    RequestsDAO requestsDAO;
    RequestRideDAO requestRideDAO;

    public Repository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        userInfoDAO = db.userInfoDAO();
        savedPlacesModelDAO = db.savedPlacesModelDAO();
        paymentMethodDOA = db.paymentMethodDOA();
        myTripsDAO = db.myTripsDAO();
        requestsDAO = db.requestsDAO();
        requestRideDAO = db.requestRideDAO();
    }

    // UserInfo DAO
    public void insertUserInfo(UserInfo... userInfo) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> userInfoDAO.insertUserInfo(userInfo));
    }

    public void updateUserInfo(UserInfo... userInfo) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> userInfoDAO.updateUserInfo(userInfo));
    }

    public void deleteUserInfo(UserInfo... userInfo) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> userInfoDAO.deleteUserInfo(userInfo));
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return userInfoDAO.getAllUserInfo();
    }

    // SavedPlacesModel DAO
    public void insertSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> savedPlacesModelDAO.insertSavedPlacesModel(SavedPlacesModel));
    }

    public void updateSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> savedPlacesModelDAO.updateSavedPlacesModel(SavedPlacesModel));
    }

    public void deleteSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> savedPlacesModelDAO.deleteSavedPlacesModel(SavedPlacesModel));
    }

    public LiveData<List<SavedPlacesModel>> getAllSavedPlacesModel() {
        return savedPlacesModelDAO.getAllSavedPlacesModel();
    }
    public void deletePlaceByID(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(()-> savedPlacesModelDAO.deletePlaceById(id));
    }

    // PaymentMethod DOA
    public void insertPaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> paymentMethodDOA.insertPaymentMethod(PaymentMethod));
    }

    public void updatePaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> paymentMethodDOA.updatePaymentMethod(PaymentMethod));
    }

    public void deletePaymentMethod(PaymentMethod... PaymentMethod) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> paymentMethodDOA.deletePaymentMethod(PaymentMethod));
    }

    public LiveData<List<PaymentMethod>> getAllPaymentMethod() {
        return paymentMethodDOA.getAllPaymentMethod();

    }
    public int getPaymentIDByCardNumber(String cardNum){
        return paymentMethodDOA.getPaymentIDByCardNumber(cardNum);
    }
    public int getMoneyFromCardNumber(String cardNum){
        return paymentMethodDOA.getMoneyFromCardNumber(cardNum);
    }
    public void updateMoney(String cardNum, int totalMoney){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> paymentMethodDOA.updateMoney(cardNum,totalMoney));
    }



    // MyTripsDAO
    public void insertMyTrips(MyTrips... MyTrips) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> myTripsDAO.insertMyTrips(MyTrips));
    }

    public void updateMyTrips(MyTrips... MyTrips) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> myTripsDAO.updateMyTrips(MyTrips));
    }

    public void deleteMyTrips(MyTrips... MyTrips) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> myTripsDAO.deleteMyTrips(MyTrips));
    }

    public LiveData<List<MyTrips>> getAllMyTrips() {
        return myTripsDAO.getAllMyTrips();
    }

    //requestsDao

    public void insertRequest(Requests... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestsDAO.insertRequest(requests));
    }
    public void updateRequest(Requests... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestsDAO.updateRequest(requests));

    }
    public void deleteRequest(Requests... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestsDAO.deleteRequest(requests));

    }
    public LiveData<List<Requests>> getAllRequest(){return requestsDAO.getAllRequests();}

    //requestRideDAO

    public void insertRequestRide(RequestRide... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestRideDAO.insertRequest(requests));
    }
    public void updateRequestRide(RequestRide... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestRideDAO.updateRequest(requests));

    }
    public void deleteRequestRide(RequestRide... requests){
        MyRoomDatabase.databaseWriteExecutor.execute(()->requestRideDAO.deleteRequest(requests));

    }
    public LiveData<List<RequestRide>> getAllRequestRide(){return requestRideDAO.getAllRequestRides();}
}
