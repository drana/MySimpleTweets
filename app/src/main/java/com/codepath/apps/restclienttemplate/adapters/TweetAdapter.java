package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.ArrayList;

/**
 * Created by dipenrana on 9/27/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    ArrayList<Tweet> mTweet = new ArrayList<Tweet>();


    public TweetAdapter(Context context, ArrayList<Tweet> tweets){

        mContext = context;
        mTweet = tweets;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
