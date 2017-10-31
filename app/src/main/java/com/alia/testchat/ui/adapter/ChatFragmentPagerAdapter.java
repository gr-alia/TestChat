package com.alia.testchat.ui.adapter;

import android.content.Context;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alia.testchat.R;
import com.alia.testchat.model.content.Channel;
import com.alia.testchat.ui.fragment.ChatFragment;
import com.alia.testchat.ui.fragment.LiveChatFragment;

import java.util.List;

/**
 * Created by Alyona on 19.10.2017.
 */

public class ChatFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Chat", "Live Chat"};

    private Context mContext;

    public ChatFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ChatFragment.newInstance(position);
        } else return LiveChatFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView tittle = (TextView) v.findViewById(R.id.tittle);
        tittle.setText(tabTitles[position]);
        return v;
    }
}
