package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bookmanagement.dao.DetailBillDAO;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StatisticalActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    DetailBillDAO detailBillDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        setTitle("Thống Kê");
        tvNgay = findViewById(R.id.tvNgay);
        tvThang = findViewById(R.id.tvThang);
        tvNam = findViewById(R.id.tvNam);
        detailBillDAO = new DetailBillDAO(this);
        NumberFormat numberFormat = new DecimalFormat("#,###");
        tvNgay.setText("Hôm nay    : "+numberFormat.format(detailBillDAO.getDoanhThuNgay())+" VND");
        tvThang.setText("Tháng này : "+numberFormat.format(detailBillDAO.getDoanhThuThang())+" VND");
        tvNam.setText("Năm này    : "+numberFormat.format(detailBillDAO.getDoanhThuNam())+" VND");
    }

    public void out(View view) {
        Intent intent = new Intent(StatisticalActivity.this, MainInterfaceActivity.class);
        startActivity(intent);
    }
}