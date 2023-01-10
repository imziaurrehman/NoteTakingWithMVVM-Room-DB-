package com.example.notetakerprojectsimister5thcs.view_models;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.repositories.NotesRepo;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepo notesRepo;

    public NotesViewModel(Application application) {
        super(application);
        notesRepo = new NotesRepo(application);

    }

    public LiveData<List<Notes>> getAllNoted() {
        return notesRepo.getAllNotes;
    }

    public LiveData<List<Notes>> highlyFilters() {
        return notesRepo.highFilter;
    }

    public LiveData<List<Notes>> lowlyFilters() {
        return notesRepo.lowFilter;
    }

    public void insertNote(Notes notes) {
        notesRepo.insertNotes(notes);
    }

    public void deleteNote(int id) {
        notesRepo.deleteNotes(id);
    }

    public void updateNote(Notes notes) {
        notesRepo.updateNotes(notes);
    }
}
