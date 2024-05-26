package com.example.book_collection_manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookManager {

    public void addBook(String title, String author, String genre, String year) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("books");
        String id = databaseReference.push().getKey();
        Book book = new Book(id, title, author, genre, year);
        databaseReference.child(id).setValue(book);
    }
    private void updateBook(String id, String title, String author, String genre, String year) {
        DatabaseReference bookReference = FirebaseDatabase.getInstance().getReference("books").child(id);
        Book book = new Book(id, title, author, genre, year);
        bookReference.setValue(book);
    }
    private void deleteBook(String id) {
        DatabaseReference bookReference = FirebaseDatabase.getInstance().getReference("books").child(id);
        bookReference.removeValue();
    }
}
