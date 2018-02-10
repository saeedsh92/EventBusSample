package com.ss.eventbussample;

/**
 * Created by admin on 2/10/18.
 */

public class Message {
    private int id;
    private String content;
    private String date;
    private String fullName;
    private boolean sendByCurrentUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSendByCurrentUser() {
        return sendByCurrentUser;
    }

    public void setSendByCurrentUser(boolean sendByCurrentUser) {
        this.sendByCurrentUser = sendByCurrentUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
