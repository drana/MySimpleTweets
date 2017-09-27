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

/**
 * Created by dipenrana on 9/26/17.
 */

public class Tweet implements Parcelable{

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("text")
    @Expose
    private String text;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdAt);
        parcel.writeString(text);
        parcel.writeInt(id);
    }

    public static Tweet parseJson(String responseData) throws JSONException {
        Tweet tweet = new Tweet();

        Gson gson = new GsonBuilder().create();
//        tweet.setText(jsonObject.getString("text"));
//        tweet.setId(jsonObject.getInt("id"));
//        tweet.setCreatedAt(jsonObject.getString("created_at"));

        tweet = gson.fromJson(responseData,Tweet.class);

        return  tweet;
    }
}
