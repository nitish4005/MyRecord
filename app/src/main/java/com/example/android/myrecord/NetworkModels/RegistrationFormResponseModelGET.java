package com.example.android.myrecord.NetworkModels;

import java.io.Serializable;

/**
 * Created by Prasad on 21-Nov-17.
 */

public class RegistrationFormResponseModelGET implements Serializable {
    String Enrollment_No,msg,Department,Name,Semester,Father_Name,Mother_Name,Mobile_No,Father_Mobile_No,Father_Occupation,Mother_Occupation;

    public RegistrationFormResponseModelGET(String enrollment_No,String msg, String department, String name, String semester, String father_Name, String mother_Name, String mobile_No, String father_Mobile_No, String father_Occupation, String mother_Occupation) {
        Enrollment_No = enrollment_No;
        this.msg=msg;
        Department = department;
        Name = name;
        Semester = semester;
        Father_Name = father_Name;
        Mother_Name = mother_Name;
        Mobile_No = mobile_No;
        Father_Mobile_No = father_Mobile_No;
        Father_Occupation = father_Occupation;
        Mother_Occupation = mother_Occupation;
    }

    public String getEnrollment_No() {
        return Enrollment_No;
    }

    public void setEnrollment_No(String enrollment_No) {
        Enrollment_No = enrollment_No;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getName() {
        return Name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getFather_Name() {
        return Father_Name;
    }

    public void setFather_Name(String father_Name) {
        Father_Name = father_Name;
    }

    public String getMother_Name() {
        return Mother_Name;
    }

    public void setMother_Name(String mother_Name) {
        Mother_Name = mother_Name;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getFather_Mobile_No() {
        return Father_Mobile_No;
    }

    public void setFather_Mobile_No(String father_Mobile_No) {
        Father_Mobile_No = father_Mobile_No;
    }

    public String getFather_Occupation() {
        return Father_Occupation;
    }

    public void setFather_Occupation(String father_Occupation) {
        Father_Occupation = father_Occupation;
    }

    public String getMother_Occupation() {
        return Mother_Occupation;
    }

    public void setMother_Occupation(String mother_Occupation) {
        Mother_Occupation = mother_Occupation;
    }
}
