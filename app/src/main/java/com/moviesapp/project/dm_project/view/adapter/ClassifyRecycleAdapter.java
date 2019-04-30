package com.moviesapp.project.dm_project.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.data.ClassifierBase;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;

import java.text.DecimalFormat;
import java.util.List;

public class ClassifyRecycleAdapter extends RecyclerView.Adapter<ClassifyRecycleAdapter.ClassifyViewHolder>  {

    private Context context;

    private List<ClassifierBase> data;

    public ClassifyRecycleAdapter(Context context,List<ClassifierBase> data){
        this.context = context;
        this.data = data;
    }

    public void setData(List<ClassifierBase> data) {
        this.data = data;
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classifier_item,parent,false);
        return new ClassifyRecycleAdapter.ClassifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifyViewHolder holder, int position) {

        holder.genre.setText(data.get(position).getGenres());
        if(data.get(position).getScore()>0.00000000001) {
            DecimalFormat df = new DecimalFormat("#0.00000000000");
            holder.score.setText(String.valueOf(df.format(data.get(position).getScore())));
        }else{
            holder.score.setText(String.valueOf(data.get(position).getScore()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ClassifyViewHolder extends RecyclerView.ViewHolder{

        private TextView genre;
        private TextView score;

        public ClassifyViewHolder(View itemView) {
            super(itemView);
            genre = itemView.findViewById(R.id.genre);
            score = itemView.findViewById(R.id.score);
        }
    }
}
