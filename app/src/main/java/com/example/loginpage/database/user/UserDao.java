package com.example.loginpage.database.user;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * from User")
    List<User> getAll();

    @Query("SELECT * FROM USER WHERE username LIKE :userName AND password LIKE :password")
    LiveData<User> readAllData(String userName, String password);

    @Query("SELECT * FROM USER WHERE fie_id=:userId")
    LiveData<User> getUser(int userId);

    @Insert
    void insert(User user);


}
