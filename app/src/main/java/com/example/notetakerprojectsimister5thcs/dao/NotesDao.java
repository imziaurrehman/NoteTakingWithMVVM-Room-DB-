package com.example.notetakerprojectsimister5thcs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notetakerprojectsimister5thcs.models.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM `NOTES_DATABASE`")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM `NOTES_DATABASE` ORDER BY priority DESC")
    LiveData<List<Notes>> highFilters();

    @Query("SELECT * FROM `NOTES_DATABASE` ORDER BY priority ASC")
    LiveData<List<Notes>> lowFilters();

    @Insert
    void insertsNotes(Notes... notes);

    //    @Query("SELECT * FROM `NOTES_DATABASE` WHERE id = :id")
    @Query("DELETE FROM `NOTES_DATABASE` WHERE id = :noteId")
    void deleteNotes(int noteId);

    @Update
    void updatesNotes(Notes notes);

}
