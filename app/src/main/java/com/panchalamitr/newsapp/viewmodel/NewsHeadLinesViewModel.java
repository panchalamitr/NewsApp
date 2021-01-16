package com.panchalamitr.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.panchalamitr.newsapp.MainApplication;
import com.panchalamitr.newsapp.data.News;
import com.panchalamitr.newsapp.data.NewsResponse;
import com.panchalamitr.newsapp.data.NewsResult;
import com.panchalamitr.newsapp.repository.NewsHeadLinesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsHeadLinesViewModel extends ViewModel {

    private NewsHeadLinesRepository mNewsHeadLinesRepository;

    private CompositeDisposable disposable;

    MutableLiveData<ArrayList<NewsResult>> newsLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();

    public NewsHeadLinesViewModel(NewsHeadLinesRepository newsHeadLinesRepository) {
        disposable = new CompositeDisposable();
        mNewsHeadLinesRepository = newsHeadLinesRepository;
    }

    public void fetchNewsHeadlines() {
        Timber.d("Fetching updated list...");
        loadingLiveData.setValue(true);
        disposable.add(mNewsHeadLinesRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<News>() {

                    @Override
                    public void onSuccess(News news) {
                        ArrayList<NewsResult> newsResultArrayList = new ArrayList<>();

                        /** Sort by published Date **/
                        List<NewsResult> newsResultList = news.getNewsResponse().getNewsResults();
                        /** Only fetch 5 records **/
                        for (int i = 0; i < 5; i++) {
                            if(i < newsResultList.size() - 1) {
                                newsResultArrayList.add(newsResultList.get(i));
                            }
                        }

                        newsLiveData.setValue(newsResultArrayList);
                        loadingLiveData.setValue(false);
                        errorLiveData.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        errorLiveData.setValue(true);
                        loadingLiveData.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    public MutableLiveData<ArrayList<NewsResult>> getNewsHeadLines() {
        return newsLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public MutableLiveData<Boolean> getErrorLiveData() {
        return errorLiveData;
    }
}
