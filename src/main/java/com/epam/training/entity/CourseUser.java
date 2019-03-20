package com.epam.training.entity;

import java.util.Date;

public class CourseUser extends BaseEntity {
    private int idCourse;
    private int idUser;
    private int tempMark;
    private Date tempStartDate;
    private Date tempEndDate;
    private String tempCourseName;

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

    public int getTempMark() {
        return tempMark;
    }

    public void setTempMark(int tempMark) {
        this.tempMark = tempMark;
    }

    public Date getTempStartDate() {
        return tempStartDate;
    }

    public void setTempStartDate(Date tempStartDate) {
        this.tempStartDate = tempStartDate;
    }

    public Date getTempEndDate() {
        return tempEndDate;
    }

    public void setTempEndDate(Date tempEndDate) {
        this.tempEndDate = tempEndDate;
    }

    public String getTempCourseName() {
        return tempCourseName;
    }

    public void setTempCourseName(String tempCourseName) {
        this.tempCourseName = tempCourseName;
    }
}