package com.codepath.apps.restclienttemplate.activities;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
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

public class TweetItemNoImage extends RecyclerView.ViewHolder{

    TwitterClient client;
    public Tweet tweetPlain;

    public TextView getTweetName() {
        return tweetNamePlain;
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

    public ImageView getIvVerifiedUserPlain() {
        return ivVerifiedUserPlain;
    }

    public void setIvVerifiedUserPlain(ImageView ivVerifiedUserPlain) {
        this.ivVerifiedUserPlain = ivVerifiedUserPlain;
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

    public TextView getTweetFavoritesCount() {
        return tweetFavoritesCount;
    }

    public TextView getTweetRetweetCount() {
        return tweetRetweetCount;
    }

    public TextView getTweetReplyText() {
        return tweetReplyText;
    }

    public void setTweetFavoritesCount(TextView tweetFavoritesCount) {
        this.tweetFavoritesCount = tweetFavoritesCount;
    }

    public void setTweetRetweetCount(TextView tweetRetweetCount) {
        this.tweetRetweetCount = tweetRetweetCount;
    }

    public void setTweetReplyText(TextView tweetReplyText) {
        this.tweetReplyText = tweetReplyText;
    }

    @BindView(R.id.ibFavoritesPlain)ImageButton tweetFavorites;
    @BindView(R.id.ibReTweetPlain)ImageButton tweetReTweet;
    @BindView(R.id.ibReplyPlain)ImageButton tweetReply;
    @BindView(R.id.tvNamePlain)TextView tweetNamePlain;
    @BindView(R.id.tvUserNamePlain)TextView tweetUserName;
    @BindView(R.id.tvCreatedAtPlain)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContentPlain)TextView tweetContent;
    @BindView(R.id.ivProfleImagePlain)ImageView tweetProfileImage;
    @BindView(R.id.ivVerifiedUserPlain)ImageView ivVerifiedUserPlain;
    @BindView(R.id.tvFavoritesPlain)TextView tweetFavoritesCount;
    @BindView(R.id.tvReTweetPlain)TextView tweetRetweetCount;
    @BindView(R.id.tvReplyPlain)TextView tweetReplyText;


    public TweetItemNoImage(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);
        client = TwitterApp.getRestClient();

        // Create the TypeFace from the TTF asset
        Typeface boldFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue-Bold.ttf");
        Typeface regularFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        // Assign the typeface to the view
        tweetNamePlain.setTypeface(boldFont);
        tweetUserName.setTypeface(regularFont);
        tweetCreatedAt.setTypeface(regularFont);
        tweetContent.setTypeface(regularFont);
        tweetFavoritesCount.setTypeface(regularFont);
        tweetRetweetCount.setTypeface(regularFont);
        tweetReplyText.setTypeface(regularFont);

    }

    @OnClick({ R.id.ibFavoritesPlain, R.id.ibReplyPlain, R.id.ibReTweetPlain})//,R.id.ivProfleImagePlain})
    public void onTweetItemNoImageClick(View view){
        switch (view.getId()) {
            case R.id.ibFavoritesPlain:
                toggleFavorites();
                Log.d("click","fav");
                break;
            case R.id.ibReTweetPlain:
                toggleReTweet();
                Log.d("click","fav");
                break;
            case R.id.ibReplyPlain:
                Log.d("click","fav");
                break;
//            case R.id.ivProfleImagePlain:
//                Log.d("click","fav");
//                break;
        }

    }

    private void toggleReTweet() {
        Boolean reTweeted = tweetPlain.getRetweeted();

        if(!reTweeted){
            onReTweet(true);
        }
        else if (reTweeted){
            onReTweet(false);
        }

    }

    private void onReTweet(final Boolean reTweeted){
        long id = tweetPlain.getId();
        client.postRetweet(id,reTweeted,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweetPlain.setRetweeted(updatedTweet.getRetweeted());
                    UpdateUI(updatedTweet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void toggleFavorites() {
        Boolean favourited = tweetPlain.getFavorited();

        if(!favourited){
            onFavorited();
        }
        else if (favourited){
            onUnfavourited();
        }

    }

    private void onUnfavourited() {
        long id = tweetPlain.getId();
        client.postFavorite(id,false,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweetPlain.setFavorited(updatedTweet.getFavorited());
                    UpdateUI(updatedTweet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void onFavorited() {

        long id = tweetPlain.getId();

        client.postFavorite(id,true,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweetPlain.setFavorited(updatedTweet.getFavorited());
                    UpdateUI(updatedTweet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void UpdateUI(Tweet updatedTweet) {
        if(updatedTweet.getFavorited()){
            tweetFavorites.setImageResource(R.drawable.ic_favorite_true);
        }
        else if(!updatedTweet.getFavorited()){
            tweetFavorites.setImageResource(R.drawable.ic_favorite_border);
        }
        if(updatedTweet.getRetweeted()){
            tweetReTweet.setImageResource(R.drawable.ic_repeat_true);
        }
        else if(!updatedTweet.getRetweeted()){
            tweetReTweet.setImageResource(R.drawable.ic_repeat);
        }
        tweetFavoritesCount.setText(Integer.toString(updatedTweet.getFavoriteCount()));
        tweetRetweetCount.setText(Integer.toString(updatedTweet.getRetweetCount()));
    }

}
