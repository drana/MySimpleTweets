package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.CommonUtils;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dipenrana on 10/3/17.
 */

public class HomeTimelineFragment extends TweetsListFragment {
    TwitterClient client;

    public HomeTimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        Log.d("HomeTimelineFragment", "Before LoadTweetsTimeline()");

        //populate timeline on creating the view.
        LoadTweetsTimeline(false);

        Log.d("HomeTimelineFragment", "After LoadTweetsTimeline()");
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

        populateTimeline(max_id,loadOldTweets);
    }

    private void populateTimeline(long max_id, final Boolean loadOldTweets) {
        
        if(!CommonUtils.isNetworkAvailable(getContext())){
            Toast.makeText(getContext(), "Network is unavailable, please connect to internet for app to get latest updates..", Toast.LENGTH_SHORT).show();
        }

        client.getHomeTimeline(max_id, new JsonHttpResponseHandler(){
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

}
