package com.panchalamitr.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.panchalamitr.newsapp.R;
import com.panchalamitr.newsapp.data.NewsResult;

import java.util.ArrayList;

public class NewsHeadLinesAdapter {
    public static class NewsHeadLinesAdapter extends RecyclerView.Adapter<NewsHeadLinesAdapter.ViewHolder> {

        private Context context;
        private ArrayList<NewsResult> newsResultArrayList;

        public NewsHeadLinesAdapter(Context context, ArrayList<NewsResult> newsResults){
            this.context = context;
            this.newsResultArrayList = newsResults;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_headline_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View view) {
                super(view);
            }
        }
    }
}
