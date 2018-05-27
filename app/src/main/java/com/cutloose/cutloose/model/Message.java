package com.cutloose.cutloose.model;

import java.util.Objects;


public class Message extends BaseModel {
    private Long createdAt;
    private String content;
    private String userId;
    private String userName;

    public Message() {
    }

    public Message(Long createdAt, String content, String userId, String userName) {
        this.createdAt = createdAt;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
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
        return Objects.equals(createdAt, message.createdAt) &&
                Objects.equals(content, message.content) &&
                Objects.equals(userId, message.userId) &&
                Objects.equals(userName, message.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(createdAt, content, userId, userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
