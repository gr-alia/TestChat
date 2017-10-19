package com.alia.testchat.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alia.testchat.ui.fragment.ChatFragment;
import com.alia.testchat.ui.fragment.LiveChatFragment;

/**
 * Created by Alyona on 19.10.2017.
 */

public class ChatFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Chat", "Live Chat"};
    public ChatFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return ChatFragment.newInstance(position);
        }
        else return LiveChatFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
