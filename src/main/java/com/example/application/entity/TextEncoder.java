package com.example.application.entity;

public class TextEncoder {
    private String text;
    private String key;

    public TextEncoder(String text, String key) {
        this.text = text;
        this.key = key;
    }

    public TextEncoder() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Text:"+text +
                "\nKey:" + key;
    }
}
