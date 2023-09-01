package com.astrocure.models;

import android.net.Uri;

public class QuestionAnswerChatModel {
    String message;
    String sentBy;
    String time;
    boolean isLink;
    boolean isImage;
    Uri uri;

    public QuestionAnswerChatModel(String message, String sentBy, String time, boolean isLink, boolean isImage, Uri uri) {
        this.message = message;
        this.sentBy = sentBy;
        this.time = time;
        this.isLink = isLink;
        this.isImage = isImage;
        this.uri = uri;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
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
