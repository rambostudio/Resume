package com.harit.chathep.resume.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.harit.chathep.resume.fragment.EducationFragment;
import com.harit.chathep.resume.fragment.ExperienceFragment;
import com.harit.chathep.resume.fragment.PersonalDataFragment;
import com.harit.chathep.resume.fragment.SkillFragment;

/**
 * Created by rambo on 28/3/2560.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PersonalDataFragment.newInstance();
            case 1:
                return ExperienceFragment.newInstance();
            case 2:
                return SkillFragment.newInstance();
            case 3:
                return EducationFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Personal Data";
            case 1:
                return "Experience";
            case 2:
                return "Skill";
            case 3:
                return "Education";
            default:
                return "";
        }
    }
}
