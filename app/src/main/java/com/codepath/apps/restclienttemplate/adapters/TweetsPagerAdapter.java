package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.MentionsTimelineFragment;

/**
 * Created by dipenrana on 10/4/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitiles[] = new String[]{"Home","Mentions"};
    private Context mContext;

    public TweetsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new HomeTimelineFragment();
        }else if(position == 1){
            return new MentionsTimelineFragment();
        }else{
            return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitiles[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
