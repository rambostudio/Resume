package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harit.chathep.resume.R;
import com.harit.chathep.resume.dao.EducationDataCollectionDao;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.manager.HttpManager;
import com.harit.chathep.resume.util.JsonUtil;
import com.harit.chathep.resume.util.NetworkStateUtil;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class EducationFragment extends Fragment {

    CardView educationContent;
    ImageView ivUniversityLogo;
    TextView tvUniversity, tvPeriod, tvGPA, tvDegree, tvMajor;
    ProgressBar progressBar;

    public EducationFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static EducationFragment newInstance() {
        EducationFragment fragment = new EducationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_education, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        progressBar = (ProgressBar) rootView.findViewById(R.id.educationProgressbar);
        educationContent = (CardView) rootView.findViewById(R.id.educationContent);
        ivUniversityLogo = (ImageView) rootView.findViewById(R.id.ivUniversityLogo);
        tvUniversity = (TextView) rootView.findViewById(R.id.tvUniversity);
        tvPeriod = (TextView) rootView.findViewById(R.id.tvEducationPeriod);
        tvDegree = (TextView) rootView.findViewById(R.id.tvEducationDegree);
        tvMajor = (TextView) rootView.findViewById(R.id.tvEducationMajor);
        tvGPA = (TextView) rootView.findViewById(R.id.tvEducationGPA);

        progressBar.setVisibility(View.VISIBLE);
        educationContent.setVisibility(View.INVISIBLE);

        Call<EducationDataCollectionDao> call = HttpManager.getInstance().getService().loadEducationList();
        if (NetworkStateUtil.isOnline()) {
            getDataFromServer(call);
        } else {
            getDataFromLocal();
        }


    }

    private void getDataFromLocal() {
        EducationDataCollectionDao dao = (EducationDataCollectionDao) JsonUtil.jsonToModel(EducationDataCollectionDao.class, "education.json");
        setDataView(dao);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void getDataFromServer(Call<EducationDataCollectionDao> call) {
        call.enqueue(new Callback<EducationDataCollectionDao>() {
            @Override
            public void onResponse(Call<EducationDataCollectionDao> call, Response<EducationDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    EducationDataCollectionDao dao = response.body();
                    setDataView(dao);


                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_LONG)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<EducationDataCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setDataView(EducationDataCollectionDao dao) {
        educationContent.setVisibility(View.VISIBLE);
        if (dao != null && dao.getData() != null) {
            tvUniversity.setText(dao.getData().getUniversity());
            tvPeriod.setText(dao.getData().getPeriod());
            tvMajor.setText(dao.getData().getMajor());
            tvDegree.setText(dao.getData().getDegree());
            tvGPA.setText(dao.getData().getGpa());
            setUniversityLogo(dao.getData().getLogoUrl());
        }

    }

    private void setUniversityLogo(String url) {
        if (NetworkStateUtil.isOnline()) {
            Glide.with(getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // ใช้เมื่อรูปในแอพมีการแสดงผลหลายขนาาด
                    .into(ivUniversityLogo);
        } else {
            ivUniversityLogo.setImageResource(R.drawable.kmitl_logo);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
