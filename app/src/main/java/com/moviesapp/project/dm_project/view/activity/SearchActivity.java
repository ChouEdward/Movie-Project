package com.moviesapp.project.dm_project.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.data.bean.GetMoviesResponse;
import com.moviesapp.project.dm_project.interfaces.ConnectSuccessListener;
import com.moviesapp.project.dm_project.util.CSVLoader;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.util.OkHttpUtil;
import com.moviesapp.project.dm_project.util.StringUtil;
import com.moviesapp.project.dm_project.view.adapter.RecyclerviewAdapter;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter recyclerviewAdapter;
    private List<ModuleAddressBean> list;
    public static List<ModuleAddressBean> search_list;
    private EditText editText;
    private ImageView search;
    private ImageView back;
    private MKLoader mkLoader;
    private TextView pls_note;

    public List<ModuleAddressBean> getMovieData(){
        if(list==null||list.isEmpty()){
            list = CSVLoader.readMonDataCsv();
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getMovieData();
        TextView title = findViewById(R.id.title);
        title.setText("Search");
        editText = findViewById(R.id.edit_search);
        search = findViewById(R.id.search);
        back = findViewById(R.id.back);
        mkLoader = findViewById(R.id.mkloader);
        pls_note = findViewById(R.id.pls_note);

        search_list = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerviewAdapter = new RecyclerviewAdapter(this,search_list, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().isEmpty()) {
                    mkLoader.setVisibility(View.VISIBLE);
                    pls_note.setVisibility(View.GONE);
                    String str = editText.getText().toString();
                    str = str.toLowerCase();
                    search_list.clear();
                    queryforsearch = str;
                    queryToSearch(str);
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
                    mkLoader.setVisibility(View.VISIBLE);
                    pls_note.setVisibility(View.GONE);
                    str = str.replaceAll("\n", "");
                    editText.setText(str);
                    str = str.toLowerCase();
                    search_list.clear();
                    queryforsearch = str;
                    queryToSearch(str);
//                    for (ModuleAddressBean moduleAddressBean:
//                         list) {
//                        if(moduleAddressBean.getOverview().toLowerCase().contains(str)||
//                                moduleAddressBean.getTitle().toLowerCase().contains(str)){
//                            search_list.add(moduleAddressBean);
//                        }
//                    }
//                    recyclerviewAdapter.notifyDataSetChanged();

                }else{
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public static String queryforsearch="";
    public void queryToSearch(String query){
        List<String> key = new ArrayList<>();
        List<String> value = new ArrayList<>();

        key.add("query");
        value.add(queryforsearch);

        String url = "/user/getMoviesBySearch.shtml";
        String s = "";
        OkHttpUtil.getInstance().connect(key, value, url, new ConnectSuccessListener() {
            @Override
            public void onFailure(String message) {
                Log.e("TestSearch",message);
            }

            @Override
            public void onResponse(final String response) {
                if(response.contains("200")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_LONG).show();
                            GetMoviesResponse moviesResponse = new Gson().fromJson(response,GetMoviesResponse.class);
                            List<ModuleAddressBean> list = moviesResponse.getModuleAddressBeans();
//                            Log.e("TestSearch",response);
                            search_list.clear();
                            search_list = list;
                            recyclerviewAdapter.setData(search_list, StringUtil.splitToWords(queryforsearch));
                            recyclerviewAdapter.notifyDataSetChanged();
                            mkLoader.setVisibility(View.GONE);
//                            Log.e("TestSearch",response);
                        }
                    });
                }
//                Log.e("Success",response);
            }
        });
    }
}
