package com.test.myapplication;

import android.graphics.drawable.Drawable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by NehaRege on 12/8/16.
 */

@IgnoreExtraProperties

public class Report {


    public  String longitude;
    public  String latitude;
    public  String street;
    public  String currentstatus;
    public  String severity;
    public String img;
    public  Drawable image;
    public  String size;
    public  String time;
    public  String date;
    public  String description;
    public List<User>user;
    public String emailId;

    public Report(String longitude, String latitude, String street, String currentstatus, String severity, Drawable image, String size, String time, String date, String description, List<User> user, String emailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        this.currentstatus = currentstatus;
        this.severity = severity;
        this.image = image;
        this.size = size;
        this.time = time;
        this.date = date;
        this.description = description;
        this.user = user;
        this.emailId = emailId;
    }

    public Report(String longitude, String latitude, String street, String img, String description, String emailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        this.img = img;
        this.description = description;
        this.emailId = emailId;
    }

    public Report(String longitude, String latitude, String street, String currentstatus, String severity, Drawable image, String size, String time, String date, String description, String emailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        this.currentstatus = currentstatus;
        this.severity = severity;
        this.image = image;
        this.size = size;
        this.time = time;
        this.date = date;
        this.description = description;
        this.emailId = emailId;
    }

//    Report report2 = new Report(longi.getText().toString(),lat.getText().toString(),street.getText().toString(),radioButtonSeverityLevel.getText().toString(),b64Image,radioButtonSize.getText().toString(),editTextDescription.getText().toString(),gmail);


    public Report(String longitude, String latitude, String street, String severity, String img, String size, String description, String emailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        //this.currentstatus = currentstatus;
        this.severity = severity;
        this.img = img;
        this.image = image;
        this.size = size;
        this.description = description;
        this.emailId = emailId;
    }

//    public Report(String longitude, String latitude, String street, Drawable image, String description) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.street = street;
//        this.image = image;
//        this.description = description;
//    }

    public Report(String longitude, String latitude, String street, String severity, Drawable image, String size, String description, String emailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        //this.currentstatus = currentstatus;
        this.severity = severity;
        this.img = img;
        this.size = size;
        this.description = description;
        this.emailId = emailId;
    }



    public Report(String longitude, String latitude, String street, String description, Drawable image) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        this.description = description;
        this.image = image;
    }

    public Report(String longitude, String latitude, String street, String img, String description) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.street = street;
        this.img = img;
        this.description = description;
    }

//    public Report(String longitude, String latitude, String street, String currentstatus, String severity, Drawable image, String size, String time, String date, String description, String emailId) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.street = street;
//        this.currentstatus = currentstatus;
//        this.severity = severity;
//        this.image = image;
//        this.size = size;
//        this.time = time;
//        this.date = date;
//        this.description = description;
//        this.emailId = emailId;
//    }

    public Report() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString(){
        return (this.emailId);

    }


}