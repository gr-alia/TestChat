package com.alia.testchat.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alia.testchat.R;
import com.alia.testchat.model.content.Channel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Alyona on 20.10.2017.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelHolder> {
    private static final String TAG = "ChannelAdapter";

    private Context mContext;
    private List<Channel> mChannels;

    public ChannelAdapter(Context context) {
        mContext = context;
        mChannels = new ArrayList<>();
    }

    @Override
    public ChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_channel, parent, false);
        Log.i(TAG, "onCreateViewHolder");
        return new ChannelHolder(v);
    }

    @Override
    public void onBindViewHolder(ChannelHolder holder, int position) {
        //Channel channel = mChannels.get(position);
        Log.i(TAG, "onBindViewHolder");
        //holder.bind(channel);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount");
        //mChannels.size();
        return 5;
    }

    public void changeDataSet(List<Channel> channels){
        mChannels.addAll(channels);
        notifyDataSetChanged();
    }

    class ChannelHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sender_photo)
        CircleImageView circleImageView;

        @BindView(R.id.sender_name)
        TextView nameView;

        @BindView(R.id.message_text)
        TextView msgTextView;

        @BindView(R.id.message_time)
        TextView msgTimeView;

        @BindView(R.id.unread_count)
        TextView unreadCountView;

        public ChannelHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Log.i(TAG, "ChannelHolder");
        }

        public void bind(@NonNull Channel channel) {
            nameView.setText(channel.getLastMessage().getSender().getFullName());
            msgTextView.setText(channel.getLastMessage().getCreateDate());
        }
    }
}
