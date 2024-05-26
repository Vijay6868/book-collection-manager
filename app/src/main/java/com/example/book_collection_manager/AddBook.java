package com.example.book_collection_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {
    BookManager bookManager;
    EditText et_title, et_author, et_genre, et_datePublished;
    String title, author, genre, datePublished;
    Button btAdd;
    ImageView ic_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookManager = new BookManager();
        input();
        handleBtAdd();
        handleIcClose();
        //addBook();
    }

    private void handleIcClose() {
        ic_close = findViewById(R.id.ic_close);
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBook.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleBtAdd() {
        btAdd = findViewById(R.id.bt_add);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                addBook();
                Toast.makeText(AddBook.this, "Book Successfully added!", Toast.LENGTH_SHORT).show();
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