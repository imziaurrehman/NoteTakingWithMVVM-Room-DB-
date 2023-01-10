package com.example.notetakerprojectsimister5thcs.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.dao.NotesDao;
import com.example.notetakerprojectsimister5thcs.database.NotesDatabase;

import java.util.List;

public class NotesRepo {
    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highFilter;
    public LiveData<List<Notes>> lowFilter;

    public NotesRepo(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        this.highFilter = notesDao.highFilters();
        this.lowFilter = notesDao.lowFilters();

    }

    public void insertNotes(Notes notes) {
        notesDao.insertsNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updatesNotes(notes);
    }
}
