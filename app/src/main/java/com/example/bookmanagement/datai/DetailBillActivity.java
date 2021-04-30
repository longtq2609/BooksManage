package com.example.bookmanagement.datai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.ListBillActivity;
import com.example.bookmanagement.R;
import com.example.bookmanagement.adapter.CartAdapter;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.DetailBillDAO;
import com.example.bookmanagement.model.Bill;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.DetailBill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailBillActivity extends AppCompatActivity {
    EditText edIDBook, edIDBill, edSoLuong;
    TextView tvThanhTien;
    DetailBillDAO detailBillDAO;
    BookDAO bookDAO;
    public List<DetailBill> detailBills = new ArrayList<>();
    ListView lv;
    CartAdapter cartAdapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chi Tiết Hóa Đơn");
        setContentView(R.layout.activity_detail_bill);
        edIDBook = (EditText) findViewById(R.id.edIDBook);
        edIDBill = (EditText) findViewById(R.id.edIDBill);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        lv = (ListView) findViewById(R.id.lv);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);

        cartAdapter = new CartAdapter(this, detailBills);
        lv.setAdapter(cartAdapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if( bundle != null){
            edIDBill.setText(bundle.getString("IDBILL"));
        }
    }

    public void addDetailBill(View view) {
        detailBillDAO = new DetailBillDAO(DetailBillActivity.this);
        bookDAO = new BookDAO(DetailBillActivity.this);
        try{
            if(validation() < 0){
                Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Thông Tin", Toast.LENGTH_SHORT).show();

            }else {
                Book book = bookDAO.getBookByID(edIDBook.getText().toString());
                if(book != null){
                    int pos = checkIDBook(detailBills,edIDBook.getText().toString());
                    Bill bill = new Bill(edIDBill.getText().toString(), new Date());
                    DetailBill detailBill = new DetailBill(1, bill,book,Integer.parseInt(edSoLuong.getText().toString()));
                    if(pos>=0){
                        int soLuong = detailBills.get(pos).getSoLuongMua();
                        detailBill.setSoLuongMua(soLuong+Integer.parseInt(edSoLuong.getText().toString()));
                        detailBills.set(pos, detailBill);
                    }else {
                        detailBills.add(detailBill);
                    }
                    cartAdapter.changeDataset(detailBills);
                }else {
                    Toast.makeText(getApplicationContext(),"Mã Sách Không Tồn Tại",Toast.LENGTH_SHORT).show();

                }
            }
        } catch (Exception e) {
            Log.e("Erro",e.toString());
        }
    }

public int validation(){
    if(edIDBook.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty()){
        return -1;
    }return 1;
}
public int checkIDBook(List<DetailBill>detailBills, String idBook){
        int pos = -1;
        for(int i =0 ;i< detailBills.size();i++){
            DetailBill detailBill = detailBills.get(i);
            if(detailBill.getBook().getIdBook().equalsIgnoreCase(idBook)){
                pos = 1;
                break;
            }
        }
        return pos;
}

    public void thanhToan(View view) {
        detailBillDAO = new DetailBillDAO(DetailBillActivity.this);
        thanhTien = 0;
        try {
            for(DetailBill detailBill : detailBills){
                detailBillDAO.insertDetailBill(detailBill);
                thanhTien = thanhTien +detailBill.getSoLuongMua() * detailBill.getBook().getGiaBia() ;
            }
            tvThanhTien.setText("Tổng Tiền: "+thanhTien);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(DetailBillActivity.this, ListBillActivity.class);
        startActivity(intent);
    }
}