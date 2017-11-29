package com.example.android.myrecord.NetworkModels;

/**
 * Created by Prasad on 21-Nov-17.
 */

public class FeedBackSumittModel {
    String heading,message,name,stid;

    public FeedBackSumittModel(String heading, String message, String name, String stid) {
        this.heading = heading;
        this.message = message;
        this.name = name;
        this.stid = stid;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }
}

