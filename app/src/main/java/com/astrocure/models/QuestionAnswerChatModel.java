package com.astrocure.models;

public class QuestionAnswerChatModel {
    String message;
    String sentBy;
    String time;
    boolean isLink;
    boolean isImage;
    String imageUrl;

    public QuestionAnswerChatModel(String message, String sentBy, String time, boolean isLink, boolean isImage, String imageUrl) {
        this.message = message;
        this.sentBy = sentBy;
        this.time = time;
        this.isLink = isLink;
        this.isImage = isImage;
        this.imageUrl = imageUrl;
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

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
