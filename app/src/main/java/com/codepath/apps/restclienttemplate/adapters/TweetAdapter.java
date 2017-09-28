package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    ArrayList<Tweet> mTweet = new ArrayList<Tweet>();

    //constructor
    public TweetAdapter(Context context, ArrayList<Tweet> tweets){

        mContext = context;
        mTweet = tweets;
    }

    @Override
    public int getItemViewType(int position) {

        Tweet tweet = mTweet.get(position);
        // check if tweet has image or no image and load item_tweet accoordingly

        return CommonUtils.TWEET_IMAGE;

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

    }

    @Override
    public int getItemCount() {
        return mTweet.size();
    }


}
