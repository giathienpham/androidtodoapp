package com.example.thien.mytodolist.model;

import java.util.UUID;

/**
 * Created by Thien on 2/5/2017.
 */

public class ToDoItem {

    private int id;
    private String title;
    private String priority;

    public ToDoItem() {
    }

    public ToDoItem(int id, String title, String priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
    }

    public ToDoItem(String title, String priority) {
        this.title = title;
        this.priority = priority;
    }


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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
