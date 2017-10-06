package com.codepath.apps.restclienttemplate.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.adapters.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.codepath.apps.restclienttemplate.utils.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsListFragment extends Fragment {

    @BindView(R.id.rvTweets)RecyclerView rvTweets;
    @BindView(R.id.swipeContainer)SwipeRefreshLayout swipeContainer;

    ArrayList<Tweet> tweets;
    TweetAdapter tweetAdapter;
    LinearLayoutManager linearLayoutManager;
    EndlessRecyclerViewScrollListener scrollListener;

    public TweetsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tweets_list, container, false);

        ButterKnife.bind(this,view);

        tweets = new ArrayList<>();
        //construct adapter from data source
        tweetAdapter= new TweetAdapter(getContext(),tweets);
        //set adapter and layout manager
        rvTweets.setAdapter(tweetAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvTweets.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rvTweets.addItemDecoration(itemDecoration);

        //swipe action listner
        SetupSwipeListner();
        //Setup scrolllistener
        SetupScrollListener();

        SetupClickListener();

        Log.d("TweetListFragment", "OnCreatView()");

        return view;
    }

    public void addItems(JSONArray response){
        for(int i =0; i< response.length();i++){
            try {
                Tweet tweet = Tweet.parseJson(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(tweets.size()-1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("TweetList","addItems()");
    }

    public void clearTweets() {
        tweetAdapter.RemoveTweets();
    }

    private void SetupScrollListener() {
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //LoadTweetsTimeline(true);
            }
        };

        // Adds the scroll listener to RecyclerView
        rvTweets.addOnScrollListener(scrollListener);
    }

    private void SetupSwipeListner() {
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                //LoadTweetsTimeline(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.primary);


    }

    private void SetupClickListener(){
        //ItemClickSupport.addTo(rvArticleItems).setOnItemClickListener
        ItemClickSupport.addTo(rvTweets).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                                                                        Tweet tweet = tweets.get(position);
                                                                        Log.d("It worked",Integer.toString(position));

                                                                    }
                                                                }

        );

    }



}
