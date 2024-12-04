package com.example.todoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.database.TodoDatabaseHelper;
import com.example.todoapp.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<Task> taskList;
    private TodoDatabaseHelper databaseHelper;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.databaseHelper = new TodoDatabaseHelper(context); // Initialize database helper
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getName());
        holder.taskCheckbox.setChecked(task.isCompleted());

        // Handle checkbox click to toggle task completion
        holder.taskCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            databaseHelper.updateTaskStatus(task.getId(), isChecked);
        });

        // Handle delete button click
        holder.deleteButton.setOnClickListener(v -> {
            databaseHelper.deleteTask(task.getId()); // Delete task from database
            taskList.remove(position); // Remove task from list
            notifyItemRemoved(position); // Notify RecyclerView about item removal
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        CheckBox taskCheckbox;
        ImageButton deleteButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskCheckbox = itemView.findViewById(R.id.taskCheckbox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
