package com.codepath.apps.restclienttemplate.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetItem extends RecyclerView.ViewHolder{

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

    @BindView(R.id.tvName)TextView tweetName;
    @BindView(R.id.tvUserName)TextView tweetUserName;
    @BindView(R.id.tvCreatedAt)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContent)TextView tweetContent;
    @BindView(R.id.ivProfleImage)ImageView tweetProfileImage;
    @BindView(R.id.ivURLImage) ImageView tweetUrlImage;
    @BindView(R.id.ivVerifiedUser)ImageView isVerified;





    public TweetItem(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);

    }


}
