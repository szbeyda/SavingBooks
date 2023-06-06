package com.example.loginpage.database.book;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int fie_id;

    @ColumnInfo()
    private int userId;
    @ColumnInfo(name = "bookName")
    private String bookName;
    @ColumnInfo(name = "bookWriter")
    private String bookWriter;

    @ColumnInfo(name = "bookSummary")
    private String bookSummary;
    @ColumnInfo(name = "bookImage")
    private String bookImage;

    public int getFie_id() {
        return fie_id;
    }

    public void setFie_id(int fie_id) {
        this.fie_id = fie_id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
