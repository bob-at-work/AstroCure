package com.astrocure.models;

public class VideoContentModel {
    String profileImgUrl;
    String title;
    String time;
    String textContent;
    String imageUrl;
    String videoUrl;
    String thumbnail;
    boolean isVideo;

    public VideoContentModel(String profileImgUrl, String title, String time, String textContent, String imageUrl, String videoUrl, String thumbnail, boolean isVideo) {
        this.profileImgUrl = profileImgUrl;
        this.title = title;
        this.time = time;
        this.textContent = textContent;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.thumbnail = thumbnail;
        this.isVideo = isVideo;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
