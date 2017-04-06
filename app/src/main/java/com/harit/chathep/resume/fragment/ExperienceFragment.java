package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.adapter.ExperienceListAdapter;
import com.harit.chathep.resume.adapter.ExperienceRecyclerViewAdapter;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.dao.PersonalDataCollectionDao;
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
public class ExperienceFragment extends Fragment {
    RecyclerView recyclerView;
    ListView listView;
    ExperienceListAdapter listAdapter;
    ProgressBar progressBar;
    public ExperienceFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ExperienceFragment newInstance() {
        ExperienceFragment fragment = new ExperienceFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_experience, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        progressBar = (ProgressBar) rootView.findViewById(R.id.experienceProgressbar);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        if (NetworkStateUtil.isOnline()) {
            getDataFromServer();
        } else {
            getDataFromLocal();
        }
    }
    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }


    private void getDataFromServer() {
        Call<ExperienceDataCollectionDao> call = HttpManager.getInstance().getService().loadExperienceList();
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ExperienceDataCollectionDao>() {
            @Override
            public void onResponse(Call<ExperienceDataCollectionDao> call, Response<ExperienceDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    ExperienceDataCollectionDao dao = response.body();
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
            public void onFailure(Call<ExperienceDataCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getDataFromLocal() {
        ExperienceDataCollectionDao dao = (ExperienceDataCollectionDao) JsonUtil.jsonToModel(ExperienceDataCollectionDao.class, "experience.json");
        setDataView(dao);
        progressBar.setVisibility(View.GONE);
    }

    private void setDataView(ExperienceDataCollectionDao dao) {
        ExperienceRecyclerViewAdapter recyclerViewAdapter = new ExperienceRecyclerViewAdapter(dao.getData(), getContext());
        initRecyclerView();
        recyclerView.setAdapter(recyclerViewAdapter);
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
