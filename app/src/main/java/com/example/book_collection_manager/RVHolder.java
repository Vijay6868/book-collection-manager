package com.example.book_collection_manager;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



public class RVHolder extends RecyclerView.ViewHolder {

    TextView tv_title, tv_genre;
    CardView cardView;
    public RVHolder(@NonNull View itemView) {
        super(itemView);
        tv_genre = itemView.findViewById(R.id.tv_bookGenre);
        tv_title = itemView.findViewById(R.id.tv_bookTitle);
        cardView = itemView.findViewById(R.id.rv_carditems);
    }
}
