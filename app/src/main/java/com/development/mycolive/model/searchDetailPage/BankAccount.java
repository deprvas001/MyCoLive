package com.development.mycolive.model.searchDetailPage;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BankAccount implements Parcelable {
    private String id;
    @SerializedName("account_no")
    private String accountId;
    private String name;
    @SerializedName("sort_name")
    private String sortName;
    private String iban;
    private String desc;

    protected BankAccount(Parcel in) {
        id = in.readString();
        accountId = in.readString();
        name = in.readString();
        sortName = in.readString();
        iban = in.readString();
        desc = in.readString();
    }

    public static final Creator<BankAccount> CREATOR = new Creator<BankAccount>() {
        @Override
        public BankAccount createFromParcel(Parcel in) {
            return new BankAccount(in);
        }

        @Override
        public BankAccount[] newArray(int size) {
            return new BankAccount[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(accountId);
        parcel.writeString(name);
        parcel.writeString(sortName);
        parcel.writeString(iban);
        parcel.writeString(desc);
    }
}
