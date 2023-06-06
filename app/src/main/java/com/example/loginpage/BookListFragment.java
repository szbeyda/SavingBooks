package com.example.loginpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.book.Book;

import java.util.ArrayList;
import java.util.List;


public class BookListFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookListAdapter adapter;
    private List<Book> bookList;

    public static int userId = 0;

    public BookListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView); // Doğru değişken adı kullanıldı
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApplicationDatabase db = ApplicationDatabase.getDbInstance(getContext());
        bookList = new ArrayList<>();
        bookList = db.bookDao().getUserBooks(((MainActivity2)getActivity()).incUId);

        adapter = new BookListAdapter(bookList);
        recyclerView.setAdapter(adapter);
    }

}