package com.example.android.myrecord.NetworkModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Prasad on 28-Nov-17.
 */

public class ChangePasswordResponseModel {
    private String old,confirmOld;
    @SerializedName("new")
    String newpass;


    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getConfirmOld() {
        return confirmOld;
    }

    public void setConfirmOld(String confirmOld) {
        this.confirmOld = confirmOld;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
}
