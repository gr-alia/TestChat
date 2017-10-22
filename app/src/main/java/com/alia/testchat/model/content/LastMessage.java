
package com.alia.testchat.model.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastMessage {

    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("text")
    @Expose
    private String text;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreateTime() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(getCreateDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = new SimpleDateFormat("HH:mm").format(date);
        return formattedTime;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
