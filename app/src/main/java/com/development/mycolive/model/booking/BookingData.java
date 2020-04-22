package com.development.mycolive.model.booking;

import com.google.gson.annotations.SerializedName;

public class BookingData {
    @SerializedName("sr_no")
   private int srNo;
    private String id;
    @SerializedName("booking_for")
    private String bookingFor;
    private String amount;
    @SerializedName("booking_date")
    private String bookingDate;

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingFor() {
        return bookingFor;
    }

    public void setBookingFor(String bookingFor) {
        this.bookingFor = bookingFor;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
