package com.example.book_collection_manager;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;

    public Book() {
        // Default constructor required for calls to DataSnapshot.getValue(Book.class)
    }

    public Book(String id, String title, String author, String genre, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Getters and setters
}

