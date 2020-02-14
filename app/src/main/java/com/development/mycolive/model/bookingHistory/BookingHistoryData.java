package com.development.mycolive.model.bookingHistory;

import java.util.List;

public class BookingHistoryData {
    private int sr_no;
    private String id;
    private String booking_for;
    private String amount;
    private String booking_date;
    private String apartment_name;
    private String room_name;
    private String post_code;
    private String address;
    private String near_by_area;
    private String id_proof;
    private List<MonthHistory> month;

    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBooking_for() {
        return booking_for;
    }

    public void setBooking_for(String booking_for) {
        this.booking_for = booking_for;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getApartment_name() {
        return apartment_name;
    }

    public void setApartment_name(String apartment_name) {
        this.apartment_name = apartment_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNear_by_area() {
        return near_by_area;
    }

    public void setNear_by_area(String near_by_area) {
        this.near_by_area = near_by_area;
    }

    public String getId_proof() {
        return id_proof;
    }

    public void setId_proof(String id_proof) {
        this.id_proof = id_proof;
    }

    public List<MonthHistory> getMonth() {
        return month;
    }

    public void setMonth(List<MonthHistory> month) {
        this.month = month;
    }
}
