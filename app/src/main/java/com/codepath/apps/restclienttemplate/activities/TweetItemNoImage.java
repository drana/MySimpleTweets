package com.codepath.apps.restclienttemplate.activities;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetItemNoImage extends RecyclerView.ViewHolder{
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

    @BindView(R.id.tvNamePlain)TextView tweetNamePlain;
    @BindView(R.id.tvUserNamePlain)TextView tweetUserName;
    @BindView(R.id.tvCreatedAtPlain)TextView tweetCreatedAt;
    @BindView(R.id.tvTweetContentPlain)TextView tweetContent;
    @BindView(R.id.ivProfleImagePlain)ImageView tweetProfileImage;
    @BindView(R.id.ivVerifiedUserPlain)ImageView ivVerifiedUserPlain;


    public TweetItemNoImage(View viewItem){
        super(viewItem);
        ButterKnife.bind(this,viewItem);

        // Create the TypeFace from the TTF asset
        Typeface boldFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue-Bold.ttf");
        Typeface regularFont = Typeface.createFromAsset(viewItem.getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        // Assign the typeface to the view
        tweetNamePlain.setTypeface(boldFont);
        tweetUserName.setTypeface(regularFont);
        tweetCreatedAt.setTypeface(regularFont);
        tweetContent.setTypeface(regularFont);

    }

    @OnClick({ R.id.ibFavorites, R.id.ibReply, R.id.ibReTweet,R.id.ivProfleImagePlain})
    public void onTweetItemNoImageClick(View view){
        switch (view.getId()) {
            case R.id.ibFavorites:
                Log.d("click","fav");
                break;
            case R.id.ibReTweet:
                Log.d("click","fav");
                break;
            case R.id.ibReply:
                Log.d("click","fav");
                break;
            case R.id.ivProfleImagePlain:
                Log.d("click","fav");
                break;
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
