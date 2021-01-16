package com.panchalamitr.newsapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.panchalamitr.newsapp.MainApplication;
import com.panchalamitr.newsapp.data.News;
import com.panchalamitr.newsapp.repository.NewsHeadLinesRepository;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsHeadLinesViewModel extends ViewModelProvider.NewInstanceFactory {

    private NewsHeadLinesRepository mNewsHeadLinesRepository;

    private CompositeDisposable disposable;
    MutableLiveData<News> newsLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();

    public NewsHeadLinesViewModel(MainApplication mainApplication, NewsHeadLinesRepository newsHeadLinesRepository) {
        Timber.d("Constructor Called");
        disposable = new CompositeDisposable();
        mNewsHeadLinesRepository = newsHeadLinesRepository;
        fetchNewsHeadlines();
    }

    private void fetchNewsHeadlines() {
        loadingLiveData.setValue(true);
        disposable.add(mNewsHeadLinesRepository.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<News>() {

                    @Override
                    public void onSuccess(News value) {
                        newsLiveData.setValue(value);
                        loadingLiveData.setValue(false);
                        errorLiveData.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(true);
                        loadingLiveData.setValue(false);
                    }
                }));

    }

     public MutableLiveData<News> getNewsHeadLines() {
        return newsLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public MutableLiveData<Boolean> getErrorLiveData() {
        return errorLiveData;
    }
}
