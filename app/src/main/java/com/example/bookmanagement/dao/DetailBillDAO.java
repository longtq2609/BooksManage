package com.example.bookmanagement.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookmanagement.database.DatabaseHelper;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.DetailBill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailBillDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME = "DetailBill";
    public static final String TAG = " TAG_DetailBill";
    public static final String SQL_DetailBill="CREATE TABLE DetailBill (" +
            " idHDCT integer PRIMARY KEY AUTOINCREMENT ," +
            " idBill text not null," +
            " idBook text not null," +
            " soLuongMua integer" +
            " );";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public DetailBillDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertDetailBill(DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("idBill", detailBill.getBill().getIdBill());
        values.put("idBook",detailBill.getBook().getIdBook());
        values.put("soLuongMua",detailBill.getSoLuongMua());
        try {
            if( db.insert(TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return  1;
    }
    public List<DetailBill> getAllDetailBill(){
        List<DetailBill> list = new ArrayList<>();
        String sSQL = "SELECT idHDCT, Bill.idBill, Bill.dayBuy, "+
                "Book.idBook, Book.idCategory, Book.tieuDe, Book.tacGia, Book.nxb, Book.giaBia,"+
                "Book.soLuong, DetailBill.soLuongMua FROM DetailBill INNER JOIN Bill "+
                "on DetailBill.idBill = Bill.idBill INNER JOIN Book on Book.idBook = DetailBill.idBook";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false){
                DetailBill detailBill = new DetailBill();
                detailBill.setIdHDCT(cursor.getInt(0));
                detailBill.setBill(new Bill(cursor.getString(1),simpleDateFormat.parse(cursor.getString(2))));
                detailBill.setBook(new Book(cursor.getString(3), cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getDouble(8),cursor.getInt(9)));
                detailBill.setSoLuongMua(cursor.getInt(10));
                list.add(detailBill);
                Log.d("====",detailBill.toString());
                cursor.moveToNext();
            }
            cursor.close();
        } catch (ParseException e) {
            Log.d(TAG, e.toString());
        }
        return list;
    }
    public List<DetailBill> getAllDetailBillByID(String idBill){
        List<DetailBill> list = new ArrayList<>();
        String sSQL = "SELECT idHDCT, Bill.idBill, Bill.dayBuy, "+
                "Book.idBook, Book.idCategory, Book.tieuDe, Book.tacGia, Book.nxb, Book.giaBia,"+
                "Book.soLuong, DetailBill.soLuongMua FROM DetailBill INNER JOIN Bill "+
                "on DetailBill.idBill = Bill.idBill INNER JOIN Book on Book.idBook = DetailBill.idBook where DetailBill.idBill='"+idBill+"'";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false){
                DetailBill detailBill = new DetailBill();
                detailBill.setIdHDCT(cursor.getInt(0));
                detailBill.setBill(new Bill(cursor.getString(1),simpleDateFormat.parse(cursor.getString(2))));
                detailBill.setBook(new Book(cursor.getString(3), cursor.getString(4),cursor.getString(5),
                        cursor.getString(6),cursor.getString(7),cursor.getDouble(8),cursor.getInt(9)));
                detailBill.setSoLuongMua(cursor.getInt(10));
                list.add(detailBill);
                Log.d("====",detailBill.toString());
                cursor.moveToNext();
            }
            cursor.close();
        } catch (ParseException e) {
            Log.d(TAG, e.toString());
        }
        return list;
    }
    public int updateDetailBill(DetailBill detailBill){
        ContentValues values = new ContentValues();
        values.put("idHDCT", detailBill.getIdHDCT());
        values.put("idBill", detailBill.getBill().getIdBill());
        values.put("idBook",detailBill.getBook().getIdBook());
        values.put("soLuongMua",detailBill.getSoLuongMua());
        int kp = db.update(TABLE_NAME, values,"idHDCT=?",new String[]{String.valueOf(detailBill.getIdHDCT())});
        if(kp == 0){
            return -1;
        }return 1;
    }
    public int deleteDetailBill(String idHDCT){
        int kp = db.delete(TABLE_NAME,"idHDCT=?",new String[]{idHDCT});
        if(kp == 0)return -1;
        return 1;
    }
    public boolean checkHD(String idBill){
        String [] columns = {"idBill"};
        String selection = "idBill";
        String [] selectionArgs ={idBill};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            int i = cursor.getCount();
            cursor.close();
            if(i <= 0){
                return false;

            }return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public double getDoanhThuNgay(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Book.giaBia*DetailBill.soLuongMua) as 'tongtien'"+
                "FROM Bill INNER JOIN DetailBill on Bill.idBill = DetailBill.idBill "+
                "INNER JOIN Book on DetailBill.idBook = Book.idBook where Bill.dayBuy = date('now')"+
                "GROUP BY DetailBill.idBook)tmp";
        Cursor cursor
                = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();

        }cursor.close();
        return doanhThu;
    }
    public double getDoanhThuThang(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Book.giaBia*DetailBill.soLuongMua) as 'tongtien'"+
                "FROM Bill INNER JOIN DetailBill on Bill.idBill = DetailBill.idBill "+
                "INNER JOIN Book on DetailBill.idBook = Book.idBook where strftime('%m',Bill.dayBuy)="+
                "strftime('%m','now')GROUP BY DetailBill.idBook)tmp ";
        Cursor cursor
                = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();

        }cursor.close();
        return doanhThu;
    }
    public double getDoanhThuNam(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Book.giaBia*DetailBill.soLuongMua) as 'tongtien'"+
                "FROM Bill INNER JOIN DetailBill on Bill.idBill = DetailBill.idBill "+
                "INNER JOIN Book on DetailBill.idBook = Book.idBook where strftime('%Y',Bill.dayBuy)"+
                " = strftime('%Y','now')GROUP BY DetailBill.idBook)tmp ";
        Cursor cursor
                = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();

        }cursor.close();
        return doanhThu;
    }
}
