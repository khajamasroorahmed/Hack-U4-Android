package edu.odu.hackathon.plato.model;

import java.util.List;

/**
 * Created by handsonMacBook02 on 2/5/16.
 */
public class ChatRequest {
    public ChatRequest() {
    }

    List<Chat> messageList;

    public List<Chat> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Chat> messagesList) {
        this.messageList = messageList;
    }

    public int size() {
        return messageList.size();
    }
}
