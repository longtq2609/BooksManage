package com.example.bookmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.dao.UserDAO;



public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbBookManagement";
    public static  final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_USER);
        db.execSQL(CategoryDAO.SQL_Category);
        db.execSQL(BookDAO.SQL_Book);
        db.execSQL(BillDAO.SQL_Bill);
        db.execSQL(DetailBillDAO.SQL_DetailBill);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +UserDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +CategoryDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +BookDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +BillDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DetailBillDAO.TABLE_NAME);

        onCreate(db);
    }
}
