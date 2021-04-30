package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;


import com.example.bookmanagement.database.DatabaseHelper;
import com.example.bookmanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "User";
    public static final String TAG = "TAG_User";
    public static final String SQL_USER="CREATE TABLE User (" +
            " username text primary key," +
            " password text," +
            " phone text," +
            " name text" +
            " );";
//    public static final String SQL_HDCT="CREATE TABLE HDCT (" +
//            " maHDCT integer primary key," +
//            " maSach text," +
//            " maHoaDon text," +
//            " soLuong text" +
//            " );";


    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("name", user.getName());
        try {
           if( db.insert(TABLE_NAME, null, values) < 0) {
               return -1;
           }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
       return  1;
    }

public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
       while (cursor.isAfterLast()==false){
            User user = new User();
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            user.setPhone(cursor.getString(2));
            user.setName(cursor.getString(3));
            list.add(user);
            cursor.moveToNext();
            Log.d("GetAll", user.toString());
        }
        cursor.close();
        return list;
}

public int deleteUser(String username){
        int kq =  db.delete(TABLE_NAME, "username = ?", new String[]{username});
        if(kq == 0){
            return -1;
        }return 1;
}
    public int updateUser(User user){
        ContentValues values = new ContentValues();
//        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("name", user.getName());
        try {
            if( db.update(TABLE_NAME,values, "username = ?",  new String[]{user.getUsername()}) < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return  1;
    }

    public int changePassword(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        if( db.update(TABLE_NAME,values, "username = ?",  new String[]{user.getUsername()}) < 0) {
            return -1;
        }
        return 1;
        }

    public int checkLogin(String username, String password) {
        int result = db.delete(TABLE_NAME,"username=? AND password=?",new String[]{username,password});
            if(result == 0){
                return -1;
            }
            return 1;
        }
    }

