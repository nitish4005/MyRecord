package com.example.android.myrecord.NetworkModels;

import java.io.Serializable;

/**
 * Created by Prasad on 05-Nov-17.
 */

public class AssignmentsRecord implements Serializable {
   private int assid,teacherId;
   private String heading,semester,startTime,endTime,date,teacherName,subject,dateOfTest,department,instructions,status,colorCode;

    public int getAssid() {
        return assid;
    }

    public void setAssid(int assid) {
        this.assid = assid;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDateOfTest() {
        return dateOfTest;
    }

    public void setDateOfTest(String dateOfTest) {
        this.dateOfTest = dateOfTest;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public AssignmentsRecord(int assid, int teacherId, String heading, String semester, String startTime, String endTime, String date, String teacherName, String subject, String dateOfTest, String department, String instructions, String status, String colorCode) {

        this.assid = assid;
        this.teacherId = teacherId;
        this.heading = heading;
        this.semester = semester;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.teacherName = teacherName;
        this.subject = subject;
        this.dateOfTest = dateOfTest;
        this.department = department;
        this.instructions = instructions;
        this.status = status;
        this.colorCode = colorCode;
    }
}
