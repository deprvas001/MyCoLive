package com.development.mycolive.model;

public class FindRoomateModel {
    String name;
    String age;
    String country;
    String city;
    String university;
    String address;

    public FindRoomateModel(String name, String age, String country, String city, String university, String address) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.city = city;
        this.university = university;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getUniversity() {
        return university;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
