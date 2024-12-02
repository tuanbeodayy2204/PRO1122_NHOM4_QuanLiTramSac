package com.poly.quanlitramsac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);
        // Xử lý sự kiện click trong thanh điều hướng
        ImageView imgHome = findViewById(R.id.img_home);
        ImageView imgTime = findViewById(R.id.img_time);
        ImageView imgNoti = findViewById(R.id.img_noti);
        ImageView imgAccount = findViewById(R.id.img_account);
        ImageView imgHelp = findViewById(R.id.img_help);
        imgHome.setOnClickListener(v -> {
            Intent intent = new Intent(SupportActivity.this,MainActivity.class);
            startActivity(intent);
        });
        imgTime.setOnClickListener(v -> {
            Intent intent = new Intent(SupportActivity.this,History.class);
            startActivity(intent);
        });
        imgNoti.setOnClickListener(v -> {
            Intent intent = new Intent(SupportActivity.this,NotiActivity.class);
            startActivity(intent);
        });
    }
}