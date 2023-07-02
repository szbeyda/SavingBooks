

package com.example.loginpage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpage.database.book.Book;

import java.io.File;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private List<Book> books;

    public BookListAdapter(List<Book> bookList) {
        this.books = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookName.setText(book.getBookName());
        holder.bookWriter.setText(book.getBookWriter());
        holder.bookSummary.setText(book.getBookSummary());
        if (book.getBookImage() != null) {
            Glide.with(holder.itemView.getContext()).load(Uri.parse(book.getBookImage())).into(holder.bookImage);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {

        this.books = books;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookWriter;
        public TextView bookSummary;
        public ImageView bookImage;

        public ViewHolder(View itemView) {
            super(itemView);
            this.bookImage = (ImageView) itemView.findViewById(R.id.bookImageView);
            this.bookName = (TextView) itemView.findViewById(R.id.bookNameTextView);
            this.bookWriter = (TextView) itemView.findViewById(R.id.bookWriterTextView);
            this.bookSummary = (TextView) itemView.findViewById(R.id.bookSummaryTextView);
        }
    }
}
