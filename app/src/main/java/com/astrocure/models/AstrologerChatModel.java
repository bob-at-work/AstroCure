package com.astrocure.models;

public class AstrologerChatModel {
    String message;
    String time;
    Boolean admin;

    public AstrologerChatModel(String message, String time, Boolean admin) {
        this.message = message;
        this.time = time;
        this.admin = admin;
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
}
