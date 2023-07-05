package com.astrocure.models;

public class QuestionAnswerChatModel {
    String message;
    String sentBy;
    String time;
    boolean isLink;

    public QuestionAnswerChatModel(String message, String sentBy, String time, boolean isLink) {
        this.message = message;
        this.sentBy = sentBy;
        this.time = time;
        this.isLink = isLink;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isLink() {
        return isLink;
    }

    public void setLink(boolean link) {
        isLink = link;
    }
}
