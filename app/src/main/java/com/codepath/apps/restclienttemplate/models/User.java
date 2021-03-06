package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by dipenrana on 9/26/17.
 */

public class User implements Parcelable{


    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("screen_name")
    @Expose
    private String screenName;
    @SerializedName("profile_image_url")
    @Expose
    private String profileImageUrl;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("friends_count")
    @Expose
    private Integer friendsCount;
    @SerializedName("profile_banner_url")
    @Expose
    private String profileBannerUrl;

    //region get & set

    //Constructor
    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Integer friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public void setProfileBannerUrl(String profileBannerUrl) {
        this.profileBannerUrl = profileBannerUrl;
    }

    //endregion

    protected User(Parcel in) {
        name = in.readString();
        screenName = in.readString();
        id = in.readLong();
        profileImageUrl = in.readString();
        profileBannerUrl = in.readString();
        verified = in.readByte() != 0;
        followersCount = in.readInt();
        friendsCount = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(screenName);
        parcel.writeLong(id);
        parcel.writeString(profileImageUrl);
        parcel.writeString(profileBannerUrl);
        parcel.writeByte((byte) (verified ? 1 : 0));
        parcel.writeInt(followersCount);
        parcel.writeInt(friendsCount);
    }

    public static User getUserfromJSON(JSONObject jsonObject) throws JSONException {

        User user = new User();
        Gson gson =  new GsonBuilder().create();
        user = gson.fromJson(String.valueOf(jsonObject),User.class);

        return  user;
    }
}
