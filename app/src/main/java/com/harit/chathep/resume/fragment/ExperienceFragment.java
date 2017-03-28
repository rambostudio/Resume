package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.adapter.ExperienceListAdapter;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.manager.HttpManager;
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
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new ExperienceListAdapter();
        listView.setAdapter(listAdapter);

        Call<ExperienceDataCollectionDao> call = HttpManager.getInstance().getService().loadExperienceList();
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ExperienceDataCollectionDao>() {
            @Override
            public void onResponse(Call<ExperienceDataCollectionDao> call, Response<ExperienceDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    ExperienceDataCollectionDao dao = response.body();
                    listAdapter.setDao(dao);
                    listAdapter.notifyDataSetChanged();
                    listView.setVisibility(View.VISIBLE);
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
