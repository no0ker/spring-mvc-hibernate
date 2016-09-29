package com.rtm.finder.ajax_entities;

public class Row {
    private String firstName;
    private String secondName;
    private String city;
    private String car_color;

    public Row(String firstName, String secondName, String city, String car_color) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.car_color = car_color;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }
}
