package com.example.cse.mostafijur.bookshop.bookdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cse.mostafijur.bookshop.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDataSource {
    private BookHelperDB helperDB;
    private SQLiteDatabase db;

    public BookDataSource(Context context){
        helperDB = new BookHelperDB(context);
    }
    public void open(){
        db = helperDB.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public boolean insertNewBooks(Book book){
        this.open();
        ContentValues values = new ContentValues();
        values.put(BookHelperDB.TBL_BOOK_COL_NAME,book.getName());
        values.put(BookHelperDB.TBL_BOOK_COL_CATEGORY,book.getCategory());
        values.put(BookHelperDB.TBL_BOOK_COL_PRICE,book.getPrice());
        long insertedRow = db.insert(BookHelperDB.BOOK_TABLE,null,values);
        this.close();
        if (insertedRow > 0){
            return true;
        }
        return false;
    }
    public List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();
        this.open();
        Cursor cursor = db.rawQuery("SELECT * FROM "+BookHelperDB.BOOK_TABLE,null);
        if (cursor != null && cursor.getCount() >0){
            cursor.moveToFirst();
            for (int i=0; i < cursor.getCount(); i++){
                int id = cursor.getInt(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_NAME));
                String category = cursor.getString(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_CATEGORY));
                double price = cursor.getDouble(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_PRICE));
                Book book = new Book(id, name, category, price);
                bookList.add(book);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();
        return bookList;
    }
    public Book getBookById(int rowId){
        Book book = null;
        this.open();
        Cursor cursor = db.rawQuery("select * from "+BookHelperDB.BOOK_TABLE+
        " where "+BookHelperDB.TBL_BOOK_COL_ID+" = "+rowId,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_NAME));
            String category = cursor.getString(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_CATEGORY));
            double price = cursor.getDouble(cursor.getColumnIndex(BookHelperDB.TBL_BOOK_COL_PRICE));
            book = new Book(rowId, name, category, price);
        }
        cursor.close();
        this.close();
        return book;
    }
    public boolean deleteBook(int rowId){
        this.open();
        int deletedRow = db.delete(BookHelperDB.BOOK_TABLE,
                BookHelperDB.TBL_BOOK_COL_ID+" = "+rowId,null);
        this.close();
        if (deletedRow > 0){
            return true;
        }
        return false;
    }
    public boolean updateBook(Book book){
        this.open();
        ContentValues values = new ContentValues();
        values.put(BookHelperDB.TBL_BOOK_COL_NAME,book.getName());
        values.put(BookHelperDB.TBL_BOOK_COL_CATEGORY,book.getCategory());
        values.put(BookHelperDB.TBL_BOOK_COL_PRICE,book.getPrice());
        final int updatedRow = db.update(BookHelperDB.BOOK_TABLE,values,
                BookHelperDB.TBL_BOOK_COL_ID+" = "+book.getBookId(),
                null);
        this.close();
        if (updatedRow >0 ){
            return true;
        }
        return false;
    }
}
