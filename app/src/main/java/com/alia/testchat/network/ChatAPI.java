package com.alia.testchat.network;

import com.alia.testchat.model.response.ChannelsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alyona on 20.10.2017.
 */

public interface ChatAPI {
    String BASE_URL = "https://iostest.db2dev.com/api/";

    @GET("chat/channels")
    Observable<ChannelsResponse> getChannels(@Query("format") String format);
}
