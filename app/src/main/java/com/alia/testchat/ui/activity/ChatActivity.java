package com.alia.testchat.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alia.testchat.R;
import com.alia.testchat.ui.adapter.ChatFragmentPagerAdapter;
import com.alia.testchat.ui.fragment.ChatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements ChatFragment.OnTabTittleListener {
    private ChatFragmentPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setTitle(R.string.menu_title_chat);
        ActionBar appBar = getSupportActionBar();
        if (appBar != null) {
            appBar.setDisplayHomeAsUpEnabled(true);
        }

        mPagerAdapter = new ChatFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mPagerAdapter.getTabView(i));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_message:
                Intent intent = new Intent(this, NewMessageActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDataUpdate(int data) {
        TabLayout.Tab tab = mTabLayout.getTabAt(0);
        View v = tab.getCustomView();
        TextView unreadView = (TextView) v.findViewById(R.id.msg_count);
        if (data != 0) {
            unreadView.setVisibility(View.VISIBLE);
            unreadView.setText(String.valueOf(data));
        }
        tab.setCustomView(v);
    }
}
