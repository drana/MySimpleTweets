package com.codepath.apps.restclienttemplate.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.codepath.apps.restclienttemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dipenrana on 10/1/17.
 */

public class TweetItemVideo extends RecyclerView.ViewHolder {

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

    public ImageView getIsVerified() {
        return isVerified;
    }

    public VideoView getTweetVideo() {
        return tweetVideo;
    }

    @BindView(R.id.tvNameVV)TextView tweetName;
    @BindView(R.id.tvUserNameVV)TextView tweetUserName;
    @BindView(R.id.tvCreatedAtVV)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContentVV)TextView tweetContent;
    @BindView(R.id.ivProfleImageVV)ImageView tweetProfileImage;
    @BindView(R.id.ivVerifiedUserVV)ImageView isVerified;
    @BindView(R.id.vvTweetVideo)VideoView tweetVideo;



    public TweetItemVideo(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
