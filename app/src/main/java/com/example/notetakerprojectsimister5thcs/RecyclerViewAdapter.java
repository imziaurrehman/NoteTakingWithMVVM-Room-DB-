package com.example.notetakerprojectsimister5thcs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetakerprojectsimister5thcs.activity.UpdateNote;
import com.example.notetakerprojectsimister5thcs.models.Notes;
import com.example.notetakerprojectsimister5thcs.view_models.NotesViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    public List<Notes> notess;
    public List<Notes> searchNotesList;
    public MainActivity mainActivity;
//    private deleteItemsOnClicked deleteClicked;

    public RecyclerViewAdapter(MainActivity mcontext, List<Notes> notes) {
        this.mainActivity = mcontext;
        this.notess = notes;
        this.searchNotesList=new ArrayList<>(notes);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSearchNotesList(List<Notes> filteredNotes)
    {
        this.notess = filteredNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.notes_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        ImageView popUpMenuButton;
        Notes note = notess.get(position);
        holder.titleTextView.setText(note.title);
        holder.subTitleTextView.setText(note.subTitle);
        holder.datesTextView.setText(note.date);
        switch (note.priority) {
            case "1":
                holder.priorityTagTextView.setBackgroundResource(R.drawable.green_dot);
                break;
            case "2":
                holder.priorityTagTextView.setBackgroundResource(R.drawable.yellow_dot);

                break;
            case "3":
                holder.priorityTagTextView.setBackgroundResource(R.drawable.red_dot);

                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateNote.class);
                intent.putExtra("id", note.id);
                intent.putExtra("title", note.title);
                intent.putExtra("subtitle", note.subTitle);
                intent.putExtra("note", note.notes);
                intent.putExtra("priority", note.priority);
//                deleteClicked.deleteClicked(note);
                view.getContext().startActivity(intent);
            }
        });
//        holder.itemView.setOnLongClickListener();


    }
    public interface  deleteItemsOnClicked{
        void deleteClicked(Notes notes);
    }

    @Override
    public int getItemCount() {
        return this.notess.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView subTitleTextView;
        public TextView datesTextView;
        public View priorityTagTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleView);
            subTitleTextView = itemView.findViewById(R.id.subTitleView);
//            nodesTextView = itemView.findViewById(R.id.notesView);
            datesTextView = itemView.findViewById(R.id.datesView);
            priorityTagTextView = itemView.findViewById(R.id.priorityTag);
        }
    }
}
