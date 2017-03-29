package com.harit.chathep.resume.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harit.chathep.resume.R;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ExperienceListItem extends BaseCustomViewGroup {

    ImageView ivCompanyLogo;
    TextView tvCompanyName,tvPeriod,tvPosition,tvWorkTitle,tvProjectName, tvWorkDescription;
    public ExperienceListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ExperienceListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ExperienceListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ExperienceListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_experience, this);
    }

    private void initInstances() {
        // findViewById here
        ivCompanyLogo = (ImageView) findViewById(R.id.ivCompanyLogo);
        tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        tvPeriod = (TextView) findViewById(R.id.tvPeriod);
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvWorkTitle = (TextView) findViewById(R.id.tvWorkTitle);
        tvProjectName = (TextView) findViewById(R.id.tvProjectName);
        tvWorkDescription = (TextView) findViewById(R.id.tvWorkDescription);
    }
    public void setCompanyLogoUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // ใช้เมื่อรูปในแอพมีการแสดงผลหลายขนาาด
                .into(ivCompanyLogo);
    }

    public void setCompanyNameText(String text) {
        tvCompanyName.setText(text);
    }

    public void setPeriodText(String text) {
        tvPeriod.setText(text);
    }

    public void setPositionText(String text) {
        tvPosition.setText(text);
    }
    public void setWorkTitleText(String text) {
        tvWorkTitle.setText(text);
    }
    public void setProjectNameText(List<String> text_list) {
        tvWorkDescription.setText(text_list.get(0));
    }

    public void setWorkDescriptionText(List<String> text_list) {
        String text = "";
        for(String s : text_list) {
            text = text+(String.format("\u2022 %s \n", s));
        }
        tvWorkDescription.setText(text);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

}
