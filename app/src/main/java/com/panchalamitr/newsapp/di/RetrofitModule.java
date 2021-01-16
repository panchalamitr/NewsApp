package com.panchalamitr.newsapp.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.panchalamitr.newsapp.data.News;
import com.panchalamitr.newsapp.retrofit.NewsRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.util.Log.VERBOSE;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    @Singleton
    @Provides
    public Gson provideGsonBuilder() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Singleton
    @Provides
    public Retrofit.Builder provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://content.guardianapis.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttp());
    }

    @Singleton
    @Provides
    public NewsRetrofit provideNews(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(NewsRetrofit.class);
    }

    public OkHttpClient getOkHttp() {
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(VERBOSE)
                .build();

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

}
