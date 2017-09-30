package com.codepath.apps.restclienttemplate.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeTweetFragment extends DialogFragment implements View.OnClickListener{

    @BindView(R.id.tvNameCompose)TextView tvName;
    @BindView(R.id.tvUserNameCompose)TextView tvUserName;
    @BindView(R.id.etTweetMssg)EditText etTweetMessage;
    @BindView(R.id.ivProfileImageCompose)ImageView ivProfileImage;
    @BindView(R.id.btnTweet)Button btnTweet;
    @BindView(R.id.ivComposeCancel)ImageButton btnCancel;
    TwitterClient client;
    User user;


    public ComposeTweetFragment() {
        // Required empty public constructor
    }

    public static ComposeTweetFragment newInstance() {
        ComposeTweetFragment fragment = new ComposeTweetFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();


        client.verify_credentials(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    user  = User.getUserfromJSON(response);
                    tvUserName.setText("@"+user.getScreenName());
                    tvName.setText(user.getName());
                    String imgUrl = user.getProfileImageUrl();

                    Glide.with(getContext())
                            .load(imgUrl)
                            .centerCrop()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into(ivProfileImage);
                    Log.d("User",user.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Twitterclient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compose_tweet, container);
        ButterKnife.bind(this,view);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Typeface boldFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue-Bold.ttf");
        Typeface regularFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue.ttf");
        tvName.setTypeface(boldFont);
        tvUserName.setTypeface(regularFont);
        etTweetMessage.setTypeface(regularFont);
        getDialog().getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btnTweet.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnTweet:
                OnTweetMssg();
                break;
            case R.id.ivComposeCancel:
                OnCancel();
                break;
            default:
                break;
        }

    }

    private void OnCancel() {
        dismiss();
        Log.d("btn","cancel clicked");
    }

    private void OnTweetMssg() {

        Tweet tweet = new Tweet();
        tweet.setText(etTweetMessage.getText().toString());
        //tweet.getUser().setName(tvName.getText().toString());

        dismiss();
        TweetDialogListener mListener = (TweetDialogListener) getActivity();
        mListener.onSendTweetMessage(tweet);

        Log.d("btn","save clicked");
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    //interface for passing filters back to activity
    public interface TweetDialogListener{
        void onSendTweetMessage(Tweet tweet);
    }
}
