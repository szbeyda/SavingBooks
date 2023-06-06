package com.example.loginpage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.book.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddBookFragment extends Fragment {
    private EditText bookNameEditText;
    //private EditText edtImageUrl;
    private ImageView bookImageView;
    private EditText bookWriterEditText;
    private Button selectImageButton;

    private EditText bookSummaryEditText;
    private Button saveButton;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    private ApplicationDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        Bundle arguments = getArguments();
        int userId = arguments.getInt("userId");
        bookNameEditText = view.findViewById(R.id.bookName);
        bookWriterEditText = view.findViewById(R.id.bookWriter);
        bookSummaryEditText = view.findViewById(R.id.bookSummary);
        //edtImageUrl = view.findViewById(R.id.bookImage);
        selectImageButton = view.findViewById(R.id.selectImageButton); // Resim seçme düğmesi
        saveButton = view.findViewById(R.id.saveButton);
        bookImageView = view.findViewById(R.id.bookImageView);

        db = ApplicationDatabase.getDbInstance(this.getContext());

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = bookNameEditText.getText().toString();
                String bookWriter = bookWriterEditText.getText().toString();
                String bookSummary = bookSummaryEditText.getText().toString();
                // String imageUrl = edtImageUrl.getText().toString();
                String imageUrl = selectedImageUri.toString(); // Seçilen resim Uri'sini al


                // Yeni bir Book nesnesi oluştur ve listeye ekle
                Book newBook = new Book();
                newBook.setBookImage(selectedImageUri.toString()); // Book nesnesine resim URL'sini ayarla
                //newBook.setBookImage(selectedImageUri.toString());
                // newBook.setBookImage(imageUrl);
                newBook.setBookName(bookName);
                newBook.setBookWriter(bookWriter);
                newBook.setBookSummary(bookSummary);
                newBook.setUserId(userId);
                db.bookDao().insert(newBook);

                // EditText alanlarını temizle
                bookNameEditText.setText("");
                bookWriterEditText.setText("");
                bookSummaryEditText.setText("");
                bookImageView.setImageURI(null);

                //bookImageView.setImageDrawable(getResources().getDrawable(R.drawable.gzp));
                //edtImageUrl.setText("");
                // Seçilen resmi göster
                if (selectedImageUri != null) {
                    bookImageView.setImageURI(selectedImageUri);
                }
            }
        });

        bookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            bookImageView.setImageURI(data.getData());
            //bookImageView.invalidate(); // Ekranı güncellemek için invalidate() metodunu çağırın
        }
    }
}
