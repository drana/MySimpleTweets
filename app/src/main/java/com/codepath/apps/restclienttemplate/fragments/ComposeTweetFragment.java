package com.codepath.apps.restclienttemplate.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

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
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    //interface for passing filters back to activity
    public interface TweetDialogListener{
        void onSendTweetMessage(ComposeTweetFragment composeTweet);
    }
}
