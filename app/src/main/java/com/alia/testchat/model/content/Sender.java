
package com.alia.testchat.model.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender implements Parcelable {

    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;

    public Sender(){

    }
    public Sender(Parcel in){
        this.lastName = in.readString();
        this.photo = in.readString();
        this.firstName = in.readString();
        this.id = in.readInt();
        this.username = in.readString();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastName);
        dest.writeString(photo);
        dest.writeString(firstName);
        dest.writeInt(id);
        dest.writeString(username);
    }

    public static final Creator<Sender> CREATOR = new Creator<Sender>() {

        @NonNull
        @Override
        public Sender createFromParcel(Parcel parcel) {
            return new Sender(parcel);
        }

        @NonNull
        @Override
        public Sender[] newArray(int size) {
            return new Sender[size];
        }

    };
}
