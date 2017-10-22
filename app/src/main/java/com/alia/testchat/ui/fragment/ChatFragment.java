package com.alia.testchat.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alia.testchat.R;
import com.alia.testchat.model.content.Channel;
import com.alia.testchat.model.response.ChannelsResponse;
import com.alia.testchat.network.ChatAPI;
import com.alia.testchat.network.RetrofitService;
import com.alia.testchat.ui.activity.ConversationActivity;
import com.alia.testchat.ui.adapter.ChannelAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChatFragment extends Fragment implements ChannelAdapter.OnItemClickListener{
    private static final String TAG = "ChatFragment";
    private static final String ARG_POSITION = "position";

    private int mPosition;

    private ChannelAdapter mAdapter;

    @BindView(R.id.channels_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty)
    TextView mEmptyView;

    @Nullable
    private Subscription mChannelsSubscription;

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

        mAdapter = new ChannelAdapter(getContext(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        ChatAPI api = RetrofitService.getInstance().getApi();
        mChannelsSubscription =
                api.getChannels("json")
                        .map(ChannelsResponse::getChannels)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::showMessages, this::showError);
        return v;
    }
    @Override
    public void onPause() {
        if (mChannelsSubscription != null) {
            mChannelsSubscription.unsubscribe();
        }
        super.onPause();
    }
    private void showError(Throwable e) {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void showMessages(@NonNull List<Channel> channels) {
        mAdapter.changeDataSet(channels);
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull Channel channel) {
        ConversationActivity.createIntent(getActivity(), channel);
    }
}
