package com.astrocure.models;

public class HelpChatModel {
    String message;
    boolean isAdmin;
    String imageUrl;
    String time;

    public HelpChatModel(String message, boolean isAdmin, String imageUrl, String time) {
        this.message = message;
        this.isAdmin = isAdmin;
        this.imageUrl = imageUrl;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
