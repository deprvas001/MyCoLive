package com.development.mycolive.model.paymentModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PaymentRequestBody implements Parcelable {
    private List<String> room_id;
    private String duration;
    private String payment_method;
    private String contract;
    private String early_check;
    private String receipt;
    private String daterange;


    public PaymentRequestBody(){

    }

    protected PaymentRequestBody(Parcel in) {
        room_id = in.createStringArrayList();
        duration = in.readString();
        payment_method = in.readString();
        contract = in.readString();
        early_check = in.readString();
        receipt = in.readString();
        daterange = in.readString();
    }

    public static final Creator<PaymentRequestBody> CREATOR = new Creator<PaymentRequestBody>() {
        @Override
        public PaymentRequestBody createFromParcel(Parcel in) {
            return new PaymentRequestBody(in);
        }

        @Override
        public PaymentRequestBody[] newArray(int size) {
            return new PaymentRequestBody[size];
        }
    };

    public List<String> getRoom_id() {
        return room_id;
    }

    public void setRoom_id(List<String> room_id) {
        this.room_id = room_id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getEarly_check() {
        return early_check;
    }

    public void setEarly_check(String early_check) {
        this.early_check = early_check;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getDaterange() {
        return daterange;
    }

    public void setDaterange(String daterange) {
        this.daterange = daterange;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(room_id);
        parcel.writeString(duration);
        parcel.writeString(payment_method);
        parcel.writeString(contract);
        parcel.writeString(early_check);
        parcel.writeString(receipt);
        parcel.writeString(daterange);
    }
}
