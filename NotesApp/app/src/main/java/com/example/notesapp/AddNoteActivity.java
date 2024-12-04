package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNoteTitle, editTextNoteDescription;
    private Button buttonSaveNote, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Initialize Views
        editTextNoteTitle = findViewById(R.id.editTextNoteTitle);
        editTextNoteDescription = findViewById(R.id.editTextNoteDescription);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Save Note Button Click Listener
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the note logic
                String title = editTextNoteTitle.getText().toString().trim();
                String description = editTextNoteDescription.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    NotesDatabaseHelper databaseHelper = new NotesDatabaseHelper(AddNoteActivity.this);
                    databaseHelper.addNote(title, description);
                }

                // Navigate back to MainActivity
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Cancel Button Click Listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Direct back to MainActivity without saving
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
