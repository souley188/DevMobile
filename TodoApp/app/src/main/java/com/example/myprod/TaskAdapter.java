package com.example.myprod;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.status.setText(task.getStatus());

        switch (task.getStatus()) {
            case "Todo":
                holder.status.setBackgroundColor(Color.GRAY);
                break;
            case "In progress":
                holder.status.setBackgroundColor(Color.BLUE);
                break;
            case "Done":
                holder.status.setBackgroundColor(Color.GREEN);
                break;
            case "Bug":
                holder.status.setBackgroundColor(Color.RED);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, status;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            status = itemView.findViewById(R.id.textViewStatus);
        }
    }
}
