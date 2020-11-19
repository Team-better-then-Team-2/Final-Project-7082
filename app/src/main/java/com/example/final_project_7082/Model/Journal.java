package com.example.final_project_7082.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Journal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String time;

    private String content;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
