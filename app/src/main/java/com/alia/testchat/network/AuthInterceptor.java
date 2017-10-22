package com.alia.testchat.network;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alyona on 20.10.2017.
 */

public class AuthInterceptor implements Interceptor {
    private String credentials;

    public AuthInterceptor(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authRequest = request.newBuilder()
                .header("Authorization", credentials)
                .build();
        return chain.proceed(authRequest);
    }
}
