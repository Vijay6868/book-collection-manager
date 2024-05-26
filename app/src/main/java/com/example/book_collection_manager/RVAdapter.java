package com.example.book_collection_manager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVHolder> {
    ArrayList<Book> bookArrayList;
    private SelectedListener selectedListener;

    RVAdapter(ArrayList<Book> bookArrayList, SelectedListener selectedListener){
        this.bookArrayList = bookArrayList;
        this.selectedListener = selectedListener;

    }
    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_carditems,parent,false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder holder, int position) {
        Book book = bookArrayList.get(position);
        holder.tv_title.setText(book.getTitle());
        holder.tv_genre.setText(book.getGenre());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selectedListener.onItemClicked(book);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
}
