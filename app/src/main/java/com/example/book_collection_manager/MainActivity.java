package com.example.book_collection_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectedListener {
    BookManager bookManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookManager = new BookManager();
       // FirebaseDatabase.getInstance().getReference().setValue("hello");
        handleBtAdd();
        readBooks();


    }
    private void recView(ArrayList<Book> arrayList){
        recyclerView = findViewById(R.id.f_rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter adapter = new RVAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
    private void readBooks() {
        bookManager.readBooks(new BookCallback() {
            @Override
            public void onCallback(ArrayList<Book> bookArrayList) {
                recView(bookArrayList);
            }
        });
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

    @Override
    public void onItemClicked(Book book) {
        Intent intent = new Intent(this, BookDetails.class);
        intent.putExtra("BOOK_ID", book.getId());
        intent.putExtra("BOOK_TITLE", book.getTitle());
        intent.putExtra("BOOK_AUTHOR", book.getAuthor());
        intent.putExtra("BOOK_YEAR", book.getPublicationYear());
        intent.putExtra("BOOK_GENRE", book.getGenre());
        startActivity(intent);
    }
}