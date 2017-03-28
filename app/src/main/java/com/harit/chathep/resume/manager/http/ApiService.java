package com.harit.chathep.resume.manager.http;

import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.dao.PersonalDataCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rambo on 28/3/2560.
 */

public interface ApiService {
    @GET("personal_data.php")
    Call<PersonalDataCollectionDao> loadPersonalData();

    @GET("experience.php")
    Call<ExperienceDataCollectionDao> loadExperienceList();

}
