package com.codepath.apps.restclienttemplate.activities;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.fragments.TweetsListFragment;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
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

public class ProfileActivity extends AppCompatActivity implements TweetsListFragment.TweetSelectedListener{


    TwitterClient client;
    String screenName;

    @BindView(R.id.ivProfileBanner)    ImageView profileBanner;
    @BindView(R.id.ivProfileImage)    ImageView profileImage;
    @BindView(R.id.tvProfileName)    TextView profileName;
    @BindView(R.id.tvProfileScreenName)    TextView profileScreenName;
    @BindView(R.id.tvProfileDescription)    TextView profileTagLine;
    @BindView(R.id.tvFollowing)    TextView profileFollowing;
    @BindView(R.id.tvFollowers)    TextView profileFollowers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        client = TwitterApp.getRestClient();

        User userObject = (User) getIntent().getParcelableExtra("USER");
        if (userObject != null && userObject.getScreenName() != null) {
            screenName = userObject.getScreenName();
            PopulateUserProfile(userObject);
        } else {
            ShowPersonalProfile();
        }


        //final UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(userObject.getScreenName());
        final UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);
        //create and replace placeholder with fragment

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flPlaceHolder, userTimelineFragment);
        ft.commit();

        // Create the TypeFace from the TTF asset
        Typeface boldFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeue-Bold.ttf");
        Typeface regularFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeue.ttf");
        // Assign the typeface to the view
        profileName.setTypeface(boldFont);
        profileScreenName.setTypeface(regularFont);
        profileTagLine.setTypeface(regularFont);
        profileFollowing.setTypeface(boldFont);
        profileFollowers.setTypeface(boldFont);

    }

    private void ShowPersonalProfile() {

        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    User user = User.getUserfromJSON(response);
                    PopulateUserProfile(user);

                    Log.d("User", user.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("User", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("User", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("User", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("User", responseString);
            }

        });

    }

    private void PopulateUserProfile(User userProfile) {

        String screenName = "@" + userProfile.getScreenName();
        int friends = userProfile.getFriendsCount();
        int followers = userProfile.getFollowersCount();



        profileName.setText(userProfile.getName());
        profileScreenName.setText("@" + userProfile.getScreenName());
        profileTagLine.setText(userProfile.getDescription());
        profileFollowing.setText(Integer.toString(userProfile.getFriendsCount()));
        profileFollowers.setText(Integer.toString(userProfile.getFollowersCount()));

        profileName.setClickable(false);
        profileScreenName.setClickable(false);
        profileImage.setClickable(false);

        String profileURL = userProfile.getProfileImageUrl();
        String bannerURL = userProfile.getProfileBannerUrl();


        Glide.with(getApplicationContext())
                .load(profileURL)
                .error(R.drawable.placeholder)
                .dontAnimate()
                .into(profileImage);

        Glide.with(getApplicationContext())
                .load(bannerURL)
                .error(R.drawable.placeholder)
                .into(profileBanner);

    }

    @Override
    public void onTweetSelected(Tweet tweet) {

    }

    //back button clicked
    public void onBackBtnClicked(View v){
        Log.d("Button","Clicked");
        finish();
    }
}
