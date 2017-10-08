package com.codepath.apps.restclienttemplate.activities;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.fragments.ComposeTweetFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetItem extends RecyclerView.ViewHolder {

    TwitterClient client;
    public Tweet tweet;

    //region get set

    public TextView getTweetName() {
        return tweetName;
    }

    public TextView getTweetUserName() {
        return tweetUserName;
    }

    public TextView getTweetCreatedAt() {
        return tweetCreatedAt;
    }

    public TextView getTweetContent() {
        return tweetContent;
    }

    public ImageView getTweetProfileImage() {
        return tweetProfileImage;
    }

    public ImageView getTweetUrlImage() {
        return tweetUrlImage;
    }

    public ImageView getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(ImageView isVerified) {
        this.isVerified = isVerified;
    }

    public ImageButton getTweetFavorites() {
        return tweetFavorites;
    }

    public ImageButton getTweetReTweet() {
        return tweetReTweet;
    }

    public ImageButton getTweetReply() {
        return tweetReply;
    }

    public void setTweetFavorites(ImageButton tweetFavorites) {
        this.tweetFavorites = tweetFavorites;
    }

    public void setTweetReTweet(ImageButton tweetReTweet) {
        this.tweetReTweet = tweetReTweet;
    }

    public void setTweetReply(ImageButton tweetReply) {
        this.tweetReply = tweetReply;
    }

    @BindView(R.id.tvName)TextView tweetName;
    @BindView(R.id.tvUserName)TextView tweetUserName;
    @BindView(R.id.tvCreatedAt)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContent)TextView tweetContent;
    @BindView(R.id.ivProfleImage)ImageView tweetProfileImage;
    @BindView(R.id.ivURLImage) ImageView tweetUrlImage;
    @BindView(R.id.ivVerifiedUser)ImageView isVerified;
    @BindView(R.id.ibFavorites)ImageButton tweetFavorites;
    @BindView(R.id.ibReTweet)ImageButton tweetReTweet;
    @BindView(R.id.ibReply)ImageButton tweetReply;

    //endregion

    public TweetItem(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);
        client = TwitterApp.getRestClient();

    }

    @OnClick({ R.id.ibFavorites, R.id.ibReply, R.id.ibReTweet })
    public void onTweetItemClick(View view){
        switch (view.getId()) {
            case R.id.ibFavorites:
                toggleFavorites();
                Log.d("click","fav");
                break;
            case R.id.ibReTweet:
                toggleReTweet();
                Log.d("click","fav");
                break;
            case R.id.ibReply:
                toggleReply();
                Log.d("click","fav");
                break;
            case R.id.ivProfleImage:
                Log.d("click","fav");
                break;
        }

    }

    private void toggleReply() {
    }

    private void toggleReTweet() {

    }

    private void toggleFavorites() {

//        TwitterApp.getRestClient().favorite(uid, favorited ^= true, handler);
//        favoriteCount += favorited ? 1 : -1;
            Boolean favourited = tweet.getFavorited();
            long id = tweet.getId();
            if(!favourited){
                client.postFavorite(id,favourited ^=true,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            Tweet updatedTweet = Tweet.parseJson(response);
                            UpdateUI(updatedTweet);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Tweet newTweet = Tweet.parseJson(response);
//                        dismiss();
//                        ComposeTweetFragment.TweetDialogListener mListener = (ComposeTweetFragment.TweetDialogListener) getActivity();
//                        mListener.onPostNewTweet(newTweet);
//                        Log.d("New Tweet","tweet send to activity");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }

    }

    private void UpdateUI(Tweet updatedTweet) {
        if(updatedTweet.getFavorited()){
            tweetFavorites.setImageResource(R.drawable.ic_favorite_true);
        }
        else if(!updatedTweet.getFavorited()){

        }
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.ibFavorites:
//                Log.d("click","fav");
//                break;
//            case R.id.ibReTweet:
//                Log.d("click","fav");
//                break;
//            case R.id.ibReply:
//                Log.d("click","fav");
//                break;
//            case R.id.ivProfleImage:
//                Log.d("click","fav");
//                break;
//        }
//    }
}
