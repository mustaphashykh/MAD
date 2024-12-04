package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextEditNoteTitle, editTextEditNoteDescription;
    private Button buttonUpdateNote, buttonDeleteNote, buttonCancel;
    private NotesDatabaseHelper databaseHelper;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Initialize Views
        editTextEditNoteTitle = findViewById(R.id.editTextEditNoteTitle);
        editTextEditNoteDescription = findViewById(R.id.editTextEditNoteDescription);
        buttonUpdateNote = findViewById(R.id.buttonUpdateNote);
        buttonDeleteNote = findViewById(R.id.buttonDeleteNote);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Initialize Database Helper
        databaseHelper = new NotesDatabaseHelper(this);

        // Get Data from Intent
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        String noteTitle = intent.getStringExtra("noteTitle");
        String noteDescription = intent.getStringExtra("noteDescription");

        // Set Data in Input Fields
        editTextEditNoteTitle.setText(noteTitle);
        editTextEditNoteDescription.setText(noteDescription);

        // Update Note
        buttonUpdateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = editTextEditNoteTitle.getText().toString().trim();
                String updatedDescription = editTextEditNoteDescription.getText().toString().trim();

                if (updatedTitle.isEmpty() || updatedDescription.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int rowsAffected = databaseHelper.updateNote(noteId, updatedTitle, updatedDescription);
                    if (rowsAffected > 0) {
                        Toast.makeText(EditNoteActivity.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditNoteActivity.this, "Failed to update note", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Delete Note
        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteNote(noteId);
                Toast.makeText(EditNoteActivity.this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity without saving changes
                Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
