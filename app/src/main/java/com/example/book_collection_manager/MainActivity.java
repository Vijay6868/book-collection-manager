package com.example.book_collection_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // FirebaseDatabase.getInstance().getReference().setValue("hello");
        handleBtAdd();
    }

    private void handleBtAdd() {
       FloatingActionButton btAdd = findViewById(R.id.bt_floatingAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this ,AddBook.class);
                startActivity(intent);
            }
        });
    }
}