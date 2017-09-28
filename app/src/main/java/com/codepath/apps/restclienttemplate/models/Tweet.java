package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dipenrana on 9/26/17.
 */

public class Tweet implements Parcelable{

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = null;





    protected Tweet(Parcel in) {
        createdAt = in.readString();
        text = in.readString();
        id = in.readInt();

    }

    public  Tweet(){

    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class Url {

        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("expanded_url")
        @Expose
        private String expandedUrl;
        @SerializedName("display_url")
        @Expose
        private String displayUrl;
        @SerializedName("indices")
        @Expose
        private List<Integer> indices = null;


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExpandedUrl() {
            return expandedUrl;
        }

        public void setExpandedUrl(String expandedUrl) {
            this.expandedUrl = expandedUrl;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public void setDisplayUrl(String displayUrl) {
            this.displayUrl = displayUrl;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdAt);
        parcel.writeString(text);
        parcel.writeLong(id);

    }

    public static Tweet parseJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();
        Gson gson =  new GsonBuilder().create();
             tweet = gson.fromJson(String.valueOf(jsonObject),Tweet.class);

        return  tweet;
    }
}
