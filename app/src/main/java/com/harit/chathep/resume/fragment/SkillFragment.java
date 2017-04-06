package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.adapter.ExperienceRecyclerViewAdapter;
import com.harit.chathep.resume.adapter.SkillRecyclerViewAdapter;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.dao.SkillDataCollectionDao;
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
public class SkillFragment extends Fragment {
    RecyclerView recyclerView;
    StaggeredGridLayoutManager gaggeredGridLayoutManager;
    ProgressBar progressBar;

    public SkillFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SkillFragment newInstance() {
        SkillFragment fragment = new SkillFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_skill, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        progressBar = (ProgressBar) rootView.findViewById(R.id.skillProgressbar);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        if (NetworkStateUtil.isOnline()) {
            getDataFromServer();
        } else {
            getDataFromLocal();
        }

    }

    private void getDataFromLocal() {
        SkillDataCollectionDao dao = (SkillDataCollectionDao) JsonUtil.jsonToModel(SkillDataCollectionDao.class, "skill.json");
        setDataView(dao);
    }

    private void setDataView(SkillDataCollectionDao dao) {
        SkillRecyclerViewAdapter skillRecyclerViewAdapter = new SkillRecyclerViewAdapter(dao.getData(), getContext());
        recyclerView.setAdapter(skillRecyclerViewAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void getDataFromServer() {
        Call<SkillDataCollectionDao> call = HttpManager.getInstance().getService().loadSkillList();

        call.enqueue(new Callback<SkillDataCollectionDao>() {
            @Override
            public void onResponse(Call<SkillDataCollectionDao> call, Response<SkillDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    SkillDataCollectionDao dao = response.body();
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
            public void onFailure(Call<SkillDataCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
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
