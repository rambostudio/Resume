package com.harit.chathep.resume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.dao.ExperienceDataDao;
import com.harit.chathep.resume.view.ExperienceViewHolders;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class ExperienceRecyclerViewAdapter extends RecyclerView.Adapter<ExperienceViewHolders> {
    List<ExperienceDataDao> itemList;
    Context context;

    public ExperienceRecyclerViewAdapter(List<ExperienceDataDao> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ExperienceViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_experience, null);
        ExperienceViewHolders viewHolders = new ExperienceViewHolders(layoutView);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(ExperienceViewHolders holder, int position) {
        holder.setCompanyLogoUrl(itemList.get(position).getLogoUrl());
        holder.setCompanyNameText(itemList.get(position).getCompanyName());
        holder.setPeriodText(itemList.get(position).getPeriod());
        holder.setPositionText(itemList.get(position).getPosition());
        holder.setProjectNameText(itemList.get(position).getProjectName());
        holder.setWorkTitleText(itemList.get(position).getWorkTitle());
        holder.setWorkDescriptionText(itemList.get(position).getWorkDescription());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
