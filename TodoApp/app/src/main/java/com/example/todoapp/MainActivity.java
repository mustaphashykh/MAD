package com.example.todoapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.adapters.TaskAdapter;
import com.example.todoapp.database.TodoDatabaseHelper;
import com.example.todoapp.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    private TodoDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        databaseHelper = new TodoDatabaseHelper(this);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load tasks from the database
        taskList = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, taskList);
        recyclerView.setAdapter(taskAdapter);

        // Handle "Add Task" button click
        FloatingActionButton addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> showAddTaskDialog());
    }

    // Show a custom dialog to input a new task
    private void showAddTaskDialog() {
        // Inflate the custom dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null);

        // Initialize dialog components
        EditText taskInput = dialogView.findViewById(R.id.taskInput);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button addButton = dialogView.findViewById(R.id.addButton);

        // Create the dialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        // Handle Cancel button
        cancelButton.setOnClickListener(view -> dialog.dismiss());

        // Handle Add button
        addButton.setOnClickListener(view -> {
            String taskName = taskInput.getText().toString().trim();
            if (!TextUtils.isEmpty(taskName)) {
                databaseHelper.addTask(taskName); // Add task to database
                refreshTaskList(); // Refresh RecyclerView
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                taskInput.setError("Task name cannot be empty");
            }
        });

        dialog.show();
    }

    private void refreshTaskList() {
        taskList.clear();
        taskList.addAll(databaseHelper.getAllTasks());
        taskAdapter.notifyDataSetChanged();
    }
}
