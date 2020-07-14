package com.example.application;


import java.io.Serializable;

public class ListViewCustomDTO implements Serializable {
    private int resId;
    private String title;
    private String content;
    private String number;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
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

    public void setNumber(String number) {
        this.number=number;
    }

    public String getNumber() {
        return number;
    }

}