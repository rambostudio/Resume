package com.harit.chathep.resume.view;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.harit.chathep.resume.R;
import com.harit.chathep.resume.util.NetworkStateUtil;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.List;

/**
 * Created by rambo on 29/3/2560.
 */

public class ExperienceViewHolders extends RecyclerView.ViewHolder {
    ImageView ivCompanyLogo;
    TextView tvCompanyName, tvPeriod, tvPosition, tvWorkTitle, tvProjectName, tvWorkDescription;

    public ExperienceViewHolders(View itemView) {
        super(itemView);
        // findViewById here
        ivCompanyLogo = (ImageView) itemView.findViewById(R.id.ivCompanyLogo);
        tvCompanyName = (TextView) itemView.findViewById(R.id.tvCompanyName);
        tvPeriod = (TextView) itemView.findViewById(R.id.tvPeriod);
        tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
        tvWorkTitle = (TextView) itemView.findViewById(R.id.tvWorkTitle);
        tvProjectName = (TextView) itemView.findViewById(R.id.tvProjectName);
        tvWorkDescription = (TextView) itemView.findViewById(R.id.tvWorkDescription);
    }

    public void setCompanyLogoUrl(String url) {
        if (NetworkStateUtil.isOnline()) {
            Glide.with(Contextor.getInstance().getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // ใช้เมื่อรูปในแอพมีการแสดงผลหลายขนาาด
                    .into(ivCompanyLogo);
        } else {
            switch (url) {
                case "http://www.testshop.livingthink.com/api/bla_logo.png":
                    ivCompanyLogo.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivCompanyLogo.setImageResource(R.drawable.bla_logo);
                    break;
                case "http://www.testshop.livingthink.com/api/comtex_logo.JPG":
                    ivCompanyLogo.setBackgroundResource(R.drawable.comtex_logo);
                    break;
                case "http://www.testshop.livingthink.com/api/ais_logo.png":
                    ivCompanyLogo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ivCompanyLogo.setImageResource(R.drawable.ais_logo);
                    break;
                default:
                    break;
            }


        }


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
        for (String s : text_list) {
            text = text + (String.format("\u2022 %s \n", s));
        }
        tvWorkDescription.setText(text);
    }
}
