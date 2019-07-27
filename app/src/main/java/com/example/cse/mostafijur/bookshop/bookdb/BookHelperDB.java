package com.example.cse.mostafijur.bookshop.bookdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BookHelperDB extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "book_db";
    public static final int DATABASE_VERSION = 1;

    public static final String BOOK_TABLE = "tbl_book";
    public static final String TBL_BOOK_COL_ID = "_bookid";
    public static final String TBL_BOOK_COL_NAME = "book_name";
    public static final String TBL_BOOK_COL_CATEGORY = "book_category";
    public static final String TBL_BOOK_COL_PRICE = "book_price";

    public static final String CREATE_TBL_BOOK =
            "CREATE TABLE "+BOOK_TABLE
            +"("+TBL_BOOK_COL_ID+" INTEGER PRIMARY KEY, "
            +TBL_BOOK_COL_NAME+" TEXT, "
            +TBL_BOOK_COL_CATEGORY+" TEXT, "
            +TBL_BOOK_COL_PRICE+" REAL);";

    public BookHelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_BOOK);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
