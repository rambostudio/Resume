package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harit.chathep.resume.R;
import com.harit.chathep.resume.dao.PersonalDataCollectionDao;
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
public class PersonalDataFragment extends Fragment {

    ImageView ivMyImage;
    TextView tvName,tvSex,tvAge,tvDOB,tvRegion,tvMilitaryStatus, tvMaritalStatus,tvNationality;
    public PersonalDataFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PersonalDataFragment newInstance() {
        PersonalDataFragment fragment = new PersonalDataFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_personal_data, container, false);
        initInstances(rootView, savedInstanceState);


        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        ivMyImage = (ImageView) rootView.findViewById(R.id.ivMyImage);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvSex = (TextView) rootView.findViewById(R.id.tvSex);
        tvAge = (TextView) rootView.findViewById(R.id.tvAge);
        tvDOB = (TextView) rootView.findViewById(R.id.tvDOB);
        tvNationality = (TextView) rootView.findViewById(R.id.tvNationality);
        tvRegion = (TextView) rootView.findViewById(R.id.tvRegion);
        tvMilitaryStatus = (TextView) rootView.findViewById(R.id.tvMilitaryStatus);
        tvMaritalStatus = (TextView) rootView.findViewById(R.id.tvMaritalStatus);

        Call<PersonalDataCollectionDao> call = HttpManager.getInstance().getService().loadPersonalData();
        call.enqueue(new Callback<PersonalDataCollectionDao>() {
            @Override
            public void onResponse(Call<PersonalDataCollectionDao> call, Response<PersonalDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    PersonalDataCollectionDao dao = response.body();
                    setPersonalDataView(dao);
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
            }

            @Override
            public void onFailure(Call<PersonalDataCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void setPersonalDataView(PersonalDataCollectionDao dao) {
        if (dao != null && dao.getData() != null) {
            tvName.setText(dao.getData().getName());
            tvSex.setText(dao.getData().getSex());
            tvAge.setText(dao.getData().getAge());
            tvDOB.setText(dao.getData().getDob());
            tvNationality.setText(dao.getData().getNationality());
            tvRegion.setText(dao.getData().getRegion());
            tvMilitaryStatus.setText(dao.getData().getMilitaryStatus());
            tvMaritalStatus.setText(dao.getData().getMaritalStatus());
            setMyImage(dao.getData().getImageUrl());
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

    private void setMyImage(String url) {
        Glide.with(getContext())
                .load(url)
//                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // ใช้เมื่อรูปในแอพมีการแสดงผลหลายขนาาด
                .into(ivMyImage);
    }

}
