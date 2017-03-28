package com.harit.chathep.resume.adapter;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.dao.ExperienceDataDao;
import com.harit.chathep.resume.view.ExperienceListItem;

/**
 * Created by rambo on 28/3/2560.
 */

public class ExperienceListAdapter extends BaseAdapter {
    private ExperienceDataCollectionDao dao;
    private int lastPosition = -1;

    public void setDao(ExperienceDataCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null) {
            return 0;
        }
        if (dao.getData() == null) {
            return 0;
        }
        return dao.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return dao.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ExperienceListItem item;
        if (view != null) {
            item = (ExperienceListItem) view;
        } else {
            item = new ExperienceListItem(viewGroup.getContext());
        }
        ExperienceDataDao dao = (ExperienceDataDao) getItem(position);
        item.setCompanyLogoUrl(dao.getLogoUrl());
        item.setCompanyNameText(dao.getCompanyName());
        item.setPeriodText(dao.getPeriod());
        item.setPositionText(dao.getPosition());
        item.setWorkTitleText(dao.getWorkTitle());
        item.setProjectNameText(dao.getProjectName());
        item.setWorkDescriptionText(dao.getWorkDescription());

        if (position < lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext(),
                    R.anim.up_from_bottom);
            item.setAnimation(animation);
            lastPosition = position;
        }
        return item;
    }
}
