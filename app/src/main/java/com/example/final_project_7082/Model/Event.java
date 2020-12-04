package com.example.final_project_7082.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Event")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "Create_date")
    private String time;
    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "day")
    private int day;

    @ColumnInfo(name = "month")
    private int month;

    @ColumnInfo(name = "year")
    private int year;


    public Event(String title, String time, String content, int day, int month, int year) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.day = day;
        this.month = month;
        this.year = year;
    }

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

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    public void setId(int id) {
        this.id = id;
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

    public void setDay(Integer day) { this.day = day; }

    public void setMonth(Integer month) { this.month = month; }

    public void setYear(Integer year) { this.year = year; }
}
