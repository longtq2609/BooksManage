package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookmanagement.database.DatabaseHelper;
import com.example.bookmanagement.model.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String  TABLE_NAME = "Bill";
    public static final String TAG = " TAG_Bill";
    public static final String SQL_Bill= "CREATE TABLE Bill (idBill text primary key, dayBuy date);";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public BillDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertBill(Bill bill){
        ContentValues values = new ContentValues();
        values.put("idBill", bill.getIdBill());
        values.put("dayBuy", simpleDateFormat.format(bill.getDayBuy()));
        try {
            if( db.insert(TABLE_NAME, null, values) <0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return  1;
    }
    public List<Bill> getAllBill() throws ParseException {
        List<Bill> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Bill bill = new Bill();
            bill.setIdBill(cursor.getString(0));
            bill.setDayBuy(simpleDateFormat.parse(cursor.getString(1)));

            list.add(bill);
            cursor.moveToNext();
            Log.d("GetAll", bill.toString());
        }
        cursor.close();
        return list;
    }
    public int updateBill(Bill bill){
        ContentValues values = new ContentValues();
        values.put("idBill", bill.getIdBill());
        values.put("dayBuy", simpleDateFormat.format(bill.getDayBuy()));
        int kp = db.update(TABLE_NAME, values,"idBill=?", new String[]{bill.getIdBill()});
        if(kp == 0){
            return -1;
        }return 1;
    }
    public int deleteBill(String idBill){
        int kp = db.delete(TABLE_NAME,"idBill=?",new String[]{idBill});
        if(kp == 0){
            return -1;

        }return 1;
    }
}
