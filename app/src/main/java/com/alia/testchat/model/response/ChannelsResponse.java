
package com.alia.testchat.model.response;

import java.util.ArrayList;
import java.util.List;

import com.alia.testchat.model.content.Channel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelsResponse {

    @SerializedName("channels")
    @Expose
    private List<Channel> channels;

    public List<Channel> getChannels() {
        if (channels == null){
            return new ArrayList<>();
        }
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

}
