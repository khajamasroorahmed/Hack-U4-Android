package edu.odu.hackathon.plato.model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by handsonMacBook02 on 2/5/16.
 */
public class Chat {

    String TAG = "";

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(long epochTime) {
        this.epochTime = epochTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    int userId;
    long epochTime;
    String message;
    String displayName;

    public Chat()
    {

    }

    public Chat(Chat item) {
        this.userId = item.getUserId();
        this.epochTime = item.getEpochTime();
        this.message = item.getMessage();
        this.displayName = item.getDisplayName();
    }

    public Chat(int id, long timeStamp, String chatText, String displayName) {
        Log.v(TAG, "Started");
        this.userId = id;
        this.epochTime = timeStamp;
        this.message = chatText;
        this.displayName = displayName;
    }





}
