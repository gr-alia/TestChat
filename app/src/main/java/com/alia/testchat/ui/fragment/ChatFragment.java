package com.alia.testchat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alia.testchat.R;
import com.alia.testchat.ui.adapter.ChannelAdapter;



import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatFragment extends Fragment {
    private static final String TAG = "ChatFragment";

    private static final String ARG_POSITION = "param1";

    private int mPosition;

    @BindView(R.id.channels_recycler)
    RecyclerView mRecyclerView;

    public ChatFragment() {

    }

    public static ChatFragment newInstance(int position) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, v);

        ChannelAdapter adapter = new ChannelAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        return v;
    }


}
