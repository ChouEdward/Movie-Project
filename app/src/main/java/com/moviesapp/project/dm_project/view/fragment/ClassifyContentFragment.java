package com.moviesapp.project.dm_project.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.main.MainActivity;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.view.adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.moviesapp.project.dm_project.util.ConstansUtil.CLASSIFIER_NAME;

public class ClassifyContentFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<ModuleAddressBean> list;
    private String classifier_name;

    @Override
    public void init_Data() {
        list = ((MainActivity)getActivity()).getMovieData();
        Bundle getargs = getArguments();
        if(getargs!=null){
            int value = getargs.getInt("cla_page_position",0);
            classifier_name = CLASSIFIER_NAME[value];
        }

    }

    @Override
    public View init_View(ViewGroup container) {
        View w= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify_content, null);

        recyclerView = w.findViewById(R.id.recyclerView);
        List<ModuleAddressBean> classifier_list = new ArrayList<>();
        for (ModuleAddressBean module:
                list) {
            if(module.getGenres().contains(classifier_name)){
                classifier_list.add(module);
            }
        }
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(getActivity(),classifier_list,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return w;
    }
}
