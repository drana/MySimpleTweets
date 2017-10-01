package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activities.TweetItem;
import com.codepath.apps.restclienttemplate.activities.TweetItemNoImage;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.utils.CommonUtils;

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
        // check if tweet has image or no image and load item_tweet accoordingly
        if(tweet.getUrls()!=null)
        return CommonUtils.TWEET_IMAGE;
        else
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
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_activated_1, parent, false);
                viewHolder = new TweetItem(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TweetItemNoImage vh1 = (TweetItemNoImage) holder;
        BindItemWithNoImage(vh1,position);

//        switch (holder.getItemViewType()) {
//            case CommonUtils.TWEET_IMAGE:
//                TweetItem vh1 = (TweetItem) holder;
//                BindItemWithImage(vh1,position);
//                break;
//            case CommonUtils.TWEET_NO_IMAGE:
//                TweetItemNoImage vh2 = (TweetItemNoImage) holder;
//                BindItemWithNoImage(vh2,position);
//
//                break;
//            default:
//                TweetItem vh = (TweetItem) holder;
//
//                break;
//        }

    }

    private void BindItemWithNoImage(TweetItemNoImage holder, int position) {

        // Get the data model based on position
        Tweet tweet = mTweets.get(position);
        TextView tvName = holder.getTweetName();
        TextView tvUserName = holder.getTweetUserName();
        TextView tvContent = holder.getTweetContent();
        TextView tvCreateAt =  holder.getTweetCreatedAt();
        ImageView ivProfileImage =  holder.getTweetProfileImage();

        tvName.setText(tweet.getUser().getName());
        tvUserName.setText("@" + tweet.getUser().getScreenName());
        tvContent.setText(tweet.getText());
        String parseDateTime = CommonUtils.getRelativeTimeAgo(tweet.getCreatedAt());
        tvCreateAt.setText(parseDateTime);

        String imgUrl = tweet.getUser().getProfileImageUrl();

        Glide.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).into(ivProfileImage);


    }

    private void BindItemWithImage(TweetItem holder, int position) {

        Tweet tweet = mTweets.get(position);
        TextView tvName = holder.getTweetName();
        TextView tvUserName = holder.getTweetUserName();
        TextView tvContent = holder.getTweetContent();
        TextView tvCreateAt =  holder.getTweetCreatedAt();
        ImageView ivProfileImage =  holder.getTweetProfileImage();
        ImageView ivUrlImage = holder.getTweetUrlImage();




        ivProfileImage.setImageResource(0);
        ivUrlImage.setImageResource(0);

        //String imgURL = CommonUtils.IMAGE_URL_PREFIX + article.getMultimedia().get(0).getUrl();
        //String imgProfile =


//        Glide.with(mContext)
//                .load(imgURL)
//                .centerCrop()
//                .placeholder(R.drawable.placeholder)
//                .error(R.drawable.placeholder).into(ivArticleImage);


        tvName.setText(tweet.getUser().getName());
        tvUserName.setText(tweet.getUser().getScreenName());
        tvContent.setText(tweet.getText());
        tvCreateAt.setText(tweet.getCreatedAt());



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

    // Clean all elements of the recycler
//    public void clear() {
//        items.clear();
//        notifyDataSetChanged();
//    }

//    // Add a list of items -- change to type used
//    public void addAll(List<Tweet> list) {
//        items.addAll(list);
//        notifyDataSetChanged();
//    }

}
