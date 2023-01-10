package com.example.notetakerprojectsimister5thcs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.view_models.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton noteCreateBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    TextView noFilters, highFilters, lowFilters;
    List<Notes> filteredNotesListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteCreateBtn = (FloatingActionButton) findViewById(R.id.createNoteBtn);
        notesRecyclerView = (RecyclerView) findViewById(R.id.notesGridRecyclerView);
        // filters data
        noFilters = findViewById(R.id.no_filter);
        highFilters = findViewById(R.id.high_filter);
        lowFilters = findViewById(R.id.low_filter);
        // no filter
        noFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadedFiltersData(0);
                noFilters.setBackgroundResource(R.drawable.border_shape);
                highFilters.setBackgroundResource(R.drawable.none_boder_shape);
                lowFilters.setBackgroundResource(R.drawable.none_boder_shape);
            }


        });
        // high filter
        highFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadedFiltersData(1);
                noFilters.setBackgroundResource(R.drawable.none_boder_shape);
                highFilters.setBackgroundResource(R.drawable.border_shape);
                lowFilters.setBackgroundResource(R.drawable.none_boder_shape);
            }
        });
        // low filter
        lowFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadedFiltersData(2);
                noFilters.setBackgroundResource(R.drawable.none_boder_shape);
                highFilters.setBackgroundResource(R.drawable.none_boder_shape);
                lowFilters.setBackgroundResource(R.drawable.border_shape);
            }
        });
        // view model instance
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Note Taker With MVVM");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
        getSupportActionBar().setElevation(0);

        noteCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainActivity.this, InsertNotesView.class));
            }
        });
        final Observer<List<Notes>> notesObserver = notes -> {
            // Update the UI, in this case, a TextView.
            notesRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, notes);
            notesRecyclerView.setAdapter(recyclerViewAdapter);
            filteredNotesListData = notes;

        };


        notesViewModel.getAllNoted().observe(this, notesObserver);


    }

    private void loadedFiltersData(int i) {
        if (i == 0) {
            notesViewModel.getAllNoted().observe(this, new Observer<List<Notes>>() {

                @Override
                public void onChanged(List<Notes> notes) {
                    setAdaptor(notes);
                    filteredNotesListData = notes;

                }
            });

        } else if (i == 1) {
            notesViewModel.highlyFilters().observe(this, new Observer<List<Notes>>() {

                @Override
                public void onChanged(List<Notes> notes) {
                    setAdaptor(notes);
                    filteredNotesListData = notes;

                }
            });
        } else if (i == 2) {
            notesViewModel.lowlyFilters().observe(this, new Observer<List<Notes>>() {

                @Override
                public void onChanged(List<Notes> notes) {
                    setAdaptor(notes);
                    filteredNotesListData = notes;

                }
            });
        }
    }

    public void setAdaptor(List<Notes> notes) {
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(recyclerViewAdapter);
        filteredNotesListData = notes;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                filteredNotes(text);
                return false;
            }
        });
        ;
        return super.onCreateOptionsMenu(menu);
    }


    private void filteredNotes(String text) {
        Log.e("@@@@", "filteredNotes: " + text);
        ArrayList<Notes> filtersNotesData = new ArrayList<>();
        for (Notes notes : this.filteredNotesListData) {
            if (notes.title.contains(text) || notes.subTitle.contains(text)) {
                filtersNotesData.add(notes);
            }

        }
        this.recyclerViewAdapter.setSearchNotesList(filtersNotesData);
    }
}