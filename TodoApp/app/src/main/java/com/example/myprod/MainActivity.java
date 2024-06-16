package com.example.myprod;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_TASK_REQUEST = 1;
    private TaskDatabaseHelper databaseHelper;
    private TaskAdapter adapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new TaskDatabaseHelper(this);
        taskList = databaseHelper.getAllTasks();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String status = data.getStringExtra("status");

            Task task = new Task(title, description, status);
            databaseHelper.addTask(task);
            taskList.add(task);
            adapter.notifyItemInserted(taskList.size() - 1);
        }
    }
}
