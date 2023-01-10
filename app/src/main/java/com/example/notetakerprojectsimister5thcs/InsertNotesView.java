package com.example.notetakerprojectsimister5thcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.LogPrinter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.view_models.NotesViewModel;

import java.util.Date;
import java.util.Objects;

public class InsertNotesView extends AppCompatActivity {
    Button noteSaveButton;
    EditText titleEdTxt, subTitleEdTxt, notesEdTxt;
    NotesViewModel notesViewModel;
    ImageView priorityGreen, priorityYellow, priorityRed;
    String priority = "1";
    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes_view);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Note Activity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
        getSupportActionBar().setElevation(0);
        noteSaveButton = findViewById(R.id.saveNoteBtn);
        titleEdTxt = findViewById(R.id.edTitle);
        subTitleEdTxt = findViewById(R.id.edSubTitle);
        priorityGreen = findViewById(R.id.greenPriority);
        priorityYellow = findViewById(R.id.yellowPriority);
        priorityRed = findViewById(R.id.redPriority);
        notesEdTxt = findViewById(R.id.edNotes);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        priorityGreen.setOnClickListener(v -> {
            priorityGreen.setImageResource(R.drawable.ic_baseline_done_24);
            priorityYellow.setImageResource(0);
            priorityRed.setImageResource(0);
            priority = "1";
        });
        priorityYellow.setOnClickListener(v -> {
            priorityGreen.setImageResource(0);
            priorityYellow.setImageResource(R.drawable.ic_baseline_done_24);
            priorityRed.setImageResource(0);
            priority = "2";
        });
        priorityRed.setOnClickListener(v -> {
            priorityGreen.setImageResource(0);
            priorityYellow.setImageResource(0);
            priorityRed.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });
        noteSaveButton.setOnClickListener(V -> {

            isAllFieldsChecked = CheckAllFields();
//            Log.v(title, title.toString());
//            Log.d("a", "onCreate: notesViewModel.insertNote(notes1);\n");
            if (isAllFieldsChecked) {
                Toast.makeText(this, "Notes is Created Successfully", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "Error while creating Notes", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean CheckAllFields() {
        if (titleEdTxt.length() == 0) {
            titleEdTxt.setError("This field is required");
            return false;
        } else if (subTitleEdTxt.length() == 0) {
            subTitleEdTxt.setError("This field is required");
            return false;
        } else if (notesEdTxt.length() == 0) {
            notesEdTxt.setError("This field is required");
            return false;
        }
        String title = titleEdTxt.getText().toString();
        String subTitle = subTitleEdTxt.getText().toString();
        String descriptionNotes = notesEdTxt.getText().toString();
        Notes notes1 = new Notes();
        //date format
        Date date = new Date();
        //CharSequence charSequence = ;
        CharSequence charSequence = DateFormat.format("MMMM dd yyyy", date.getTime());
        notes1.title = title;
        notes1.subTitle = subTitle;
        notes1.notes = descriptionNotes;
        notes1.priority = priority;
        notes1.date = charSequence.toString();
        notesViewModel.insertNote(notes1);
        return true;
    }
}