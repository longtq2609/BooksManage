package com.example.bookmanagement.datai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.ListCategoryActivity;
import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;

public class DetailCategoryActivity extends AppCompatActivity {
    TextView idCategory;
    EditText updateNameCategory,updateLctCategory,updateDcpCategory;
    public CategoryDAO categoryDAO;
    Button btnUpdateCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        setTitle("Chỉnh Sửa Thể Loại");

        idCategory = findViewById(R.id.idCategoryUD);
        btnUpdateCategory = findViewById(R.id.btnUpdateCategory);
        updateNameCategory = findViewById(R.id.updateNameCategory);
        updateLctCategory = findViewById(R.id.updateLctCategory);
        updateDcpCategory = findViewById(R.id.updateDcpCategory);

        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getBundleExtra("category");
            idCategory.setText(bundle.getString("idCategory"));
            updateNameCategory.setText(bundle.getString("nameCategory"));
            updateLctCategory.setText(bundle.getString("moTa"));
            updateDcpCategory.setText(bundle.getString("viTri"));
        }
    }

    public void updateCategory(View view) {
        categoryDAO = new CategoryDAO(DetailCategoryActivity.this);
        Category category = new Category(idCategory.getText().toString(),
                updateNameCategory.getText().toString(),
                updateLctCategory.getText().toString(),
                updateDcpCategory.getText().toString());

        if(categoryDAO.updateCategory(category) > 0 ){

            Toast.makeText(getApplicationContext(),"Sửa Thành Công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Sửa Thất Bại", Toast.LENGTH_SHORT).show();
        }


    }

    public void out(View view) {
        updateNameCategory.setText("");
        updateLctCategory.setText("");
        updateDcpCategory.setText("");
    }

    public void showCategory(View view) {
        Intent intent = new Intent(DetailCategoryActivity.this, ListCategoryActivity.class);
        startActivity(intent);
    }
}