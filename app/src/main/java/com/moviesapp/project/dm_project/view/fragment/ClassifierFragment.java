package com.moviesapp.project.dm_project.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.data.ClassifierBase;
import com.moviesapp.project.dm_project.util.ClassifierUtil;
import com.moviesapp.project.dm_project.util.ConstansUtil;
import com.moviesapp.project.dm_project.view.adapter.ClassifyRecycleAdapter;
import com.moviesapp.project.dm_project.view.adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassifierFragment  extends BaseFragment  {

    private ImageView btn;
    private RecyclerView recyclerView;
    private ClassifyRecycleAdapter classifyRecycleAdapter;
    private TextView pls_note;
    private EditText editText;
    private List<ClassifierBase> data;

    @Override
    public void init_Data() {

    }

    @Override
    public View init_View(ViewGroup container) {
        View w = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classifier, container, false);
        btn = w.findViewById(R.id.classify);
        pls_note = w.findViewById(R.id.pls_note);
        editText = w.findViewById(R.id.edit_classify);
        data = new ArrayList<>();
        recyclerView = w.findViewById(R.id.recyclerView);
        classifyRecycleAdapter = new ClassifyRecycleAdapter(getContext(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(classifyRecycleAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().isEmpty()) {
                    pls_note.setVisibility(View.GONE);
                    String str = editText.getText().toString();
                    str = str.toLowerCase();
                    data.clear();
                    data = ClassifierUtil.classify(str, ConstansUtil.genres, ConstansUtil.movie_lists);
                    classifyRecycleAdapter.setData(data);
                    classifyRecycleAdapter.notifyDataSetChanged();
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setSelection(s.toString().length());
                String str = editText.getText().toString();
                if(str.contains("\n")&& !str.replaceAll("\n", "").isEmpty()){
                    str = str.replaceAll("\n", "");
                    editText.setText(str);
                    str = str.toLowerCase();
                    data.clear();
                    data = ClassifierUtil.classify(str, ConstansUtil.genres, ConstansUtil.movie_lists);
                    classifyRecycleAdapter.setData(data);
                    classifyRecycleAdapter.notifyDataSetChanged();
                }else{
                    data.clear();
                    classifyRecycleAdapter.setData(data);
                    classifyRecycleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return w;
    }
}
