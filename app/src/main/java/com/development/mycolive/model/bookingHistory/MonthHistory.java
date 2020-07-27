package com.development.mycolive.model.bookingHistory;

import com.google.gson.annotations.SerializedName;

public class MonthHistory {
    private String month;
    @SerializedName("nonth_name")
    private String monthName;
    private String status;
    private String payment_method;
    private String receipt;
    private String approval;
    private String payment;
    private String payment_id;
    private String dues_month_id;
    private float dues_amount;
    private int early_check_yn;
    private String early_amount;
    private String no_of_day;



    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getDues_month_id() {
        return dues_month_id;
    }

    public void setDues_month_id(String dues_month_id) {
        this.dues_month_id = dues_month_id;
    }

    public float getDues_amount() {
        return dues_amount;
    }

    public void setDues_amount(float dues_amount) {
        this.dues_amount = dues_amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getEarly_check_yn() {
        return early_check_yn;
    }

    public void setEarly_check_yn(int early_check_yn) {
        this.early_check_yn = early_check_yn;
    }

    public String getEarly_amount() {
        return early_amount;
    }

    public void setEarly_amount(String early_amount) {
        this.early_amount = early_amount;
    }

    public String getNo_of_day() {
        return no_of_day;
    }

    public void setNo_of_day(String no_of_day) {
        this.no_of_day = no_of_day;
    }
}

