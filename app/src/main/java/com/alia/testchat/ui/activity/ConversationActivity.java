package com.alia.testchat.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alia.testchat.R;
import com.alia.testchat.model.content.Channel;
import com.alia.testchat.model.content.Sender;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity {
    public static final String EXTRA_SENDER = "extraChannel";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void createIntent(FragmentActivity activity, Channel channel){
        Intent intent = new Intent(activity, ConversationActivity.class);
        intent.putExtra(EXTRA_SENDER, channel.getLastMessage().getSender());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);

        Sender sender = getIntent().getParcelableExtra(EXTRA_SENDER);

        setSupportActionBar(mToolbar);
        setTitle(sender.getFullName());
        ActionBar appBar = getSupportActionBar();
        if (appBar != null) {
            appBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_conversation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_block:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
