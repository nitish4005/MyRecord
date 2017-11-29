package com.example.android.myrecord.NetworkModels;

/**
 * Created by Prasad on 22-Oct-17.
 */

public class LoginResponseModel {
    private String name,id,semester, messaege, department,access_token;

    public LoginResponseModel(String name, String id, String semester, String messaege, String department, String access_token) {
        this.name = name;
        this.id = id;
        this.semester = semester;
        this.messaege = messaege;
        this.department = department;
        this.access_token = access_token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMessaege() {
        return messaege;
    }

    public void setMessaege(String messaege) {
        this.messaege = messaege;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String  getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
