package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.loginpage.database.ApplicationDatabase;
import com.example.loginpage.database.book.BookDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView btmnavi;
    public int incUId;

    AddBookFragment addBookFragment;
    BookListFragment bookListFragment;

    // BookDao sınıfından bir referans oluşturun
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent incIntent = getIntent();
        incUId = incIntent.getIntExtra("intentUId", 0);

        Bundle data = new Bundle();
        data.putInt("userId", incUId);

        btmnavi = findViewById(R.id.btmnavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new BookListFragment()).commit(); //default gelir
        btmnavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { //botoom navi içi tıklamalar
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.booklist) {
                    bookListFragment = new BookListFragment();
                    addBookFragment.setArguments(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new BookListFragment()).commit();
                } else if (item.getItemId() == R.id.addbook) {
                    addBookFragment = new AddBookFragment();
                    addBookFragment.setArguments(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, addBookFragment).commit();
                } else if (item.getItemId() == R.id.user) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new PersonFragment()).commit();
                }
                return true;
            }
        });

        // BookDao sınıfının örneğini alın
        bookDao = ApplicationDatabase.getDbInstance(this).bookDao();

       /* // Kitap sayacını güncelleyin ve görüntüleyin
        updateBookCount();*/

    }
    // Kitap sayacını güncellemek için bir metod oluşturun
   /* private void updateBookCount() {
        int bookCount = bookDao.getBookCount(); // Kitap sayısını alın
        // Kitap sayacını güncelleyin (XML dosyasında yer alan TextView'in id'sini kullanarak)
        TextView bookCountTextView = findViewById(R.id.bookCountTextView);
        bookCountTextView.setText("Number Of Registered Books: " + bookCount);}*/


        public void onBackPressed () { //geriye- 1.intent sıfırla
            Intent backIntent = new Intent(MainActivity2.this, MainActivityLogin.class);
            finish();
            startActivity(backIntent);


        }

    }

