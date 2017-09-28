package com.codepath.apps.restclienttemplate.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetItemNoImage extends RecyclerView.ViewHolder{
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

    @BindView(R.id.tvName)TextView tweetName;
    @BindView(R.id.tvUserName)TextView tweetUserName;
    @BindView(R.id.tvCreatedAt)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContent)TextView tweetContent;
    @BindView(R.id.ivProfleImage)ImageView tweetProfileImage;


    public TweetItemNoImage(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);

    }
}
