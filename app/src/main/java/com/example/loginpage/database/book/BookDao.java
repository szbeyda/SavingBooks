package com.example.loginpage.database.book;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BookDao {
    @Query("Select * from Book  where userId=:userId")
    List<Book> getUserBooks(int userId);

    @Insert
    void insert(Book book);


}
