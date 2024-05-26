package com.example.book_collection_manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookManager {
    DatabaseReference databaseReference;

    public BookManager() {
       databaseReference = FirebaseDatabase.getInstance().getReference("books");;
    }
    public void addBook(String title, String author, String genre, String year) {
        String id = databaseReference.push().getKey();
        Book book = new Book(id, title, author, genre, year);
        databaseReference.child(id).setValue(book);
    }
}
