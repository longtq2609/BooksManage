package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookmanagement.database.DatabaseHelper;
import com.example.bookmanagement.model.Book;


import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String  TABLE_NAME = "Book";
    public static final String TAG = " TAG_Book";
    public static final String SQL_Book="CREATE TABLE Book (" +
            " idBook text primary key," +
            " idCategory text," +
            " tieuDe text," +
            " nxb text," +
            " tacGia text," +
            " giaBia text," +
            " soLuong number" +
            " );";
    public BookDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertBook(Book book){
        ContentValues values = new ContentValues();
        values.put("idBook", book.getIdBook());
        values.put("idCategory",  book.getIdCategory());
        values.put("tieuDe",  book.getTieuDe());
        values.put("nxb",  book.getNxb());
        values.put("tacGia",  book.getTacGia());
        values.put("giaBia",  book.getGiaBia());
        values.put("soLuong",  book.getSoLuong());
        if(checkKey(book.getIdBook())) {
            int result = db.update(TABLE_NAME, values, "idBook=?", new String[]{book.getIdBook()});
            if (result == 0) {
                return -1;
            }
            } else {
                try {
                    if (db.insert(TABLE_NAME, null, values) < 0) {
                        return -1;
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
        }
        return  1;
    }
    public List<Book> getAllBook(){
        List<Book> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Book book = new Book();
            book.setIdBook(cursor.getString(0));
            book.setIdCategory(cursor.getString(1));
            book.setTieuDe(cursor.getString(2));
            book.setNxb(cursor.getString(3));
            book.setTacGia(cursor.getString(4));
            book.setGiaBia(cursor.getDouble(5));
            book.setSoLuong(cursor.getInt(6));
            list.add(book);
            cursor.moveToNext();
            Log.d("+++",book.toString());


        }
        cursor.close();
        return list;
    }
    public int deleteBook(String idBook){
        int result = db.delete(TABLE_NAME,"idBook = ?",new String[]{idBook});
        if(result == 0){
            return -1;
        }
        return 1;
    }
    public int updateBook(Book book){
        ContentValues values = new ContentValues();
        values.put("idBook",book.getIdBook());
        values.put("idCategory",book.getIdCategory());
        values.put("tieuDe",book.getTieuDe());
        values.put("nxb",book.getNxb());
        values.put("tacGia",book.getTacGia());
        values.put("giaBia",book.getGiaBia());
        values.put("soLuong",book.getSoLuong());
        int result = db.update(TABLE_NAME, values,"idBook=?",new String[]{book.getIdBook()});
        if(result == 0){
            return -1;
        }
        return 1;
    }

    public boolean checkKey(String primaryKey){
        String [] columns = {"idBook"};
        String selection = "idBook=?";
        String [] selectionArgs = {primaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null,null,null);
            cursor.moveToFirst();
            int i = cursor.getCount();
            cursor.close();
            if(i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Book getBookByID(String idBook){
        Book book = null;
        String selection = "idBook=?";
        String [] selectionArgs = {idBook};
        Cursor cursor
                = db.query(TABLE_NAME, null, selection,selectionArgs,null,null,null,null);
        Log.d("getSachByID","====>"+cursor.getCount());
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            book = new Book();
            book.setIdBook(cursor.getString(0));
            book.setIdCategory(cursor.getString(1));
            book.setTieuDe(cursor.getString(2));
            book.setNxb(cursor.getString(3));
            book.setTacGia(cursor.getString(4));
            book.setGiaBia(cursor.getDouble(5));
            book.setSoLuong(cursor.getInt(6));
            break;
        }
        cursor.close();
        return book;
    }

    public List<Book> getSachTop10(String month ) {
        List<Book> books = new ArrayList<>();

        if(Integer.parseInt(month)<10) {
            month = "0" + month;
        }
            String sSQL ="SELECT idBook, SUM(soLuongMua) as soluong from DetailBill INNER JOIN Bill " +
                    "on Bill.idBill = DetailBill.idBill WHERE strftime('%m',Bill.dayBuy) = '"+month+"' " +
                    "GROUP BY idBook ORDER BY soluong DESC LIMIT 10";
            Cursor cursor = db.rawQuery(sSQL, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false){
                Log.d("//====", cursor.getString(0));
                Book book = new Book();
                book.setGiaBia(Double.valueOf(0));
                book.setTieuDe(cursor.getString(0));
                book.setSoLuong(cursor.getInt(1));
                books.add(book);
                cursor.moveToNext();
            }
            cursor.close();
            return books;
        }
        public Book checkBook(String strPrimaryKey){
        Book book = new Book();
        String[] columns = {"idBook"};
        String selection = "idBook=?";
        String [] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, columns,selection,selectionArgs, null,null,null);
            cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            book.setIdBook(cursor.getString(0));
            book.setIdCategory(cursor.getString(1));
            book.setTieuDe(cursor.getString(2));
            book.setNxb(cursor.getString(3));
            book.setTacGia(cursor.getString(4));
            book.setGiaBia(cursor.getDouble(5));
            book.setSoLuong(cursor.getInt(6));
            break;
        }
        cursor.close();
        return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
    }

