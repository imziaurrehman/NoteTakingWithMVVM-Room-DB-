package com.example.notetakerprojectsimister5thcs.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NOTES_DATABASE")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "subtitle")
    public String subTitle;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "priority")
    public String priority;
    @ColumnInfo(name = "date")
    public String date;
}
