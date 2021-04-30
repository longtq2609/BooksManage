package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookmanagement.adapter.BookAdapter;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity  {
    Intent intent;
    Spinner spinner;
    EditText idBook, nameBook, pscBook, authorBook,priceBook, amountBook;
    String maTheLoai = "";
    BookDAO bookDAO;
    List<Category> categoryList= new ArrayList<>();
    CategoryDAO categoryDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        spinner = findViewById(R.id.spnId);
        getCategory();
        setTitle("Thêm Sách");
        idBook = findViewById(R.id.idBook);
        nameBook = findViewById(R.id.nameBook);
        pscBook = findViewById(R.id.pscBook);
        authorBook = findViewById(R.id.authorBook);
        priceBook = findViewById(R.id.priceBook);
        amountBook = findViewById(R.id.amountBook);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = categoryList.get(spinner.getSelectedItemPosition()).getIdCategory();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public void showSpinner(View view){
        bookDAO = new BookDAO(BookActivity.this);
        bookDAO.getAllBook();
    }
    public void getCategory(){
        categoryDAO = new CategoryDAO(BookActivity.this);
        categoryList = categoryDAO.getAllCategory();
        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    public void addBook(View view) {
        bookDAO = new BookDAO(BookActivity.this);
        Book book = new Book(idBook.getText().toString(), maTheLoai,nameBook.getText().toString(),pscBook.getText().toString(),authorBook.getText().toString(), Double.parseDouble(priceBook.getText().toString()),Integer.parseInt(amountBook.getText().toString()));
        try {
            if(bookDAO.insertBook(book)>0){
                Toast.makeText(getApplicationContext(),"Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Thêm Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    public void out(View view) {

        idBook.setText("");
        nameBook.setText("");
        pscBook.setText("");
        authorBook.setText("");
        priceBook.setText("");
        amountBook.setText("");
    }

    public void showBook(View view) {
        intent = new Intent(BookActivity.this, ListBookActivity.class);
        startActivity(intent);
    }
//    public int check(String TL){
//        for(int i =0;i<categoryList.size();i++){
//            if (TL.equals(categoryList.get(i).getIdCategory())) {
//                return i;
//            }
//        }
//        return 0;
//    }

}