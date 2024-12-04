package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private NotesDatabaseHelper databaseHelper;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;
    private Button addNoteButton;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Initialize RecyclerView
            notesRecyclerView = findViewById(R.id.notesRecyclerView);
            if (notesRecyclerView == null) {
                Log.e(TAG, "RecyclerView not found in the layout.");
                return;
            }
            notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Initialize Add Note Button
            addNoteButton = findViewById(R.id.addNoteButton);
            if (addNoteButton == null) {
                Log.e(TAG, "Add Note Button not found in the layout.");
                return;
            }

            // Initialize Database Helper
            databaseHelper = new NotesDatabaseHelper(this);

            // Fetch Notes from Database
            noteList = new ArrayList<>();
            noteList.addAll(databaseHelper.getAllNotes());

            // Set Adapter for RecyclerView
            noteAdapter = new NoteAdapter(this, noteList);
            notesRecyclerView.setAdapter(noteAdapter);

            // Add Note Button Click Listener
            addNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error initializing MainActivity: " + e.getMessage(), e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            // Reload notes when returning to this activity
            noteList.clear(); // Clear the existing list
            noteList.addAll(databaseHelper.getAllNotes()); // Add updated notes from the database
            noteAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
        } catch (Exception e) {
            Log.e(TAG, "Error reloading notes in onResume: " + e.getMessage(), e);
        }
    }
}
