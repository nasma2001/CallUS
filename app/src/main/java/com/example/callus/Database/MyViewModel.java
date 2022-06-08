package com.example.callus.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertUserInfo(UserInfo... userInfo) {
        repository.insertUserInfo(userInfo);
    }

    public void updateUserInfo(UserInfo... userInfo) {
        repository.updateUserInfo(userInfo);
    }

    public void deleteUserInfo(UserInfo... userInfo) {
        repository.deleteUserInfo(userInfo);
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return repository.getAllUserInfo();
    }


    // SavedPlacesModel DAO
    public void insertSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        repository.insertSavedPlacesModel(SavedPlacesModel);
    }

    public void updateSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        repository.updateSavedPlacesModel(SavedPlacesModel);
    }

    public void deleteSavedPlacesModel(SavedPlacesModel... SavedPlacesModel) {
        repository.deleteSavedPlacesModel(SavedPlacesModel);
    }
    public void deletePlaceById(int id){
        repository.deletePlaceByID(id);
    }
    public LiveData<List<SavedPlacesModel>> getAllSavedPlacesModel() {
        return repository.getAllSavedPlacesModel();
    }

    // PaymentMethod DOA
    public void insertPaymentMethod(PaymentMethod... PaymentMethod) {
        repository.insertPaymentMethod(PaymentMethod);
    }

    public void updatePaymentMethod(PaymentMethod... PaymentMethod) {
        repository.updatePaymentMethod(PaymentMethod);
    }

    public void deletePaymentMethod(PaymentMethod... PaymentMethod) {
        repository.deletePaymentMethod(PaymentMethod);
    }

    public LiveData<List<PaymentMethod>> getAllPaymentMethod() {
        return repository.getAllPaymentMethod();

    }
    public int getPaymentIDByCardNumber(String cardNum){
        return repository.getPaymentIDByCardNumber(cardNum);
    }
    public int getMoneyFromCardNumber(String cardNum){
        return  repository.getMoneyFromCardNumber(cardNum);
    }
    public void updateMoney(String cardNum, int totalMoney){
        repository.updateMoney(cardNum,totalMoney);
    }



    // MyTripsDAO
    public void insertMyTrips(MyTrips... MyTrips) {
        repository.insertMyTrips(MyTrips);
    }

    public void updateMyTrips(MyTrips... MyTrips) {
        repository.updateMyTrips(MyTrips);
    }

    public void deleteMyTrips(MyTrips... MyTrips) {
        repository.deleteMyTrips(MyTrips);
    }

    public LiveData<List<MyTrips>> getAllMyTrips() {
        return repository.getAllMyTrips();
    }

    //Requests
    public void insertRequest(Requests... requests){
        repository.insertRequest(requests);
    }
    public void updateRequest(Requests... requests){
        repository.updateRequest(requests);
    }
    public void deleteRequest(Requests... requests){
        repository.deleteRequest(requests);
    }
    public LiveData<List<Requests>> getAllRequests(){
        return repository.getAllRequest();
    }

    //RequestsRide
    public void insertRequestRide(RequestRide... requests){
        repository.insertRequestRide(requests);
    }
    public void updateRequestRide(RequestRide... requests){
        repository.updateRequestRide(requests);
    }
    public void deleteRequestRide(RequestRide... requests){
        repository.deleteRequestRide(requests);
    }
    public LiveData<List<RequestRide>> getAllRequestRide(){
        return repository.getAllRequestRide();
    }
}
