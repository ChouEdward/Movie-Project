package com.moviesapp.project.dm_project.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.ScrollingActivity;
import com.moviesapp.project.dm_project.util.BoldSearchUtils;
import com.moviesapp.project.dm_project.util.ConstansUtil;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.util.StringUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Context context;
    private List<ModuleAddressBean> data;
    private boolean is2showOverview = false;
    private List<String>  searchQuery;

    public RecyclerviewAdapter(Context context,List<ModuleAddressBean> data, boolean is2showOverview){
        this.context = context;
        this.data = data;
        this.is2showOverview = is2showOverview;
        searchQuery = new ArrayList<>();
        listKeywordtemp = new ArrayList<>();
    }

    public void setData(List<ModuleAddressBean> data, String[] search_Query) {
        this.data = data;
        searchQuery.clear();
        for(int i=0;i<search_Query.length;i++){
            boolean flag = false;
            for (int j=0;j<searchQuery.size();j++){
                if(searchQuery.get(j).equals(search_Query[i])){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                if(search_Query[i].toLowerCase().contains("toy")){
                    searchQuery.add(search_Query[i]);
                }
                else{
                    searchQuery.add(StringUtil.Stemmer.stem(search_Query[i]));
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(is2showOverview) {
            boolean flag = false;
            for (String searchitem:
                    searchQuery) {
                if(data.get(position).getTitle().toLowerCase().contains(searchitem)){
                    StringBuilder stringBuilder = new StringBuilder("");
                    stringBuilder.append(searchitem.length()>0?searchitem.charAt(0):"");
                    String answers = stringBuilder.toString();
                    if(searchitem.length()>1)
                    answers = answers.toUpperCase()+searchitem.substring(1,searchitem.length());
                    holder.name.setText(BoldSearchUtils.changeSearchContentStyle(data.get(position).getTitle(), answers));
                    flag = true;
                    break;
                }
            }
            if(!flag){
                holder.name.setText(data.get(position).getTitle());
            }
        }else{
            holder.name.setText(data.get(position).getTitle());
        }
        Picasso.get().load(ConstansUtil.POSTER_URL+data.get(position).getPosterPath()).into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.putExtra("id",data.get(position).getId());
                context.startActivity(intent);
            }
        });

        searchQuery = putKeywordFirst(searchQuery, data.get(position).getOverview());

        if(is2showOverview && searchQuery.size()>0){
            StringBuilder query = new StringBuilder(" " + searchQuery.get(0));
            String overview = data.get(position).getOverview();
            if(overview.contains(query.toString())){
                int end = overview.indexOf(query.toString()) + query.length();
                for (int i=end;i<overview.length();i++){
                    if(overview.charAt(i)!=' '){
                        query.append(overview.charAt(i));
                    }else{
                        break;
                    }
                }
            }
            String content = cutString(data.get(position).getOverview(), query.toString());
            if(content.isEmpty()){
                if(data.get(position).getOverview().toLowerCase().contains(query.toString())) {
                    StringBuilder stringBuilder = new StringBuilder("");
                    stringBuilder.append(query.toString().length() > 0 ? query.toString().charAt(0) : "");
                    stringBuilder.append(query.toString().charAt(1));
                    String answers = stringBuilder.toString();
                    if (query.toString().length() > 1)
                        answers = answers.toUpperCase() + query.toString().substring(2, query.toString().length());
                    String uppercontent = cutString(data.get(position).getOverview(), answers);
                    if(!uppercontent.isEmpty()){
                        holder.overview.setVisibility(View.VISIBLE);
                        holder.overview.setText(BoldSearchUtils.changeSearchContentStyle(uppercontent,
                                answers));
                    }else holder.overview.setVisibility(View.GONE);
                }else holder.overview.setVisibility(View.GONE);
            }
            else {
                holder.overview.setVisibility(View.VISIBLE);
                holder.overview.setText(BoldSearchUtils.changeSearchContentStyle(content,
                        query.toString()));
            }


            if(searchQuery.size()>1){
                StringBuilder query2 = new StringBuilder(" " + searchQuery.get(1));
                String overview2 = data.get(position).getOverview();
                if(overview2.contains(query2.toString())){
                    int end = overview2.indexOf(query2.toString()) + query2.length();
                    for (int i=end;i<overview2.length();i++){
                        if(overview2.charAt(i)!=' '){
                            query2.append(overview2.charAt(i));
                        }else{
                            break;
                        }
                    }
                }
                String content2 = cutString(data.get(position).getOverview(), query2.toString());
                if(content2.isEmpty()){
                    if(data.get(position).getOverview().toLowerCase().contains(query2.toString())) {
                        StringBuilder stringBuilder = new StringBuilder("");
                        stringBuilder.append(query2.toString().length() > 0 ? query2.toString().charAt(0) : "");
                        stringBuilder.append(query2.toString().charAt(1));
                        String answers2 = stringBuilder.toString();
                        if (query2.toString().length() > 1)
                            answers2 = answers2.toUpperCase() + query2.toString().substring(2, query2.toString().length());
                        String uppercontent2 = cutString(data.get(position).getOverview(), answers2);
                        if(!uppercontent2.isEmpty()){
                            holder.overview2.setVisibility(View.VISIBLE);
                            holder.overview2.setText(BoldSearchUtils.changeSearchContentStyle(uppercontent2,
                                    answers2));
                        }else holder.overview2.setVisibility(View.GONE);
                    }else holder.overview2.setVisibility(View.GONE);
                    holder.overview2.setVisibility(View.GONE);
                }
                else {
                    holder.overview2.setVisibility(View.VISIBLE);
                    holder.overview2.setText(BoldSearchUtils.changeSearchContentStyle(content2,
                            query2.toString()));
                }
            }else{
                holder.overview2.setVisibility(View.GONE);
            }
        }

    }
    private List<String> listKeywordtemp;
    private List<String> putKeywordFirst(List<String> list, String overview){
        int count=0;
        for(int i=0;i<list.size();i++){
            if(overview.contains(list.get(i))&&count<list.size()){
                    Collections.swap(list,count++,i);
            }
        }
        return list;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String cutString(String str, String search){
        int start = 0;
        int end = str.length()-1;
        String answer ="";
        if(str.contains(search)){
            start  = str.indexOf(search);
            end = start+search.length();
            start = (start-15)<=0?0:(start-15);
            end = (end+80)>=str.length()?str.length():(end+80);//20
            answer = str.substring(start,end);
            if(start!=0){
                answer = "..."+answer;
            }
            if(end<str.length()-1){
                answer = answer+"...";
            }

        }
        return answer;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private ImageView icon;
        private TextView overview;
        private TextView overview2;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.movie_name);
            icon = itemView.findViewById(R.id.icon);
            overview = itemView.findViewById(R.id.overview);
            overview2 = itemView.findViewById(R.id.overview2);
        }
    }
}
