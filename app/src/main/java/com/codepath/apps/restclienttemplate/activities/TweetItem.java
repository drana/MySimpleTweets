package com.codepath.apps.restclienttemplate.activities;

import android.graphics.Typeface;
import android.media.Image;
import android.support.design.widget.Snackbar;
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
import com.loopj.android.http.AsyncHttpResponseHandler;
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

    public TextView getTweetFavoritesCount() {
        return tweetFavoritesCount;
    }

    public TextView getTweetReTweetCount() {
        return tweetReTweetCount;
    }

    public TextView getTweetReplyText() {
        return tweetReplyText;
    }

    public void setTweetFavoritesCount(TextView tweetFavoritesCount) {
        this.tweetFavoritesCount = tweetFavoritesCount;
    }

    public void setTweetReTweetCount(TextView tweetReTweetCount) {
        this.tweetReTweetCount = tweetReTweetCount;
    }

    public void setTweetReplyText(TextView tweetReplyText) {
        this.tweetReplyText = tweetReplyText;
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
    @BindView(R.id.tvFavorites)TextView tweetFavoritesCount;
    @BindView(R.id.tvReTweet)TextView tweetReTweetCount;
    @BindView(R.id.tvReply)TextView tweetReplyText;

    //endregion

    public TweetItem(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);
        client = TwitterApp.getRestClient();

        // Create the TypeFace from the TTF asset
        Typeface boldFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue-Bold.ttf");
        Typeface regularFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        // Assign the typeface to the view
        tweetName.setTypeface(boldFont);
        tweetUserName.setTypeface(regularFont);
        tweetCreatedAt.setTypeface(regularFont);
        tweetContent.setTypeface(regularFont);
        tweetFavoritesCount.setTypeface(regularFont);
        tweetReTweetCount.setTypeface(regularFont);
        tweetReplyText.setTypeface(regularFont);

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
//            case R.id.ivProfleImage:
//                Log.d("click","fav");
//                break;
        }

    }



    private void toggleReply() {
    }

    private void toggleReTweet() {
        Boolean reTweeted = tweet.getRetweeted();

        if(!reTweeted){
            onReTweet(true);
        }
        else if (reTweeted){
            onReTweet(false);
        }

    }

    private void onReTweet(final Boolean reTweeted){
        long id = tweet.getId();
        client.postRetweet(id,reTweeted,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweet.setRetweeted(updatedTweet.getRetweeted());
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


//    private void onUnReTweet() {
//
//        long id = tweet.getId();
//        client.postRetweet(id,false,new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                try {
//                    Tweet updatedTweet = Tweet.parseJson(response);
//                    tweet.setRetweeted(updatedTweet.getRetweeted());
//                    UpdateUI(updatedTweet);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//        });
//
//    }
//
//    private void onRetweet() {
//
//        long id = tweet.getId();
//        client.postRetweet(id,true,new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                try {
//                    Tweet updatedTweet = Tweet.parseJson(response);
//                    tweet.setRetweeted(updatedTweet.getRetweeted());
//                    UpdateUI(updatedTweet);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//        });
//    }

    private void toggleFavorites() {
        Boolean favourited = tweet.getFavorited();

            if(!favourited){
                onFavorited();
            }
            else if (favourited){
                    onUnfavourited();
            }

    }

    private void onUnfavourited() {
        long id = tweet.getId();
        client.postFavorite(id,false,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweet.setFavorited(updatedTweet.getFavorited());
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

        long id = tweet.getId();

        client.postFavorite(id,true,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Tweet updatedTweet = Tweet.parseJson(response);
                    tweet.setFavorited(updatedTweet.getFavorited());
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
        tweetReTweetCount.setText(Integer.toString(updatedTweet.getRetweetCount()));


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
