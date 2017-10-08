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

    Fragment cache[] = {null,null};

    private String tabTitiles[] = new String[]{"Home","Mentions"};
    private Context mContext;

    public TweetsPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if(cache[position] == null) {
            if(position == 0 ){
                cache[position] = new HomeTimelineFragment();
            }else  if (position == 1){
                cache[position] = new MentionsTimelineFragment();
            }
            else
                cache[position] = null;
        }


//            if (position == 0) {
//                return new HomeTimelineFragment();
//            } else if (position == 1) {
//                return new MentionsTimelineFragment();
//            } else {
//                return null;
//            }

        return cache[position];

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
