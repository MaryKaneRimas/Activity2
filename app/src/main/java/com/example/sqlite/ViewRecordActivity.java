package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewRecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<RecordModelClass> recordModelClasses = databaseHelperClass.getRecordList();

        if (recordModelClasses.size() > 0){
            RecordAdapterClass recordAdapterClass = new RecordAdapterClass(recordModelClasses, ViewRecordActivity.this);
            recyclerView.setAdapter(recordAdapterClass);
        }else {
            Toast.makeText(this, "There is no student record in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
