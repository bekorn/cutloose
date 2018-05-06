package com.cutloose.cutloose.model;

import java.util.Date;


public class Message extends BaseModel {
    private Date date;
    private String content;
    private String userId;

    public Message() {
    }

    public Message(Date date, String content, String userId) {
        this.date = date;
        this.content = content;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null)
            return false;
        return userId != null ? userId.equals(message.userId) : message.userId == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
