package com.harit.chathep.resume.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rambo on 28/3/2560.
 */

public class ExperienceDataCollectionDao {
    @SerializedName("success")
    private boolean succcess;

    @SerializedName("data")
    private List<ExperienceDataDao> data;

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public List<ExperienceDataDao> getData() {
        return data;
    }

    public void setData(List<ExperienceDataDao> data) {
        this.data = data;
    }
}
