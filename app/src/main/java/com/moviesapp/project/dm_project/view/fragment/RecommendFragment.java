package com.moviesapp.project.dm_project.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.main.MainActivity;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.view.adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<ModuleAddressBean> list;

    @Override
    public void init_Data() {
        list = new ArrayList<>();
        List<ModuleAddressBean> datalist = ((MainActivity)getActivity()).getMovieData();
        list.addAll(datalist);
    }

    @Override
    public View init_View(ViewGroup container) {
        View w= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_recommend, null);
        TextView title = w.findViewById(R.id.title);
        title.setText("Recommendation");

        recyclerView = w.findViewById(R.id.recyclerView);

        Collections.sort(list);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(getActivity(),list,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return w;
    }
}
