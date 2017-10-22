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
import com.squareup.picasso.Picasso;

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

    private final OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Channel channel);

    }

    public ChannelAdapter(Context context, @NonNull OnItemClickListener onItemClickListener) {
        mContext = context;
        mChannels = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
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
        Channel channel = mChannels.get(position);
        Log.i(TAG, "onBindViewHolder");
        holder.bind(channel);

        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(channel);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount");

        return mChannels.size();
    }

    public void changeDataSet(List<Channel> channels){
        mChannels.addAll(channels);
        notifyDataSetChanged();
    }


    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Channel channel = (Channel) view.getTag();
            mOnItemClickListener.onItemClick(view, channel);
        }
    };
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
            msgTextView.setText(channel.getLastMessage().getText());

            Picasso.with(mContext)
                    .load(channel.getLastMessage().getSender().getPhoto())
                    .placeholder(R.drawable.ic_new_message)
                    .into(circleImageView);

            msgTimeView.setText(channel.getLastMessage().getCreateTime());
            unreadCountView.setText(String.valueOf(channel.getUnreadMessagesCount()));
        }
    }
}
