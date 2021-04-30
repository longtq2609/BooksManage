package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanagement.adapter.BookAdapter;
import com.example.bookmanagement.adapter.TopAdapter;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BestsellingBooksActivity extends AppCompatActivity {
    public static List<Book> bookList = new ArrayList<>();
    RecyclerView listView;
    TopAdapter bookAdapter = null;
    BookDAO bookDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestselling_books);
        setTitle("Top Sách Bán Chạy");
        listView = findViewById(R.id.lvBookTOP);
        edThang = findViewById(R.id.edThang);


    }

    public void VIEW_SACH_10(View view) {
        if(Integer.parseInt(edThang.getText().toString())>13 || Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không Đúng Định Dạng", Toast.LENGTH_SHORT).show();
        }else {

            bookDAO = new BookDAO(BestsellingBooksActivity.this);
            bookList = bookDAO.getSachTop10(edThang.getText().toString());
            bookAdapter = new TopAdapter(this, bookList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            listView.setLayoutManager(linearLayoutManager);
            listView.setAdapter( bookAdapter);

        }
    }

    public void out(View view) {
        Intent intent
                 = new Intent(BestsellingBooksActivity.this, MainInterfaceActivity.class);
        startActivity(intent);
    }
}