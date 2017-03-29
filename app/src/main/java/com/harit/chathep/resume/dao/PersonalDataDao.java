package com.harit.chathep.resume.dao;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rambo on 28/3/2560.
 */

public class PersonalDataDao {
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("age")
    private String age;
    @SerializedName("dob")
    private String dob;
    @SerializedName("nationality")
    private String nationality;
    @SerializedName("region")
    private String region;
    @SerializedName("military_status")
    private String militaryStatus;
    @SerializedName("marital_status")
    private String maritalStatus;
    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("address")
    private String address;
    @SerializedName("email")
    private String email;
    @SerializedName("tel")
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(String militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
