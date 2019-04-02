package com.moviesapp.project.dm_project.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.main.MainActivity;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.view.activity.SearchActivity;
import com.moviesapp.project.dm_project.view.adapter.RecyclerviewAdapter;

import java.util.List;

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<ModuleAddressBean> list;

    @Override
    public void init_Data() {
        list = ((MainActivity)getActivity()).getMovieData();
    }

    @Override
    public View init_View(ViewGroup container) {
        View w= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ImageView search = w.findViewById(R.id.search);
        recyclerView = w.findViewById(R.id.recyclerView);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(getActivity(),list,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return w;
    }
}
