package com.codepath.apps.restclienttemplate.fragments;


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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeTweetFragment extends DialogFragment implements View.OnClickListener{

    @BindView(R.id.tvNameCompose)TextView tvName;
    @BindView(R.id.tvUserNameCompose)TextView tvUserName;
    @BindView(R.id.tvTweetMssg)TextView tvTweetMessage;
    @BindView(R.id.ivProfileImageCompose)ImageView ivProfileImage;
    @BindView(R.id.btnTweet)Button btnTweet;
    @BindView(R.id.ivComposeCancel)ImageButton btnCancel;

    public ComposeTweetFragment() {
        // Required empty public constructor
    }

    public static ComposeTweetFragment newInstance() {
        ComposeTweetFragment fragment = new ComposeTweetFragment();
        return fragment;
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
        tweet.setText(tvTweetMessage.getText().toString());
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
