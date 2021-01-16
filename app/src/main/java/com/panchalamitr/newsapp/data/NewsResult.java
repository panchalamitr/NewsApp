
package com.panchalamitr.newsapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

public class NewsResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("isHosted")
    @Expose
    private Boolean isHosted;
    @SerializedName("pillarId")
    @Expose
    private String pillarId;
    @SerializedName("pillarName")
    @Expose
    private String pillarName;

    protected NewsResult(Parcel in) {
        id = in.readString();
        type = in.readString();
        sectionId = in.readString();
        sectionName = in.readString();
        webPublicationDate = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        apiUrl = in.readString();
        byte tmpIsHosted = in.readByte();
        isHosted = tmpIsHosted == 0 ? null : tmpIsHosted == 1;
        pillarId = in.readString();
        pillarName = in.readString();
    }

    public static final Creator<NewsResult> CREATOR = new Creator<NewsResult>() {
        @Override
        public NewsResult createFromParcel(Parcel in) {
            return new NewsResult(in);
        }

        @Override
        public NewsResult[] newArray(int size) {
            return new NewsResult[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Boolean getIsHosted() {
        return isHosted;
    }

    public void setIsHosted(Boolean isHosted) {
        this.isHosted = isHosted;
    }

    public String getPillarId() {
        return pillarId;
    }

    public void setPillarId(String pillarId) {
        this.pillarId = pillarId;
    }

    public String getPillarName() {
        return pillarName;
    }

    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(sectionId);
        dest.writeString(sectionName);
        dest.writeString(webPublicationDate);
        dest.writeString(webTitle);
        dest.writeString(webUrl);
        dest.writeString(apiUrl);
        dest.writeByte((byte) (isHosted == null ? 0 : isHosted ? 1 : 2));
        dest.writeString(pillarId);
        dest.writeString(pillarName);
    }

}
