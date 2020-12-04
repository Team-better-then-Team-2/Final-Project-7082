package com.example.final_project_7082.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void addEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("SELECT * FROM Event ORDER BY ID DESC")
    List<Event> getAllEvent();

    @Query("SELECT * FROM Event WHERE day = :myday")
    List<Event> selectEvents(int myday);

    @Query("UPDATE Event SET title = :title , content = :content, day = :day, month = :month, year = :year WHERE id = :sID")
    void update(int sID, String title, String content, int day, int month, int year);

    @Query("DELETE FROM Event WHERE id = :sID")
    void delete(int sID);

    @Query("SELECT * FROM Event WHERE day = :day AND month= :month AND year = :year")
    List<Event> selectEvents(int year,int month,int day);
}
