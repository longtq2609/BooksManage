package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookmanagement.database.DatabaseHelper;
import com.example.bookmanagement.model.Category;


import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "Category";
    public static final String TAG = " TAG_Category";
    public static final String SQL_Category="CREATE TABLE Category (" +
            " idCategory text primary key," +
            " nameCategory text," +
            " moTa text," +
            " viTri text" +
            " );";
    public CategoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public CategoryDAO() {

    }
    public int insertCategory(Category category){
        ContentValues values = new ContentValues();
        values.put("idCategory", category.getIdCategory());
        values.put("nameCategory", category.getNameCategory());
        values.put("moTa", category.getMoTa());
        values.put("viTri", category.getViTri());
        if(checkKey(category.getIdCategory())) {
            int result = db.update(TABLE_NAME, values, "idCategory=?", new String[]{category.getIdCategory()});
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
    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Category category = new Category();
            category.setIdCategory(cursor.getString(0));
            category.setNameCategory(cursor.getString(1));
            category.setMoTa(cursor.getString(2));
            category.setViTri(cursor.getString(3));
            list.add(category);
            Log.d("===",category.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public int deleteCategory(String idCategory){
        int kq =  db.delete(TABLE_NAME, "idCategory = ?", new String[]{idCategory});
        if(kq == 0){
            return -1;
        }return 1;
    }

    public int updateCategory(Category category) {
        ContentValues values = new ContentValues();
        values.put("nameCategory", category.getNameCategory());
        values.put("moTa", category.getMoTa());
        values.put("viTri", category.getViTri());
        try {
            if( db.update(TABLE_NAME, values, "idCategory=?", new String[]{category.getIdCategory()})  < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return  1;
    }

    public boolean checkKey(String primaryKey){
        String [] columns = {"idCategory"};
        String selection = "idCategory=?";
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


}
