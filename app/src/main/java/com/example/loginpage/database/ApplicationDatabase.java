package com.example.loginpage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.loginpage.database.book.Book;
import com.example.loginpage.database.book.BookDao;
import com.example.loginpage.database.user.User;
import com.example.loginpage.database.user.UserDao;

@Database(entities = {User.class, Book.class}, version = 2, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract BookDao bookDao();

    private static ApplicationDatabase INSTANCE;

    public static ApplicationDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "dbBookApp")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}