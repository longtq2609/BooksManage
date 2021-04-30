package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.Category;
import com.example.bookmanagement.model.User;

public class CategoryActivity extends AppCompatActivity {
    Intent intent;
    Button btnAddCategory;
    CategoryDAO categoryDAO;
    EditText idCategory, nameCategory, lctCategory, dcpCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Thêm Thể Loại");
        btnAddCategory = findViewById(R.id.btnAddCategory);
        idCategory = findViewById(R.id.idCategory);
        nameCategory = findViewById(R.id.nameCategory);
        lctCategory = findViewById(R.id.lctCategory);
        dcpCategory = findViewById(R.id.dcpCategory);
    }

    public void addCategory(View view) {
        categoryDAO = new CategoryDAO(CategoryActivity.this);
        Category category = new Category(idCategory.getText().toString(),
                nameCategory.getText().toString(),
                lctCategory.getText().toString(),
                dcpCategory.getText().toString());
        try {
            if(categoryDAO.insertCategory(category)>0){
                Toast.makeText(getApplicationContext(),"Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Thêm Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    public void out(View view) {
        idCategory.setText("");
        nameCategory.setText("");
        lctCategory.setText("");
        dcpCategory.setText("");
    }


    public void showCategory(View view) {
        intent = new Intent(CategoryActivity.this, ListCategoryActivity.class);
        startActivity(intent);
    }
}