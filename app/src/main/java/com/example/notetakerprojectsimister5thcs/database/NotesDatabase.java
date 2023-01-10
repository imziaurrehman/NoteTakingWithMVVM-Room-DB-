package com.example.notetakerprojectsimister5thcs.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notetakerprojectsimister5thcs.dao.NotesDao;
import com.example.notetakerprojectsimister5thcs.models.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,
                    "NOTES_DATABASE").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

}
