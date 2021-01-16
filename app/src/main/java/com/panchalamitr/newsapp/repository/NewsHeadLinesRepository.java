package com.panchalamitr.newsapp.repository;

import com.panchalamitr.newsapp.data.News;
import com.panchalamitr.newsapp.retrofit.NewsRetrofit;


import javax.inject.Inject;

import io.reactivex.Single;

public class NewsHeadLinesRepository {

    private NewsRetrofit mNewsRetrofit;

    @Inject
    public NewsHeadLinesRepository(NewsRetrofit newsRetrofit){
        mNewsRetrofit = newsRetrofit;
    }

    public Single<News> getNews(){
        String apiKey = "4f7de66d-f619-4097-9ebd-acb0750e85bb";
        return mNewsRetrofit.getNews(apiKey);
    }
}
