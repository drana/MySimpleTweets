package com.codepath.apps.restclienttemplate.activities;

import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    User user;
    TwitterClient client;

    @BindView(R.id.ivProfileBanner)
    ImageView profileBanner;
    @BindView(R.id.ivProfileImage)
    ImageView profileImage;
    @BindView(R.id.tvProfileName)
    TextView profileName;
    @BindView(R.id.tvProfileScreenName)
    TextView profileScreenName;
    @BindView(R.id.tvProfileDescription)
    TextView profileTagLine;
    @BindView(R.id.tvFollowing)
    TextView profileFollowing;
    @BindView(R.id.tvFollowers)
    TextView profileFollowers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        client = TwitterApp.getRestClient();


        final UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(getIntent().getStringExtra("screen_name"));
        //create and replace placeholder with fragment

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flPlaceHolder,userTimelineFragment);
        ft.commit();

        //
        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    user  = User.getUserfromJSON(response);
                    profileName.setText(user.getName());
                    profileScreenName.setText(user.getScreenName());
                    profileTagLine.setText(user.getDescription());
                    profileFollowing.setText(user.getFriendsCount() + " Following");
                    profileFollowers.setText(user.getFollowersCount() + " Followers");

                    String profileURL = user.getProfileImageUrl();
                    String bannerURL = user.getProfileBannerUrl();


                    Glide.with(getApplicationContext())
                            .load(profileURL)
                            .error(R.drawable.placeholder)
                            .dontAnimate()
                            .into(profileImage);

                    Glide.with(getApplicationContext())
                            .load(bannerURL)
                            .error(R.drawable.placeholder)
                            .into(profileBanner);

                    Log.d("User",user.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("User", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("User",errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("User",errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("User",responseString);
            }

        });

    }

}
