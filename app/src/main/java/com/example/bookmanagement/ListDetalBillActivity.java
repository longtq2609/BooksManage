package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bookmanagement.adapter.CartAdapter;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.DetailBill;

import java.util.ArrayList;
import java.util.List;

public class ListDetalBillActivity extends AppCompatActivity {
    public List<DetailBill> detailBills = new ArrayList<>();
    ListView listView;
    CartAdapter cartAdapter = null;
    DetailBillDAO detailBillDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa Đơn Chi Tiết");
        setContentView(R.layout.activity_list_detal_bill);
        listView = (ListView) findViewById(R.id.lvDetalBill);
        detailBillDAO = new DetailBillDAO(ListDetalBillActivity.this);
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        if( bundle != null){
            detailBills = detailBillDAO.getAllDetailBillByID(bundle.getString("IDBILL"));
        }
        cartAdapter = new CartAdapter(this, detailBills);
        listView.setAdapter(cartAdapter);
    }

    public void out(View view) {
        finish();
    }
}