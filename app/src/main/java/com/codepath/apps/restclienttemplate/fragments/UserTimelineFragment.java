package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dipenrana on 10/4/17.
 */

public class UserTimelineFragment extends TweetsListFragment {
    
    TwitterClient client;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        Log.d("UserTimelineFragment", "Before LoadTweetsTimeline()");

        //populate timeline on creating the view.
        populateTimeline(false);

        Log.d("UserTimelineFragment", "After LoadTweetsTimeline()");
    }

    public static UserTimelineFragment newInstance(String screenname){
        UserTimelineFragment fragment = new UserTimelineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("screen_name",screenname);
        fragment.setArguments(bundle);

        return  fragment;
    }

    private void populateTimeline(final Boolean loadOldTweets) {
        String screen_name = getArguments().getString("screen_name");

        //get usertimeline from twitter client
        client.getUserTimeline(screen_name, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if(!loadOldTweets){
                    clearTweets();
                }
                //add tweets to tweet adapter
                addItems(response);
                Log.d("HomeTimeline", "addItems()");
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
                //Log.d("TwitterClient", response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient",errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient",errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient",responseString);
            }
        });
    }

    //get timeline whether its from scrolling or loading home page.
    protected void LoadTweetsTimeline(boolean loadOldTweets) {

        long max_id;
        if(loadOldTweets){
            max_id = tweetAdapter.getTweetAt(tweetAdapter.getItemCount() - 1).getId();
            max_id--;//
            Log.d("Scroll", Long.toString(max_id));
        }
        else{
            max_id = -1;
        }

        populateTimeline(loadOldTweets);
    }
}
