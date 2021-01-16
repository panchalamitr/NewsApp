package com.panchalamitr.newsapp.repository;

import com.panchalamitr.newsapp.data.News;
import com.panchalamitr.newsapp.retrofit.NewsRetrofit;
import com.panchalamitr.newsapp.util.Constant;

import javax.inject.Inject;

import io.reactivex.Single;

public class NewsHeadLinesRepository {

    private final NewsRetrofit mNewsRetrofit;

    @Inject
    public NewsHeadLinesRepository(NewsRetrofit newsRetrofit) {
        mNewsRetrofit = newsRetrofit;
    }

    public Single<News> getNews() {
        return mNewsRetrofit.getNews(Constant.API_KEY);
    }
}
