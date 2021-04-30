package com.example.bookmanagement.datai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.ListUserActivity;
import com.example.bookmanagement.R;
import com.example.bookmanagement.dao.UserDAO;
import com.example.bookmanagement.model.User;

public class DetailUserActivity extends AppCompatActivity {
    TextView  edUserName;
    EditText  edUpdatePassword,edUpdatePhone,edUpdateFullName;
    public UserDAO userDAO;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        setTitle("Chỉnh Sửa Người Dùng");

        btnUpdate = findViewById(R.id.btnUpdateUser);
        edUserName = findViewById(R.id.edUserName);
        edUpdatePassword = findViewById(R.id.edUpdatePassword);
        edUpdatePhone = findViewById(R.id.edUpdatePhone);
        edUpdateFullName = findViewById(R.id.edUpdateFullName);

        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getBundleExtra("user");
            edUserName.setText(bundle.getString("username"));
            edUpdatePassword.setText(bundle.getString("password"));
            edUpdatePhone.setText(bundle.getString("phone"));
            edUpdateFullName.setText(bundle.getString("name"));
        }
    }

    public void updateUser(View view) {
        userDAO = new UserDAO(DetailUserActivity.this);
        User user = new User(edUserName.getText().toString(),
                edUpdatePassword.getText().toString(),
                edUpdatePhone.getText().toString(),
                edUpdateFullName.getText().toString());

            if(userDAO.updateUser(user) > 0 ){

                Toast.makeText(getApplicationContext(),"Sửa Thành Công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Sửa Thất Bại", Toast.LENGTH_SHORT).show();
            }



        }


    public void out(View view) {
        edUpdatePassword.setText("");
        edUpdatePhone.setText("");
        edUpdateFullName.setText("");
    }



    public void showUer(View view) {
        Intent intent = new Intent(DetailUserActivity.this, ListUserActivity.class);
        startActivity(intent);
    }
}
