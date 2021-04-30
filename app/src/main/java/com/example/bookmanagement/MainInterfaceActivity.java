package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainInterfaceActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
    }

    public void viewUser(View view) {
        intent = new Intent(MainInterfaceActivity.this, ListUserActivity.class);
        startActivity(intent);
    }

    public void viewCategory(View view) {
        intent = new Intent(MainInterfaceActivity.this, ListCategoryActivity.class);
        startActivity(intent);
    }

    public void viewBook(View view) {
        intent = new Intent(MainInterfaceActivity.this, ListBookActivity.class);
        startActivity(intent);
    }

    public void viewBill(View view) {
        intent = new Intent(MainInterfaceActivity.this, ListBillActivity.class);
        startActivity(intent);
    }

    public void viewChart(View view) {
        intent = new Intent(MainInterfaceActivity.this, BestsellingBooksActivity.class);
        startActivity(intent);
    }

    public void viewStatistical(View view) {
        intent = new Intent(MainInterfaceActivity.this, StatisticalActivity.class);
        startActivity(intent);
    }
}