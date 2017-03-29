package com.harit.chathep.resume.dao;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rambo on 29/3/2560.
 */

public class SkillDataDao {
    @SerializedName("skill_title")
    private String skillTitle;
    @SerializedName("skill_description")
    private List<String> skillDescription = new List<String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(String s) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends String> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends String> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public String get(int i) {
            return null;
        }

        @Override
        public String set(int i, String s) {
            return null;
        }

        @Override
        public void add(int i, String s) {

        }

        @Override
        public String remove(int i) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<String> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<String> subList(int i, int i1) {
            return null;
        }
    };

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public List<String> getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(List<String> skillDescription) {
        this.skillDescription = skillDescription;
    }
}
