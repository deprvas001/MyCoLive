package com.development.mycolive.views.model;

public class CurrentBookingModel {
    String srNo;
    String orderNumber;
    String bookingDate;
    String bookingFor;
    String amount;

    public CurrentBookingModel(String srNo, String orderNumber, String bookingDate, String bookingFor, String amount) {
        this.srNo = srNo;
        this.orderNumber = orderNumber;
        this.bookingDate = bookingDate;
        this.bookingFor = bookingFor;
        this.amount = amount;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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
}
