
package com.panchalamitr.newsapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("response")
    @Expose
    private NewsResponse newsResponse;

    public NewsResponse getNewsResponse() {
        return newsResponse;
    }

    public void setNewsResponse(NewsResponse newsResponse) {
        this.newsResponse = newsResponse;
    }

}
