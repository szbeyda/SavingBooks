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
import android.widget.EditText;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.book.Book;

import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;


public class BookListFragment extends Fragment {
    private RecyclerView recyclerView;
    private EditText search;
    private BookListAdapter adapter;
    private List<Book> bookList;
    private List<Book> filteredBookList;
    private int bookCount = 0;


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
        bookList = db.bookDao().getUserBooks(((MainActivity2) getActivity()).incUId);
        filteredBookList = new ArrayList<>(bookList);
        adapter = new BookListAdapter(bookList);
        recyclerView.setAdapter(adapter);
        search = view.findViewById(R.id.searchBook);
        // Arama kutusuna metin girildikçe tetiklenecek TextWatcher eklenir
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Filtreleme işlemi gerçekleştirilir
                filter(editable.toString());
            }
        });
    }

    public void updateBookCount() {
        if (adapter != null) {
            adapter.notifyDataSetChanged(); // Adapter'i güncelleyerek sayaç değerini yansıtır
        }
    }


    private void filter(String text){
        filteredBookList.clear();
        if (text.isEmpty()){
            filteredBookList.addAll(bookList);
        }
        else {
            for (Book book : bookList) {
                if(book.getBookName().toLowerCase().contains(text.toLowerCase()) || book.getBookWriter().toLowerCase().contains(text.toLowerCase())) {
               filteredBookList.add(book);
           }
        }
    }
        adapter.setBooks(filteredBookList); // adapter içindeki kitap listesini güncelleyin
        adapter.notifyDataSetChanged();

}

}