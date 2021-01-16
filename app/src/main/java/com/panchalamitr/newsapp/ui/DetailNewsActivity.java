package com.panchalamitr.newsapp.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.panchalamitr.newsapp.data.NewsResult;
import com.panchalamitr.newsapp.databinding.ActivityDetailViewBinding;

import timber.log.Timber;

public class DetailNewsActivity extends BaseActivity {

    ActivityDetailViewBinding activityDetailViewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailViewBinding = ActivityDetailViewBinding.inflate(getLayoutInflater());
        setContentView(activityDetailViewBinding.getRoot());
        activityDetailViewBinding.webView.getSettings().setJavaScriptEnabled(true);

        initProgressBar(activityDetailViewBinding.pbDetailView);

        NewsResult newsResult = getIntent().getParcelableExtra("detail");
        if (newsResult != null) {
            Timber.d(newsResult.getApiUrl());
            activityDetailViewBinding.webView.loadUrl(newsResult.getWebUrl());
        } else {
            Toast.makeText(DetailNewsActivity.this, "Error Loading Page...", Toast.LENGTH_LONG).show();
        }


        activityDetailViewBinding.webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgressBar();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgressBar();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError error) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                Toast.makeText(DetailNewsActivity.this, error.getDescription(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
