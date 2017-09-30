package com.codepath.apps.restclienttemplate.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComposeTweetFragment extends Fragment {

    @BindView(R.id.tvNameCompose)TextView tvName;
    @BindView(R.id.tvUserNameCompose)TextView tvUserName;
    @BindView(R.id.tvTweetMssg)TextView tvTweetMessage;
    @BindView(R.id.ivProfileImageCompose)ImageView ivProfileImage;

    public ComposeTweetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compose_tweet, container, false);
        ButterKnife.bind(this,view);

        return view;
    }

}
