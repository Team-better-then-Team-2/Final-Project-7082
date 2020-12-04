package com.example.final_project_7082.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Journal.class, Event.class}, version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract JournalDao getJournalDao();
    public abstract  EventDao getEventDao();
}
