package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edPass, edRePass;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đổi Mật Khẩu");
        setContentView(R.layout.activity_change_password);
        edPass = findViewById(R.id.edPass);
        edRePass = findViewById(R.id.edRePass);
    }
    public int validateFrom(){
        int check =1;
        if(edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Thông Tin", Toast.LENGTH_LONG).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(),"Mật Khẩu Không trung Khớp", Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }
    public void out(View view) {
        onBackPressed();
    }

    public void changePassword(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUsername = sharedPreferences.getString("USERNAME","");
        userDAO = new UserDAO(ChangePasswordActivity.this);
        User user = new User(strUsername, edPass.getText().toString(),"","");
        try {
        if(validateFrom() > 0){
            if(userDAO.changePassword(user)>0){
                Toast.makeText(getApplicationContext(),"Thay Đổi Mật Khẩu Thành Công", Toast.LENGTH_LONG).show();

            }else {
                Toast.makeText(getApplicationContext(),"Thay Đổi Mật Khẩu Thất Bại", Toast.LENGTH_LONG).show();

            }
        }


    }catch (Exception e){
        Log.e("Error",e.getMessage());
    }
}
}
