package com.cutloose.cutloose.model;

import java.util.Date;


public class Message {
    private Date date;
    private String content;
    private String id;
    private String userId;

    public Message() {
    }

    public Message(Date date, String content, String id, String userId) {
        this.date = date;
        this.content = content;
        this.id = id;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
