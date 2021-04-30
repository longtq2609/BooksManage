package com.example.bookmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookmanagement.adapter.BillAdapter;
import com.example.bookmanagement.dao.BillDAO;
import com.example.bookmanagement.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class ListBillActivity extends AppCompatActivity {
    Intent intent;
    public List<Bill> bills = new ArrayList<>();
    ListView listView;
    BillAdapter billAdapter = null;
    BillDAO billDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa Đơn");
        setContentView(R.layout.activity_list_bill);
        listView = (ListView) findViewById(R.id.lvBill);
        billDAO = new BillDAO(ListBillActivity.this);
        try {
             bills = billDAO.getAllBill();
        }catch (Exception e){
            Log.d("Erro",e.toString());
        }

        billAdapter = new BillAdapter(this, bills);
        listView.setAdapter(billAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill bill = (Bill) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBillActivity.this, ListDetalBillActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDBILL", bill.getIdBill());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listView.setTextFilterEnabled(true);

        EditText edSeach =  (EditText) findViewById(R.id.edSeach);

        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
            if(count < before){
                billAdapter.resetData();
            }
                billAdapter.getFilter().filter(s.toString());

        }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void out(View view) {
        intent = new Intent(ListBillActivity.this, MainInterfaceActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.bill, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.addBill){
            intent = new Intent(this, BillActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}