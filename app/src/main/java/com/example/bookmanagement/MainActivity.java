package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.SHOW_PROGRESS);
        textView = findViewById(R.id.tv);


        progressBar.setMax(100);
        progressBar.setScaleY(3f);


        progressAnimation();
    }

    public void progressAnimation(){
        ProgressBarAnimation barAnimation = new ProgressBarAnimation(this, progressBar, textView, 0f, 100f);
        barAnimation.setDuration(8000);
        progressBar.setAnimation(barAnimation);
    }
}