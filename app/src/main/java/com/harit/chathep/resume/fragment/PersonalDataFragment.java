package com.harit.chathep.resume.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harit.chathep.resume.R;
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
public class PersonalDataFragment extends Fragment {

    ImageView ivMyImage;
    TextView tvName, tvSex, tvAge, tvDOB, tvRegion, tvMilitaryStatus, tvMaritalStatus, tvNationality, tvEmail, tvAddress, tvTel;
    LinearLayout personalContent;
    ProgressBar progressBar;

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
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        tvTel = (TextView) rootView.findViewById(R.id.tvTell);
        tvEmail = (TextView) rootView.findViewById(R.id.tvEmail);
        progressBar = (ProgressBar) rootView.findViewById(R.id.personalProgressbar);
        personalContent = (LinearLayout) rootView.findViewById(R.id.personalContent);
        progressBar.setVisibility(View.VISIBLE);

        if (NetworkStateUtil.isOnline()) {
            getDataFromServer();
        } else {
            getDataFromLocal();
        }
    }

    private void getDataFromServer() {
        Call<PersonalDataCollectionDao> call = HttpManager.getInstance().getService().loadPersonalData();
        call.enqueue(new Callback<PersonalDataCollectionDao>() {
            @Override
            public void onResponse(Call<PersonalDataCollectionDao> call, Response<PersonalDataCollectionDao> response) {
                if (response.isSuccessful()) {
                    PersonalDataCollectionDao dao = response.body();
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
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<PersonalDataCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_LONG)
                        .show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getDataFromLocal() {

        PersonalDataCollectionDao dao = (PersonalDataCollectionDao) JsonUtil.jsonToModel(PersonalDataCollectionDao.class, "personal_data.json");
        setDataView(dao);
        progressBar.setVisibility(View.GONE);
        personalContent.setVisibility(View.VISIBLE);
    }

    private void setDataView(PersonalDataCollectionDao dao) {
        personalContent.setVisibility(View.VISIBLE);
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
            tvAddress.setText(dao.getData().getAddress());
            tvEmail.setText(dao.getData().getEmail());
            tvTel.setText(dao.getData().getTel());
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

        if (NetworkStateUtil.isOnline()) {
            ivMyImage.getLayoutParams().height = (int) getResources().getDimension(R.dimen.imageview_myimage_height);
            ivMyImage.getLayoutParams().width = (int) getResources().getDimension(R.dimen.imageview_myimage_width);
            ivMyImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // ใช้เมื่อรูปในแอพมีการแสดงผลหลายขนาาด
                    .into(ivMyImage);
        } else {
            ivMyImage.setBackgroundResource(R.drawable.round_image);
        }


    }

}
