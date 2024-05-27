package com.example.book_collection_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {
    BookManager bookManager;
    EditText et_title, et_author, et_genre, et_datePublished;
    String title, author, genre, datePublished;
    Button btAdd;
    ImageView ic_close;
    TextView w_title, w_author, w_genre, w_datePublished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookManager = new BookManager();
        input();
        handleBtAdd();
        handleIcClose();
        warninglabels();

        //addBook();
    }


    private void handleIcClose() {
        ic_close = findViewById(R.id.ic_close);
        ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBook.this, MainActivity.class);
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
                boolean check= validateInputs();
                if(check){
                    addBook();
                    Toast.makeText(AddBook.this, "Book Successfully added!", Toast.LENGTH_SHORT).show();
                }

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
        bookManager.addBook(title, author, genre, datePublished);
    }

    private void warninglabels() {
        w_title = findViewById(R.id.wlb_title);
        w_author = findViewById(R.id.wlb_author);
        w_genre = findViewById(R.id.wlb_genre);
        w_datePublished = findViewById(R.id.wlb_datePublished);
    }

    private static boolean isValidString(String attribute, int minLength) {
        return attribute != null && attribute.length() >= minLength;
    }

    private boolean validateInputs() {

        boolean isValid = true;
        if (!isValidString(title, 3)) {
            isValid = false;
            w_title.setVisibility(View.VISIBLE);
        } else {
            w_title.setVisibility(View.INVISIBLE);
        }
        if (!isValidString(author, 3)) {
            isValid = false;
            w_author.setVisibility(View.VISIBLE);
        } else {
            w_author.setVisibility(View.INVISIBLE);
        }
        if (!isValidString(genre, 3)) {
            isValid = false;
            w_genre.setVisibility(View.VISIBLE);
        } else {
            w_genre.setVisibility(View.INVISIBLE);
        }


        if (datePublished.length() != 4) {
            isValid = false;
            w_datePublished.setVisibility(View.VISIBLE);
        } else {
            try {
                // Try to parse the year
                Integer.parseInt(datePublished);
                // If parsing succeeds, hide the warning
                w_datePublished.setVisibility(View.INVISIBLE);
            } catch (NumberFormatException e) {
                // If parsing fails, show the warning
                w_datePublished.setVisibility(View.VISIBLE);
                isValid = false;
            }
        }
        return isValid;

    }
}