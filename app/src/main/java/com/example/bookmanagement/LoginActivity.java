package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookmanagement.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {
  Button btnLogin, btnCancel;
  EditText edUser, edPass;
  CheckBox chkRememberPass;
  String strUser, strPass;
  UserDAO userDAO;
   Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng Nhập");
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);
        userDAO = new UserDAO(LoginActivity.this);


    }


    public void login(View view) {
        strUser = edUser.getText().toString();
        strPass = edPass.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên Đăng Nhập Hoặc Mật Khẩu Không Không Được Bỏ Trống", Toast.LENGTH_SHORT).show();
        }else {
            if(userDAO.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                intent = new Intent(LoginActivity.this,MainInterfaceActivity.class);
                startActivity(intent);
            }
            if(strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")){
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                intent = new Intent(LoginActivity.this,MainInterfaceActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"Tên Đăng Nhập Hoặc Mật Khẩu Không Đúng ", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        strUser = pref.getString("USERNAME","");
        strPass = pref.getString("PASSWORD","");
        if( !status ){
            edit.clear();
        }else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }

}