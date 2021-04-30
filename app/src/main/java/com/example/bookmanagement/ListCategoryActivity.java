package com.example.bookmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.bookmanagement.adapter.CategoryAdapter;
import com.example.bookmanagement.dao.CategoryDAO;
import com.example.bookmanagement.model.Category;

import java.util.ArrayList;
import java.util.List;

public class ListCategoryActivity extends AppCompatActivity {
    public static List<Category> categories = new ArrayList<>();
    Intent intent;
    CategoryDAO categoryDAO;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        recyclerView = findViewById(R.id.recylerview);

        categoryDAO = new CategoryDAO(ListCategoryActivity.this);
        categories = categoryDAO.getAllCategory();
        categoryAdapter = new CategoryAdapter(ListCategoryActivity.this, categories);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
    }



    public void out(View view) {
        intent = new Intent(ListCategoryActivity.this, MainInterfaceActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.addCategory){
            intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}