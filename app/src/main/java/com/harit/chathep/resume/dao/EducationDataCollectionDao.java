package com.harit.chathep.resume.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class EducationDataCollectionDao {
    @SerializedName("success")
    private boolean succcess;

    @SerializedName("data")
    private EducationDataDao data;

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public EducationDataDao getData() {
        return data;
    }

    public void setData(EducationDataDao data) {
        this.data = data;
    }
}
