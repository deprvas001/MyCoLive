package com.development.mycolive.model.termscondition;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ContractResponse implements Parcelable {
    private float  early_checkin_rent;
    private int early_checkin_available;
    private int is_available;
    private float pay_amount_this_month;
    private float monthly_amount;
    private float security_amount;
    private List<String> room_id;
    private String early_check_date;
    private String duration;
    private String contractDetails;
    private String msg;

    protected ContractResponse(Parcel in) {
        early_checkin_rent = in.readFloat();
        early_checkin_available = in.readInt();
        is_available = in.readInt();
        pay_amount_this_month = in.readFloat();
        monthly_amount = in.readFloat();
        security_amount = in.readFloat();
        room_id = in.createStringArrayList();
        early_check_date = in.readString();
        duration = in.readString();
        contractDetails = in.readString();
        msg = in.readString();
    }

    public static final Creator<ContractResponse> CREATOR = new Creator<ContractResponse>() {
        @Override
        public ContractResponse createFromParcel(Parcel in) {
            return new ContractResponse(in);
        }

        @Override
        public ContractResponse[] newArray(int size) {
            return new ContractResponse[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public float getEarly_checkin_rent() {
        return early_checkin_rent;
    }

    public void setEarly_checkin_rent(float early_checkin_rent) {
        this.early_checkin_rent = early_checkin_rent;
    }

    public int getEarly_checkin_available() {
        return early_checkin_available;
    }

    public void setEarly_checkin_available(int early_checkin_available) {
        this.early_checkin_available = early_checkin_available;
    }

    public int getIs_available() {
        return is_available;
    }

    public void setIs_available(int is_available) {
        this.is_available = is_available;
    }

    public float getPay_amount_this_month() {
        return pay_amount_this_month;
    }

    public void setPay_amount_this_month(float pay_amount_this_month) {
        this.pay_amount_this_month = pay_amount_this_month;
    }

    public float getMonthly_amount() {
        return monthly_amount;
    }

    public void setMonthly_amount(float monthly_amount) {
        this.monthly_amount = monthly_amount;
    }

    public float getSecurity_amount() {
        return security_amount;
    }

    public void setSecurity_amount(float security_amount) {
        this.security_amount = security_amount;
    }

    public List<String> getRoom_id() {
        return room_id;
    }

    public void setRoom_id(List<String> room_id) {
        this.room_id = room_id;
    }

    public String getEarly_check_date() {
        return early_check_date;
    }

    public void setEarly_check_date(String early_check_date) {
        this.early_check_date = early_check_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(String contractDetails) {
        this.contractDetails = contractDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(early_checkin_rent);
        parcel.writeInt(early_checkin_available);
        parcel.writeInt(is_available);
        parcel.writeFloat(pay_amount_this_month);
        parcel.writeFloat(monthly_amount);
        parcel.writeFloat(security_amount);
        parcel.writeStringList(room_id);
        parcel.writeString(early_check_date);
        parcel.writeString(duration);
        parcel.writeString(contractDetails);
        parcel.writeString(msg);
    }
}
