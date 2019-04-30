package com.moviesapp.project.dm_project.data;

import android.support.annotation.NonNull;

public class ClassifierBase implements Comparable<ClassifierBase>{

    private String genres;
    private double score = 0.0;

    public ClassifierBase() {
    }

    public ClassifierBase(String genres, double score) {
        this.genres = genres;
        this.score = score;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull ClassifierBase o) {
        return getScore()>o.getScore()?-1:1;
    }
}
