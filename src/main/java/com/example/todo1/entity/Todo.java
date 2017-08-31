package com.example.todo1.entity;

import java.util.Date;

/**
 * Created by acerpc on 2017/7/20.
 */
public class Todo {
    private int id;
    private String title;
    private int completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}