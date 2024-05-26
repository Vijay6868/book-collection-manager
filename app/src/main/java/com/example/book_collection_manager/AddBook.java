package com.example.book_collection_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBook extends AppCompatActivity {
    BookManager bookManager;
    EditText et_title, et_author, et_genre, et_datePublished;
    String title, author, genre, datePublished;
    Button btAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookManager = new BookManager();
        input();
        handleBtAdd();
        //addBook();
    }

    private void handleBtAdd() {
        btAdd = findViewById(R.id.bt_add);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                addBook();
            }
        });
    }

    private void input() {
        et_title = findViewById(R.id.et_title);
        et_author = findViewById(R.id.et_author);
        et_genre = findViewById(R.id.et_genre);
        et_datePublished = findViewById(R.id.et_datePublished);

        title = et_title.getText().toString();
        author = et_author.getText().toString();
        genre = et_genre.getText().toString();
        datePublished = et_datePublished.getText().toString();

    }

    private void addBook() {
        bookManager.addBook(title,author,genre,datePublished);
    }

}