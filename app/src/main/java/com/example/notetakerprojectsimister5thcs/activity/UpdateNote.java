package com.example.notetakerprojectsimister5thcs.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notetakerprojectsimister5thcs.R;
import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.view_models.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;
import java.util.Objects;

public class UpdateNote extends AppCompatActivity {
    EditText updateNoteTitle, updateNoteSubTitle, updateNotes;
    ImageView updateRedPriority, updateGreenPriority, updateYellowPriority;
    Button updateNoteBtn;
    NotesViewModel notesViewModel;
    String priority = "1";
    boolean isAllFieldsChecked = false;
    String uTitle, uSubTitle, uNotes, uPriority;
    int uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        // toolbar stuffs
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Note Activity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_700)));
        getSupportActionBar().setElevation(0);
        // fields initialization
        updateNoteBtn = findViewById(R.id.updateNoteBtn);
        updateNoteTitle = findViewById(R.id.edUpdateTitle);
        updateNoteSubTitle = findViewById(R.id.edUpdateSubTitle);
        updateNotes = findViewById(R.id.edUpdateNotes);
        updateGreenPriority = findViewById(R.id.greenUpdatePriority);
        updateYellowPriority = findViewById(R.id.yellowUpdatePriority);
        updateRedPriority = findViewById(R.id.redUpdatePriority);
        // get object of notesViewModel class
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        // get notes data from recyclerViewAdaptor class
        Bundle bundle = getIntent().getExtras();
        uId = bundle.getInt("id", 0);
        uTitle = bundle.getString("title");
        uSubTitle = bundle.getString("subtitle");
        uNotes = bundle.getString("note");
        uPriority = getIntent().getStringExtra("priority");

        // updates here
        updateNoteTitle.setText(uTitle);
        updateNoteSubTitle.setText(uSubTitle);
        updateNotes.setText(uNotes);
        // place prev checked in updates
        if (uPriority.contains("1")) {
            updateGreenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateYellowPriority.setImageResource(0);
            updateRedPriority.setImageResource(0);
        } else if (uPriority.contains("2")) {
            updateGreenPriority.setImageResource(0);
            updateYellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateRedPriority.setImageResource(0);
        } else if (uPriority.contains("3")) {
            updateGreenPriority.setImageResource(0);
            updateYellowPriority.setImageResource(0);
            updateRedPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        // priorities clickable and set here
        updateGreenPriority.setOnClickListener(v -> {
            updateGreenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateYellowPriority.setImageResource(0);
            updateRedPriority.setImageResource(0);
            priority = "1";
        });
        updateYellowPriority.setOnClickListener(v -> {
            updateGreenPriority.setImageResource(0);
            updateYellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateRedPriority.setImageResource(0);
            priority = "2";
        });
        updateRedPriority.setOnClickListener(v -> {
            updateGreenPriority.setImageResource(0);
            updateYellowPriority.setImageResource(0);
            updateRedPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });
// update notes btn
        updateNoteBtn.setOnClickListener(V -> {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                Toast.makeText(this, "Notes is Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error while Updating Notes", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean CheckAllFields() {
        if (updateNoteTitle.length() == 0) {
            updateNoteTitle.setError("This field is required");
            return false;
        } else if (updateNoteSubTitle.length() == 0) {
            updateNoteSubTitle.setError("This field is required");
            return false;
        } else if (updateNotes.length() == 0) {
            updateNotes.setError("This field is required");
            return false;
        }
        String updateTitle = updateNoteTitle.getText().toString();
        String updateSubTitle = updateNoteSubTitle.getText().toString();
        String updateDescriptionNotes = updateNotes.getText().toString();
        Notes upNotes = new Notes();
        //date format
        Date date = new Date();
        //CharSequence charSequence = ;
        CharSequence charSequence = DateFormat.format("MMMM dd yyyy", date.getTime());
        upNotes.id = uId;
        upNotes.title = updateTitle;
        upNotes.subTitle = updateSubTitle;
        upNotes.notes = updateDescriptionNotes;
        upNotes.priority = priority;
        upNotes.date = charSequence.toString();
        notesViewModel.updateNote(upNotes);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_delete) {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNote.this);
            View view = LayoutInflater.from(UpdateNote.this).inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.deleteBottomSheetLayout));
            sheetDialog.setContentView(view);
            TextView delNo, delYes;
            delNo = view.findViewById(R.id.delete_no);
            delYes = view.findViewById(R.id.delete_yes);
            delNo.setOnClickListener(view1 -> {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                sheetDialog.dismiss();
            });
            delYes.setOnClickListener(view2 -> {
                notesViewModel.deleteNote(uId);
                Toast.makeText(this, "Notes is Canceled from delete", Toast.LENGTH_SHORT).show();
                finish();
            });
            sheetDialog.show();
        }
        return true;
    }
}
