package com.astrocure.models;

public class AstrologerChatModel {
    String message;
    String time;
    Boolean admin;
    String audioFile;
    String imageUrl;

    public AstrologerChatModel(String message, String time, Boolean admin, String audioFile, String imageUrl) {
        this.message = message;
        this.time = time;
        this.admin = admin;
        this.audioFile = audioFile;
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
