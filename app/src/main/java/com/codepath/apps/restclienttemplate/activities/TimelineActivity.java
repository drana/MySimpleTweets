package com.codepath.apps.restclienttemplate.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.adapters.TweetAdapter;
import com.codepath.apps.restclienttemplate.fragments.ComposeTweetFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.CommonUtils;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.codepath.apps.restclienttemplate.utils.ItemClickSupport;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements ComposeTweetFragment.TweetDialogListener{

    @BindView(R.id.rvTweets)RecyclerView rvTweets;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.swipeContainer)SwipeRefreshLayout swipeContainer;
    TwitterClient client;
    ArrayList<Tweet> tweets;
    TweetAdapter tweetAdapter;
    EndlessRecyclerViewScrollListener scrollListener;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = TwitterApp.getRestClient();

        ButterKnife.bind(this);

        //set toolbar as actionbar for this activity
        ApplyToolBarStyle();

        //initialize data source
        tweets = new ArrayList<>();
        //construct adapter from data source
        tweetAdapter= new TweetAdapter(this,tweets);
        //set adapter and layout manager
        rvTweets.setAdapter(tweetAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rvTweets.setLayoutManager(linearLayoutManager);

        //Setup scrolllistener
        SetupScrollListener();

        //populateTimeline();
        LoadTweetsTimeline(false);

        //swipe action listner
        SetupSwipeListner();

        //on an article click event
        SetupListViewCLickListener();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvTweets.addItemDecoration(itemDecoration);

    }

    private void SetupSwipeListner() {
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                LoadTweetsTimeline(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.primary);


    }

    private void SetupScrollListener() {
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                LoadTweetsTimeline(true);
            }
        };

        // Adds the scroll listener to RecyclerView
        rvTweets.addOnScrollListener(scrollListener);
    }

    //attach listeners for article items
    private void SetupListViewCLickListener() {

        ItemClickSupport.addTo(rvTweets).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // do it
                        Tweet details = tweets.get(position);

                        PackageManager pm = getApplicationContext().getPackageManager();
                        boolean isInstalled = CommonUtils.isPackageInstalled("com.android.chrome", pm);
                        //if chrome installed start chrome else use webview
                        if(isInstalled) {
                            //SetupChromeWebView(details.getWebUrl());
                        }
                        else{
//                            Intent intent = new Intent(SearchActivity.this,ArticleDetailsActivity.class);
//                            intent.putExtra("ARTICLE_WEB_URL", details.getWebUrl());
//                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                            startActivity(intent);
                        }
                    }
                }
        );
    }

    //get timeline whether its from scrolling or loading home page.
    private void LoadTweetsTimeline(boolean loadOldTweets) {

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

    private void ApplyToolBarStyle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();

        if(item.getItemId() == R.id.miComposeTweet){

            ShowComposeTWeet();

        }
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return  true;
    }

    private void ShowComposeTWeet() {
        FragmentManager fm = getSupportFragmentManager();
        ComposeTweetFragment composeTweetFragment = ComposeTweetFragment.newInstance();
        composeTweetFragment.setStyle( DialogFragment.STYLE_NORMAL, R.style.AppBaseTheme );// fragment fullscreen
        composeTweetFragment.show(fm, "tweetFrag");
    }

    private void populateTimeline(long max_id, final Boolean loadOldTweets) {

        client.getHomeTimeline(max_id, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if(!loadOldTweets){
                    tweetAdapter.RemoveTweets();
                }
                for(int i =0; i< response.length();i++){
                        try {
                            Tweet tweet = Tweet.parseJson(response.getJSONObject(i));
                            tweets.add(tweet);
                            tweetAdapter.notifyItemInserted(tweets.size()-1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
                Log.d("TwitterClient", response.toString());
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

    @Override
    public void onPostNewTweet(Tweet newTweet) {
        tweets.add(0, newTweet);
        tweetAdapter.notifyItemInserted(0);
        rvTweets.getLayoutManager().scrollToPosition(0);
        Log.d("New Tweet",newTweet.getText());
    }

    public void onComposeAction(View v) {
        ShowComposeTWeet();
    }


}
