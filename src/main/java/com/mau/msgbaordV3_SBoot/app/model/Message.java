package com.mau.msgbaordV3_SBoot.app.model;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private int messageId;
    private int userId;
    private String content;
    private Date creationDate;

    // Constructor with all fields
    public Message(int messageId, int userId, String content, Date creationDate) {
        this.messageId = messageId;
        this.userId = userId;
        this.content = content;
        this.creationDate = creationDate;
    }


    // Getters and Setters (Existing code remains here)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreationDate() {
        return (Timestamp) creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}