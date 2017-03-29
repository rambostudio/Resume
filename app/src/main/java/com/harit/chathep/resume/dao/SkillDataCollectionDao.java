package com.harit.chathep.resume.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class SkillDataCollectionDao {
    @SerializedName("success")
    private boolean succcess;

    @SerializedName("data")
    private List<SkillDataDao> data;

    public boolean isSucccess() {
        return succcess;
    }

    public void setSucccess(boolean succcess) {
        this.succcess = succcess;
    }

    public List<SkillDataDao> getData() {
        return data;
    }

    public void setData(List<SkillDataDao> data) {
        this.data = data;
    }
}
