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
    private List<Url> urls=null;
    @SerializedName("entities")
    @Expose
    private Entities entities;
    @SerializedName("extended_entities")
    @Expose
    private ExtendedEntities extendedEntities;
    @SerializedName("retweet_count")
    @Expose
    private Integer retweetCount;
    @SerializedName("favorite_count")
    @Expose
    private Integer favoriteCount;
    @SerializedName("favorited")
    @Expose
    private Boolean favorited;
    @SerializedName("retweeted")
    @Expose
    private Boolean retweeted;


    protected Tweet(Parcel in) {
        createdAt = in.readString();
        text = in.readString();
        id = in.readInt();
        retweetCount = in.readInt();
        favoriteCount = in.readInt();
        favorited = in.readByte() != 0;
        retweeted = in.readByte() != 0;

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

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public ExtendedEntities getExtendedEntities() {
        return extendedEntities;
    }

    public void setExtendedEntities(ExtendedEntities extendedEntities) {
        this.extendedEntities = extendedEntities;
    }
    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }


    public class Entities {

        @SerializedName("hashtags")
        @Expose
        private List<Hashtag> hashtags = null;
        @SerializedName("symbols")
        @Expose
        private List<Object> symbols = null;
        @SerializedName("user_mentions")
        @Expose
        private List<Object> userMentions = null;
        @SerializedName("urls")
        @Expose
        private List<Object> urls = null;
        @SerializedName("media")
        @Expose
        private List<Medium> media = null;

        public List<Hashtag> getHashtags() {
            return hashtags;
        }

        public void setHashtags(List<Hashtag> hashtags) {
            this.hashtags = hashtags;
        }

        public List<Object> getSymbols() {
            return symbols;
        }

        public void setSymbols(List<Object> symbols) {
            this.symbols = symbols;
        }

        public List<Object> getUserMentions() {
            return userMentions;
        }

        public void setUserMentions(List<Object> userMentions) {
            this.userMentions = userMentions;
        }

        public List<Object> getUrls() {
            return urls;
        }

        public void setUrls(List<Object> urls) {
            this.urls = urls;
        }

        public List<Medium> getMedia() {
            return media;
        }

        public void setMedia(List<Medium> media) {
            this.media = media;
        }

    }

    public class ExtendedEntities {

        @SerializedName("media")
        @Expose
        private List<Medium__> media = null;

        public List<Medium__> getMedia() {
            return media;
        }

        public void setMedia(List<Medium__> media) {
            this.media = media;
        }

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

    public class Hashtag {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("indices")
        @Expose
        private List<Integer> indices = null;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

    }

    public class Medium {

        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("id_str")
        @Expose
        private String idStr;
        @SerializedName("indices")
        @Expose
        private List<Integer> indices = null;
        @SerializedName("media_url")
        @Expose
        private String mediaUrl;
        @SerializedName("media_url_https")
        @Expose
        private String mediaUrlHttps;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("display_url")
        @Expose
        private String displayUrl;
        @SerializedName("expanded_url")
        @Expose
        private String expandedUrl;
        @SerializedName("type")
        @Expose
        private String type;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIdStr() {
            return idStr;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

        public String getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
            this.mediaUrl = mediaUrl;
        }

        public String getMediaUrlHttps() {
            return mediaUrlHttps;
        }

        public void setMediaUrlHttps(String mediaUrlHttps) {
            this.mediaUrlHttps = mediaUrlHttps;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public void setDisplayUrl(String displayUrl) {
            this.displayUrl = displayUrl;
        }

        public String getExpandedUrl() {
            return expandedUrl;
        }

        public void setExpandedUrl(String expandedUrl) {
            this.expandedUrl = expandedUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }



    }

    public class Medium__ {

        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("id_str")
        @Expose
        private String idStr;
        @SerializedName("indices")
        @Expose
        private List<Integer> indices = null;
        @SerializedName("media_url")
        @Expose
        private String mediaUrl;
        @SerializedName("media_url_https")
        @Expose
        private String mediaUrlHttps;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("display_url")
        @Expose
        private String displayUrl;
        @SerializedName("expanded_url")
        @Expose
        private String expandedUrl;
        @SerializedName("type")
        @Expose
        private String type;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIdStr() {
            return idStr;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
        }

        public List<Integer> getIndices() {
            return indices;
        }

        public void setIndices(List<Integer> indices) {
            this.indices = indices;
        }

        public String getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
            this.mediaUrl = mediaUrl;
        }

        public String getMediaUrlHttps() {
            return mediaUrlHttps;
        }

        public void setMediaUrlHttps(String mediaUrlHttps) {
            this.mediaUrlHttps = mediaUrlHttps;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public void setDisplayUrl(String displayUrl) {
            this.displayUrl = displayUrl;
        }

        public String getExpandedUrl() {
            return expandedUrl;
        }

        public void setExpandedUrl(String expandedUrl) {
            this.expandedUrl = expandedUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
        parcel.writeInt(favoriteCount);
        parcel.writeInt(retweetCount);
        parcel.writeByte((byte) (favorited ? 1 : 0));
        parcel.writeByte((byte) (retweeted ? 1 : 0));
    }

    public static Tweet parseJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();
        Gson gson =  new GsonBuilder().create();
             tweet = gson.fromJson(String.valueOf(jsonObject),Tweet.class);

        return  tweet;
    }
}
