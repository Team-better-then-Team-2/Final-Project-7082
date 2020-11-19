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

    @Query("UPDATE Event SET title = :sTitle , content = :sContent WHERE id = :sID")
    void update(int sID, String sTitle, String sContent);

    @Query("DELETE FROM Event WHERE id = :sID")
    void delete(int sID);

    @Query("DELETE FROM Event")
    void deleteAll();
}
