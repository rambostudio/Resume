package com.harit.chathep.resume.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rambo on 29/3/2560.
 */

public class EducationDataDao {
    @SerializedName("university")
    private String university;
    @SerializedName("period")
    private String period;
    @SerializedName("degree")
    private String degree;
    @SerializedName("major")
    private String major;
    @SerializedName("gpa")
    private String gpa;
    @SerializedName("logo_url")
    private String logoUrl;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
