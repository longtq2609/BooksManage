package com.example.bookmanagement.datai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.ListBookActivity;
import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.BookDAO;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Category;

import java.util.ArrayList;
import java.util.List;

public class DetailBookActivity extends AppCompatActivity {
    TextView idBookUD;
    EditText nameBookUD, pscBookUD, authorBookUD, priceBookUD, amountBookUD;
    Spinner spnIdBookUD;
    public BookDAO bookDAO;
    String maTheLoai="";
    CategoryDAO categoryDAO;
    List<Category> categoryList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        spnIdBookUD = findViewById(R.id.spnIdBookUD);
        getCategory();
        setTitle("Chỉnh Sửa Sách");

        idBookUD = findViewById(R.id.idBookUD);
        nameBookUD = findViewById(R.id.nameBookUD);
        pscBookUD = findViewById(R.id.pscBookUD);
        authorBookUD = findViewById(R.id.authorBookUD);
        priceBookUD = findViewById(R.id.priceBookUD);
        amountBookUD = findViewById(R.id.amountBookUD);

        spnIdBookUD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = categoryList.get(spnIdBookUD.getSelectedItemPosition()).getIdCategory();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getBundleExtra("book");
            idBookUD.setText(bundle.getString("idBook"));
            nameBookUD.setText(bundle.getString("tieuDe"));
            String maTheLoai = bundle.getString("idCategory");
            pscBookUD.setText(bundle.getString("nxb"));
            authorBookUD.setText(bundle.getString("tacGia"));
            priceBookUD.setText(bundle.getString("giaBia"));
            amountBookUD.setText(bundle.getString("soLuong"));
            spnIdBookUD.setSelection(check(maTheLoai));
        }

    }
        public void showSpinner(View view){
        bookDAO = new BookDAO(DetailBookActivity.this);
        bookDAO.getAllBook();
    }
    public void updateBook(View view) {
        bookDAO = new BookDAO(DetailBookActivity.this);
        Book book = new Book(idBookUD.getText().toString(), maTheLoai,nameBookUD.getText().toString(),pscBookUD.getText().toString(),authorBookUD.getText().toString(), Double.parseDouble(priceBookUD.getText().toString()),Integer.parseInt(amountBookUD.getText().toString()));
        try {
            if(bookDAO.updateBook(book)>0){
                Toast.makeText(getApplicationContext(),"Sửa Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Sửa Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }
    public void getCategory(){
        categoryDAO = new CategoryDAO(DetailBookActivity.this);
        categoryList = categoryDAO.getAllCategory();
        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdBookUD.setAdapter(arrayAdapter);
    }
    public void out(View view) {
        idBookUD.setText("");
        nameBookUD.setText("");
        pscBookUD.setText("");
        authorBookUD.setText("");
        priceBookUD.setText("");
        amountBookUD.setText("");
    }

    public void showBook(View view) {
       Intent intent = new Intent(DetailBookActivity.this, ListBookActivity.class);
        startActivity(intent);
    }
    public int check(String TL){
        for(int i =0;i<categoryList.size();i++){
            if (TL.equals(categoryList.get(i).getIdCategory())) {
                return i;
            }
        }
        return 0;
    }
}