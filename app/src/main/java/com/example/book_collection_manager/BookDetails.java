package com.example.book_collection_manager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetSocketAddress;

public class BookDetails extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_SELECT = 2;
    EditText et_title, et_author, et_genre,et_datePublished;
    TextView w_title, w_author, w_genre, w_datePublished;
    String id, title, author, genre, datePublished;
    Button btBack, btDelete;
    String _title, _author, _genre, _datePublished;
    BookManager bookManager;
    ImageView ic_edit, ic_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        input();
        getBookDetails();
        setBooDetails();
        handleBtDelete();
        handleBtBack();
        warninglabels();
        //validateInputs();
        handleIcEdit();
        handleIcDone();
        handleIcCamera();
        //getInputData();
        handleFieldEditStatus(false);
        bookManager = new BookManager();

    }

    private void handleIcCamera() {
        ImageView btSelectImage = findViewById(R.id.ic_camera);
        btSelectImage.setOnClickListener(v -> {
            Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_SELECT);
        });
    }

    private void handleIcDone() {
        ic_done = findViewById(R.id.ic_done);
        ic_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputData();
                boolean check = validateInputs();
                if(check){
                    ic_done.setVisibility(View.GONE);
                    ic_edit.setVisibility(View.VISIBLE);
                    handleFieldEditStatus(false);
                    bookManager.updateBook(id,_title,_author,_genre,_datePublished);
                    Toast.makeText(BookDetails.this, "Book Details Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handleFieldEditStatus(boolean b) {
        et_datePublished.setEnabled(b);
        et_title.setEnabled(b);
        et_author.setEnabled(b);
        et_genre.setEnabled(b);

    }


    private void handleIcEdit() {
        ic_edit = findViewById(R.id.ic_edit);
        ic_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic_edit.setVisibility(View.GONE);
                ic_done.setVisibility(View.VISIBLE);
                handleFieldEditStatus(true);
            }
        });
    }

    private void getInputData() {
       _title = et_title.getText().toString();
       _author = et_author.getText().toString();
       _genre = et_author.getText().toString();
       _datePublished = et_datePublished.getText().toString();
    }

    private boolean validateInputs() {

        boolean isValid = true;
        if(!isValidString(_title,3)){
            isValid = false;
            w_title.setVisibility(View.VISIBLE);
        }
        else {
            w_title.setVisibility(View.INVISIBLE);
        }
        if(!isValidString(_author,3)){
            isValid = false;
            w_author.setVisibility(View.VISIBLE);
        }
        else {
            w_author.setVisibility(View.INVISIBLE);
        }
        if(!isValidString(_genre,3)){
            isValid = false;
            w_genre.setVisibility(View.VISIBLE);
        }
        else {
            w_genre.setVisibility(View.INVISIBLE);
        }


        if (_datePublished.length() != 4) {
            isValid = false;
            w_datePublished.setVisibility(View.VISIBLE);
        } else {
            try {
                // Try to parse the year
                Integer.parseInt(_datePublished);
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

    private void warninglabels() {
        w_title= findViewById(R.id.wlb_title);
        w_author = findViewById(R.id.wlb_author);
        w_genre = findViewById(R.id.wlb_genre);
        w_datePublished = findViewById(R.id.wlb_datePublished);
    }

    private void handleBtBack() {
        btBack = findViewById(R.id.bt_Back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(BookDetails.this, MainActivity.class);
        startActivity(intent);
    }

    private void handleBtDelete() {
        btDelete = findViewById(R.id.bt_Delete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookDetails.this);
                builder.setMessage("Are you sure you want to delete this Book record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BookDetails.this, "Book record deleted", Toast.LENGTH_SHORT).show();
                        bookManager.deleteBook(id);
                        goToMainActivity();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void setBooDetails() {
        et_title.setText(title);
        et_author.setText(author);
        et_genre.setText(genre);
        et_datePublished.setText(datePublished);
    }

    private void getBookDetails() {
        Intent intent = getIntent();
        id = intent.getStringExtra("BOOK_ID");
        title = intent.getStringExtra("BOOK_TITLE");
        author = intent.getStringExtra("BOOK_AUTHOR");
        genre = intent.getStringExtra("BOOK_GENRE");
        datePublished = intent.getStringExtra("BOOK_YEAR");
    }

    private void input() {
        et_title = findViewById(R.id.et_title);
        et_author = findViewById(R.id.et_author);
        et_genre = findViewById(R.id.et_genre);
        et_datePublished = findViewById(R.id.et_datePublished);
    }
    private static boolean isValidString(String attribute, int minLength) {
        return attribute != null && attribute.length() >= minLength;
    }
}