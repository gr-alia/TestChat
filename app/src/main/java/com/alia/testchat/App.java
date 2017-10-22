package com.alia.testchat;

import android.app.Application;

import com.alia.testchat.network.RetrofitService;

/**
 * Created by Alyona on 20.10.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.initInstance(this);

    }
}
