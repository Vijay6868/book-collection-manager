package com.example.book_collection_manager;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookManager {

    public void addBook(String title, String author, String genre, String year) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("books");
        String id = databaseReference.push().getKey();
        Book book = new Book(id, title, author, genre, year);
        databaseReference.child(id).setValue(book);
    }
    public void updateBook(String id, String title, String author, String genre, String year) {
        DatabaseReference bookReference = FirebaseDatabase.getInstance().getReference("books").child(id);
        Book book = new Book(id, title, author, genre, year);
        bookReference.setValue(book);
    }
    public void deleteBook(String id) {
        DatabaseReference bookReference = FirebaseDatabase.getInstance().getReference("books").child(id);
        bookReference.removeValue();
    }
    public void readBooks(BookCallback bookCallback){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("books");
        ArrayList<Book> bookArrayList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Book book = dataSnapshot.getValue(Book.class);
                    if(book!=null){
                        bookArrayList.add(book);
                    }
                }
                bookCallback.onCallback(bookArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
