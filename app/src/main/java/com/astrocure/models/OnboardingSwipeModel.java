package com.astrocure.models;

public class OnboardingSwipeModel {
    String title;
    String content;
    int rawAnim;

    public OnboardingSwipeModel(String title, String content, int rawAnim) {
        this.title = title;
        this.content = content;
        this.rawAnim = rawAnim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRawAnim() {
        return rawAnim;
    }

    public void setRawAnim(int rawAnim) {
        this.rawAnim = rawAnim;
    }
}
