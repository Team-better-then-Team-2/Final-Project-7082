package com.example.final_project_7082.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JournalDao {

    @Insert
    void addJournal(Journal journal);

    @Update
    void updateJournal(Journal journal);

    @Delete
    void deleteJournal(Journal journal);

    @Query("SELECT * FROM Journal ORDER BY ID DESC")
    List<Journal> getAllJournal();
}
