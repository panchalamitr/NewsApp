package com.panchalamitr.newsapp.retrofit;

import com.panchalamitr.newsapp.data.News;

import dagger.Module;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRetrofit {

    @GET("search")
    Single<News> getNews(@Query("api-key") String apiKey);
}
