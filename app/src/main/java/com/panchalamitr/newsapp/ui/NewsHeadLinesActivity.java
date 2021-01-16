package com.panchalamitr.newsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panchalamitr.newsapp.adapter.NewsHeadLinesAdapter;
import com.panchalamitr.newsapp.data.NewsResult;
import com.panchalamitr.newsapp.databinding.ActivityNewsHeadlineBinding;
import com.panchalamitr.newsapp.listener.OnItemClickListener;
import com.panchalamitr.newsapp.repository.NewsHeadLinesRepository;
import com.panchalamitr.newsapp.viewmodel.NewsHeadLinesViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsHeadLinesActivity extends BaseActivity implements OnItemClickListener {

    private NewsHeadLinesViewModel newsHeadLineViewModel;
    private ArrayList<NewsResult> arrayListNewsResult;
    NewsHeadLinesAdapter newsHeadLinesAdapter;

    @Inject
    NewsHeadLinesRepository newsHeadLinesRepository;

    ActivityNewsHeadlineBinding activityNewsHeadlineBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewsHeadlineBinding = ActivityNewsHeadlineBinding.inflate(getLayoutInflater());
        setContentView(activityNewsHeadlineBinding.getRoot());

        activityNewsHeadlineBinding.rvNewsHeadLine.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        arrayListNewsResult = new ArrayList<>();
        newsHeadLinesAdapter = new NewsHeadLinesAdapter(this, arrayListNewsResult, this);
        activityNewsHeadlineBinding.rvNewsHeadLine.setAdapter(newsHeadLinesAdapter);

        newsHeadLineViewModel = new NewsHeadLinesViewModel(newsHeadLinesRepository);

        initProgressBar(activityNewsHeadlineBinding.pbNewsHeadLine);

        /** Observe HeadLines **/
        newsHeadLineViewModel.getNewsHeadLines().observe(this, repos -> {
            arrayListNewsResult.clear();
            arrayListNewsResult.addAll(repos);
            newsHeadLinesAdapter.notifyDataSetChanged();
        });

        /** Observe Loading process **/
        newsHeadLineViewModel.getLoadingLiveData().observe(this, repos -> {
            if (repos) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });

        /** Observe Errors **/
        newsHeadLineViewModel.getErrorLiveData().observe(this, repos -> {
            if (repos)
                Toast.makeText(this, "Error loading response", Toast.LENGTH_LONG).show();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        newsHeadLineViewModel.fetchNewsHeadlines();
    }

    @Override
    public void onItemClick(Object object) {
        NewsResult newsResult = (NewsResult)object;
        Intent intent = new Intent(NewsHeadLinesActivity.this, DetailNewsActivity.class);
        intent.putExtra("detail", newsResult);
        startActivity(intent);
    }
}
