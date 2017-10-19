package com.alia.testchat.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alia.testchat.R;

/**
 * Created by Alyona on 19.10.2017.
 */

public class LiveChatFragment extends Fragment {
    public LiveChatFragment() {
    }
    public static LiveChatFragment newInstance(int position) {
        LiveChatFragment fragment = new LiveChatFragment();

        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live_chat, container, false);

    }
}
