package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    Intent intent;
    Button btnAddUser;
    public UserDAO userDAO;
    EditText edUserName, edPassword, edRePassword, edPhone, edFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Thêm Người Dùng");
        btnAddUser = findViewById(R.id.btnAddUser);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        edRePassword = findViewById(R.id.edRePassword);
        edPhone = findViewById(R.id.edPhone);
        edFullName = findViewById(R.id.edFullName);
    }

    public void addUser(View view) {
        userDAO = new UserDAO(UserActivity.this);
        User user = new User(edUserName.getText().toString(),
                edPassword.getText().toString(),
                edPhone.getText().toString(),
                edFullName.getText().toString());
        try {
            if(userDAO.insertUser(user)>0){
                Toast.makeText(getApplicationContext(),"Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Thêm Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
    }

    public void out(View view) {
        edUserName.setText("");
        edPassword.setText("");
        edRePassword.setText("");
        edPhone.setText("");
        edFullName.setText("");
    }

    public void showUsers(View view) {
       intent = new Intent(UserActivity.this, ListUserActivity.class);
       startActivity(intent);
    }
}