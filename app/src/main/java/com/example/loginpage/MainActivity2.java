package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView btmnavi;
    public int incUId;

    AddBookFragment addBookFragment;
    BookListFragment bookListFragment;

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


    }


    public void onBackPressed() { //geriye- 1.intent sıfırla
        Intent backIntent = new Intent(MainActivity2.this, MainActivityLogin.class);
        finish();
        startActivity(backIntent);


    }


}
