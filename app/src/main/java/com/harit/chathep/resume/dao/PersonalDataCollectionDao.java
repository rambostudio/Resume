package com.harit.chathep.resume.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rambo on 28/3/2560.
 */

public class PersonalDataCollectionDao {
    @SerializedName("success")
    private boolean succcess;

    @SerializedName("data")
    private PersonalDataDao data;

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public PersonalDataDao getData() {
        return data;
    }

    public void setData(PersonalDataDao data) {
        this.data = data;
    }
}
