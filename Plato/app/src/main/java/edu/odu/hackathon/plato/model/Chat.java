package edu.odu.hackathon.plato.model;

import android.util.Log;

/**
 * Created by handsonMacBook02 on 2/5/16.
 */
public class Chat {

    String TAG = "";
    int id;
    long timeStamp;
    String chatText;
    String displayName;
    public Chat(Chat item) {
        this.id = item.getId();
        this.timeStamp = item.getTimeStamp();
        this.chatText = item.getChatText();
        this.displayName = item.getDisplayName();
    }
    public Chat(int id, long timeStamp, String chatText, String displayName) {
        Log.v(TAG, "Started");
        this.id = id;
        this.timeStamp = timeStamp;
        this.chatText = chatText;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
