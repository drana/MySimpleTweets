package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.session.MediaController;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activities.TweetItem;
import com.codepath.apps.restclienttemplate.activities.TweetItemNoImage;
import com.codepath.apps.restclienttemplate.activities.TweetItemVideo;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.utils.CommonUtils;
import com.codepath.apps.restclienttemplate.utils.ItemClickSupport;


import java.util.ArrayList;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<Tweet> mTweets = new ArrayList<Tweet>();

    //constructor
    public TweetAdapter(Context context, ArrayList<Tweet> tweets){

        mContext = context;
        mTweets = tweets;
    }

    @Override
    public int getItemViewType(int position) {

        Tweet tweet = mTweets.get(position);

//        try{
//
//        if(tweet.getExtendedEntities() !=null && tweet.getExtendedEntities().getMedia() !=null) {
//            if (tweet.getExtendedEntities().getMedia().get(0).getType().equals("video")) {
//                return CommonUtils.TWEET_VIDEO;
//
//            }
//        }
//        else if(tweet.getEntities().getMedia() != null){
//                if (tweet.getEntities().getMedia().size()>0){
//                    return  CommonUtils.TWEET_IMAGE;
//                }
//            }
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }

        if(tweet.getEntities().getMedia() != null){

            if (tweet.getEntities().getMedia().size()>0){
                return  CommonUtils.TWEET_IMAGE;
            }
        }
//        else
//            return CommonUtils.TWEET_NO_IMAGE;


        return CommonUtils.TWEET_NO_IMAGE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate view based on view type
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case CommonUtils.TWEET_IMAGE:
                View vTweetImage = inflater.inflate(R.layout.item_tweet, parent, false);
                viewHolder = new TweetItem(vTweetImage);
                break;
            case CommonUtils.TWEET_NO_IMAGE:
                View vTweetNoImage = inflater.inflate(R.layout.item_tweet_no_image, parent, false);
                viewHolder = new TweetItemNoImage(vTweetNoImage);
                break;
            case CommonUtils.TWEET_VIDEO:
                View vTweetVideo = inflater.inflate(R.layout.item_tweet_video, parent, false);
                viewHolder = new TweetItemNoImage(vTweetVideo);
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_activated_1, parent, false);
                viewHolder = new TweetItem(v);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //holder.bind(mTweets.get(position),mListener);

        switch (holder.getItemViewType()) {
            case CommonUtils.TWEET_IMAGE:
                TweetItem vh1 = (TweetItem) holder;
                BindItemWithImage(vh1,position);
                break;
            case CommonUtils.TWEET_NO_IMAGE:
                TweetItemNoImage vh2 = (TweetItemNoImage) holder;
                BindItemWithNoImage(vh2,position);
                break;
            case CommonUtils.TWEET_VIDEO:
                TweetItemVideo vh3 = (TweetItemVideo) holder;
                BindItemWithVideo(vh3,position);
                break;
            default:
                TweetItem vh = (TweetItem) holder;
                break;
        }
    }

    private void BindItemWithVideo(TweetItemVideo holder, int position) {
        // Get the data model based on position
        Tweet tweet = mTweets.get(position);
        TextView tvName = holder.getTweetName();
        TextView tvUserName = holder.getTweetUserName();
        TextView tvContent = holder.getTweetContent();
        TextView tvCreateAt =  holder.getTweetCreatedAt();
        ImageView ivProfileImage =  holder.getTweetProfileImage();
        ImageView ivVerifiedUser = holder.getIsVerified();

        VideoView ivTweetVideo = holder.getTweetVideo();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvContent.setText(tweet.getText().replace("\n",""));
        String parseDateTime = CommonUtils.getRelativeTimeAgo(tweet.getCreatedAt());
        tvCreateAt.setText(parseDateTime);

        Boolean verified = tweet.getUser().getVerified();
        if(verified) {
            ivVerifiedUser.setVisibility(View.VISIBLE);
        }

        String imgUrl = tweet.getUser().getProfileImageUrl();
        String tweetURL = tweet.getEntities().getMedia().get(0).getMediaUrl();


        Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .error(R.drawable.placeholder).into(ivProfileImage);

        ivTweetVideo.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4");

    }

    private void BindItemWithNoImage(TweetItemNoImage holder, int position) {

        // Get the data model based on position
        Tweet tweet = mTweets.get(position);
        TextView tvName = holder.getTweetName();
        TextView tvUserName = holder.getTweetUserName();
        TextView tvContent = holder.getTweetContent();
        TextView tvCreateAt =  holder.getTweetCreatedAt();
        ImageView ivProfileImage =  holder.getTweetProfileImage();
        ImageView ivVerifiedUser = holder.getIvVerifiedUserPlain();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvContent.setText(tweet.getText());
        String parseDateTime = CommonUtils.getRelativeTimeAgo(tweet.getCreatedAt());
        tvCreateAt.setText(parseDateTime);

        Boolean verified = tweet.getUser().getVerified();
        if(verified) {
            ivVerifiedUser.setVisibility(View.VISIBLE);
        }

        String imgUrl = tweet.getUser().getProfileImageUrl();

        Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .error(R.drawable.placeholder).into(ivProfileImage);


    }

    private void BindItemWithImage(TweetItem holder, int position) {

        // Get the data model based on position

        Tweet tweet = mTweets.get(position);
        holder.tweet = tweet;
        TextView tvName = holder.getTweetName();
        TextView tvUserName = holder.getTweetUserName();
        TextView tvContent = holder.getTweetContent();
        TextView tvCreateAt =  holder.getTweetCreatedAt();
        ImageView ivProfileImage =  holder.getTweetProfileImage();
        ImageView ivTweetImage = holder.getTweetUrlImage();
        ImageView ivVerifiedUser = holder.getIsVerified();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvContent.setText(tweet.getText().replace("\n",""));
        String parseDateTime = CommonUtils.getRelativeTimeAgo(tweet.getCreatedAt());
        tvCreateAt.setText(parseDateTime);

        Boolean verified = tweet.getUser().getVerified();
        if(verified) {
            ivVerifiedUser.setVisibility(View.VISIBLE);
        }

        String imgUrl = tweet.getUser().getProfileImageUrl();
        String tweetURL = tweet.getEntities().getMedia().get(0).getMediaUrl();


        Glide.with(mContext)
                .load(imgUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).into(ivProfileImage);

        Glide.with(mContext)
                .load(tweetURL)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).into(ivTweetImage);



    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //get item at position
    public Tweet getTweetAt(int position) {
        return this.mTweets.get(position);
    }

    public void RemoveTweets() {
            int size = this.mTweets.size();
            this.mTweets.clear();
            notifyItemRangeRemoved(0, size);
    }

}
