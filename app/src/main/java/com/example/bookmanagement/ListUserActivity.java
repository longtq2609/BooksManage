package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import com.example.bookmanagement.adapter.UserAdapter;
import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserActivity extends AppCompatActivity {
    public static List<User> list = new ArrayList<>();
    RecyclerView recyclerView;
    UserDAO userDAO;
    UserAdapter userAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        recyclerView = findViewById(R.id.recylerview);

        userDAO = new UserDAO(ListUserActivity.this);
        list =userDAO.getAllUser();
        userAdapter = new UserAdapter(ListUserActivity.this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);

    }


    public void out(View view) {
      intent = new Intent(ListUserActivity.this, MainInterfaceActivity.class);
      startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_them:
                intent = new Intent(this, UserActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_doimatkhau:
                intent = new Intent(this, ChangePasswordActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_dangxuat:
                SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.commit();
                intent = new Intent(ListUserActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}