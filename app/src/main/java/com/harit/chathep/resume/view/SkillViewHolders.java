package com.harit.chathep.resume.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.harit.chathep.resume.R;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class SkillViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvSkillTitle;
    private TextView tvSkillDescription;

    public SkillViewHolders(View itemView) {
        super(itemView);
        tvSkillTitle = (TextView) itemView.findViewById(R.id.tvSkillTitle);
        tvSkillDescription = (TextView) itemView.findViewById(R.id.tvSkillDescription);
    }

    @Override
    public void onClick(View view) {

    }

    public void setSkillTitleText(String text) {
        tvSkillTitle.setText(text);

    }
    public void setSkillDescriptionText(List<String> text_list) {
        String text = "";
        for(String s : text_list) {
            text = text+(String.format("\u2022 %s \n", s));
        }
        tvSkillDescription.setText(text);
    }

}
