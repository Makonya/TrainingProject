package com.epam.training.entity;

import java.util.Date;

public class CourseUser extends BaseEntity {
    private int idCourse;
    private int idUser;
    private int templMark;
    private Date templStartDate;
    private Date templEndDate;
    private String templCourseName;

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTemplMark() {
        return templMark;
    }

    public void setTemplMark(int templMark) {
        this.templMark = templMark;
    }

    public Date getTemplStartDate() {
        return templStartDate;
    }

    public void setTemplStartDate(Date templStartDate) {
        this.templStartDate = templStartDate;
    }

    public Date getTemplEndDate() {
        return templEndDate;
    }

    public void setTemplEndDate(Date templEndDate) {
        this.templEndDate = templEndDate;
    }

    public String getTemplCourseName() {
        return templCourseName;
    }

    public void setTemplCourseName(String templCourseName) {
        this.templCourseName = templCourseName;
    }
}
