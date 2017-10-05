package com.codepath.apps.restclienttemplate.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        ApplyToolBarStyle();

        //creat user fragment

        UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(getIntent().getStringExtra("screen_name"));
        //create and replace placeholder with fragment

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flPlaceHolder,userTimelineFragment);
        ft.commit();

    }

    private void ApplyToolBarStyle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.twitter_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
