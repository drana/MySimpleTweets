package com.codepath.apps.restclienttemplate.activities;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.adapters.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.fragments.ComposeTweetFragment;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;


import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity implements ComposeTweetFragment.TweetDialogListener{


    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs)TabLayout tabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

       //set toolbar as actionbar for this activity
        ApplyToolBarStyle();

        //set adapter for view pager
        viewPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(),this));

        //setup tablayout view
        tabLayout.setupWithViewPager(viewPager);

        Log.d("TimelineActivity", "onCreate()");

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

    @Override
    public void onPostNewTweet(Tweet newTweet) {

    }

    public void onComposeAction(View v) {
        ShowComposeTWeet();
    }



}
