package com.harit.chathep.resume.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harit.chathep.resume.R;
import com.harit.chathep.resume.dao.ExperienceDataCollectionDao;
import com.harit.chathep.resume.dao.SkillDataCollectionDao;
import com.harit.chathep.resume.dao.SkillDataDao;
import com.harit.chathep.resume.view.SkillViewHolders;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillViewHolders>{
    private List<SkillDataDao> itemList;
    private Context context;

    public SkillRecyclerViewAdapter(List<SkillDataDao> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public SkillViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_skill, null);
        SkillViewHolders skillViewHolders = new SkillViewHolders(layoutView);
        return skillViewHolders;
    }

    @Override
    public void onBindViewHolder(SkillViewHolders holder, int position) {
        holder.setSkillTitleText(itemList.get(position).getSkillTitle());
        holder.setSkillDescriptionText(itemList.get(position).getSkillDescription());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
