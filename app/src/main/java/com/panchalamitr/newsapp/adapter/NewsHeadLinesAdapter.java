package com.panchalamitr.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.panchalamitr.newsapp.R;
import com.panchalamitr.newsapp.data.NewsResult;
import com.panchalamitr.newsapp.listener.OnItemClickListener;

import java.util.ArrayList;

public class NewsHeadLinesAdapter extends RecyclerView.Adapter<NewsHeadLinesAdapter.ViewHolder> {

    private ArrayList<NewsResult> newsResultArrayList;
    private OnItemClickListener onItemClickListener;

    public NewsHeadLinesAdapter(Context context, ArrayList<NewsResult> newsResults, OnItemClickListener listener) {
        this.newsResultArrayList = newsResults;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_headline_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rlNewsHeadLine.setTag(holder.rlNewsHeadLine.getId(), newsResultArrayList.get(position));
        holder.tvTitle.setText(newsResultArrayList.get(position).getWebTitle());
    }

    @Override
    public int getItemCount() {
        return newsResultArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RelativeLayout rlNewsHeadLine;

        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            rlNewsHeadLine = (RelativeLayout) view.findViewById(R.id.rlNewsHeadLine);

            rlNewsHeadLine.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    NewsResult newsResult = ((NewsResult)v.getTag(v.getId()));
                    onItemClickListener.onItemClick(newsResult);
                }
            });
        }
    }

}
