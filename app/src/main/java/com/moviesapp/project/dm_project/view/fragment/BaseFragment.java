package com.moviesapp.project.dm_project.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    public Context ct = getActivity();
    public void set_Context(Context ct)
    {
        this.ct=ct;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init_Data();
        View v=init_View(container);
        return v;
    }
    public abstract void init_Data();
    public abstract View init_View(ViewGroup container);
}
