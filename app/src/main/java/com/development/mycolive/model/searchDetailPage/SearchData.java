package com.development.mycolive.model.searchDetailPage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchData {
    @SerializedName("selected_room_id")
    private String selectedRoomId;
    @SerializedName("apartment")
    List<ApartmentData> apartmentDataList;
    @SerializedName("rooms")
    List<ApartmentData> roomDataList;
    @SerializedName("bank_account")
    private BankAccount bankAccount;

    public BankAccount getBankAccount() {

        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getSelectedRoomId() {
        return selectedRoomId;
    }

    public void setSelectedRoomId(String selectedRoomId) {
        this.selectedRoomId = selectedRoomId;
    }

    public List<ApartmentData> getApartmentDataList() {
        return apartmentDataList;
    }

    public void setApartmentDataList(List<ApartmentData> apartmentDataList) {
        this.apartmentDataList = apartmentDataList;
    }

    public List<ApartmentData> getRoomDataList() {
        return roomDataList;
    }

    public void setRoomDataList(List<ApartmentData> roomDataList) {
        this.roomDataList = roomDataList;
    }
}
