package com.panchalamitr.newsapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.panchalamitr.newsapp.MainApplication;
import com.panchalamitr.newsapp.R;
import com.panchalamitr.newsapp.databinding.ActivityNewsHeadlineBinding;
import com.panchalamitr.newsapp.di.RetrofitModule;
import com.panchalamitr.newsapp.repository.NewsHeadLinesRepository;
import com.panchalamitr.newsapp.viewmodel.NewsHeadLinesViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsHeadLinesActivity extends BaseActivity {

    private NewsHeadLinesViewModel newsHeadLineViewModel;

    @Inject
    NewsHeadLinesRepository newsHeadLinesRepository;

    NewsHeadLinesViewModel newsHeadLinesViewModel;

    ActivityNewsHeadlineBinding activityNewsHeadlineBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_headline);

        newsHeadLineViewModel = new NewsHeadLinesViewModel((MainApplication)getApplication(), newsHeadLinesRepository);
        newsHeadLineViewModel.getLoadingLiveData().observe(this, repos -> {

        });
        newsHeadLineViewModel.getErrorLiveData().observe(this, repos -> {
            if(repos != null) Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        });
        newsHeadLineViewModel.getNewsHeadLines().observe(this, repos -> {
            if(repos != null) Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show();
        });

    }

}
