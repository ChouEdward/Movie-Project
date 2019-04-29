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
import com.moviesapp.project.dm_project.util.ConstansUtil;
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
//                    queryToSearch(str);
                    List<ModuleAddressBean> list = query(str, ConstansUtil.movie_lists);
//                            Log.e("TestSearch",response);
                    search_list.clear();
                    search_list = list;
                    recyclerviewAdapter.setData(search_list, StringUtil.splitToWords(queryforsearch));
                    recyclerviewAdapter.notifyDataSetChanged();
                    mkLoader.setVisibility(View.GONE);
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
//                    queryToSearch(str);
                    ;
                    List<ModuleAddressBean> list = query(str, ConstansUtil.movie_lists);
//                            Log.e("TestSearch",response);
                    search_list.clear();
                    search_list = list;
                    recyclerviewAdapter.setData(search_list, StringUtil.splitToWords(queryforsearch));
                    recyclerviewAdapter.notifyDataSetChanged();
                    mkLoader.setVisibility(View.GONE);

//                    for (ModuleAddressBean moduleAddressBean:
//                         list) {
//                        if(moduleAddressBean.getOverview().toLowerCase().contains(str)||
//                                moduleAddressBean.getTitle().toLowerCase().contains(str)){
//                            search_list.add(moduleAddressBean);
//                        }
//                    }
//                    recyclerviewAdapter.notifyDataSetChanged();

                }else{
                    mkLoader.setVisibility(View.GONE);
//                    pls_note.setVisibility(View.VISIBLE);
                    search_list.clear();
                    recyclerviewAdapter.setData(search_list, new String[]{});
                    recyclerviewAdapter.notifyDataSetChanged();
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

    public static List<ModuleAddressBean> query(String s,List<ModuleAddressBean> list_data){
        List<ModuleAddressBean> list = new ArrayList<>();
        String[] inputs = StringUtil.splitToWords(s);
        List<String> input_list = new ArrayList<>();
        for (int i=0;i<inputs.length;i++) {
            input_list.add(StringUtil.Stemmer.stem(inputs[i]));
        }

        List<Double> scores = new ArrayList<>(list_data.size());
        List<Double> decimator = new ArrayList<>(list_data.size());

        List<Double> w_td = new ArrayList<Double>(list_data.size());
        List<Double> w_deci = new ArrayList<Double>(list_data.size());

        double que_deci = 0.0;

        for(int i=0;i<list_data.size();i++){
            scores.add(i,0.0);
            decimator.add(i,0.0);
            w_td.add(i,0.0);
            w_deci.add(i,0.0);
        }

        for (int i=0;i<input_list.size();i++){
            String input = input_list.get(i);
            int f_tq=1;
            for (int k=i+1;k<input_list.size();){
                if(input_list.get(k).equals(input)){
                    f_tq++;
                    input_list.remove(k);
                }else{
                    k++;
                }
            }

            double w_tq = 1+Math.log10(f_tq);
            que_deci = que_deci+w_tq*w_tq;

            double df_t = 0.0;
            double w_td_right=0.0;

            for(int j=0;j<list_data.size();j++){
                if(list_data.get(j).getMap_word().get(input)!=null && list_data.get(j).getMap_word().get(input)!=0){
                    df_t++;
                }
            }
            w_td_right = Math.log10(list_data.size()/df_t);
            for(int j=0;j<w_td.size();j++){
                double value = 0.0;
                if(list_data.get(j).getMap_word().get(input)!=null && list_data.get(j).getMap_word().get(input)!=0) {
                    value = (1 + Math.log10(list_data.get(j).getMap_word().get(input)==null?1:list_data.get(j).getMap_word().get(input))) * w_td_right;
                }
                w_td.set(j, w_td.get(j)+value*w_tq);
                w_deci.set(j,w_deci.get(j)+(value*value));
            }
        }
        for(int j=0;j<w_td.size();j++){
            scores.set(j,w_td.get(j)/(Math.pow(w_deci.get(j),0.5) * Math.pow(que_deci,0.5)));
        }

        return findTopK(20,scores,list_data);
    }

    public static List<ModuleAddressBean> findTopK(int k, List<Double> scores, List<ModuleAddressBean> list_data){
        List<ModuleAddressBean> top = new ArrayList<>();
        for (int i=0;i<k;i++){
            double biggest = 0.0;
            int biggest_index = -1;
            for (int j=0;j<scores.size();j++){
                if(scores.get(j)>biggest){
                    biggest = scores.get(j);
                    biggest_index = j;
                }
            }
            if(biggest>0.0 && biggest_index >=0){
                top.add(list_data.get(biggest_index));
                System.out.println("Scores: "+scores.get(biggest_index)+" Name: "+list_data.get(biggest_index).getTitle());
                scores.set(biggest_index,0.0);
            }
        }
        return top;
    }
}
