package com.poly.quanlitramsac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.quanlitramsac.Adapter.ChargingHistoryAdapter;
import com.poly.quanlitramsac.Model.ChargingHistoryWithStationDetails;

import java.util.List;

public class History extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView historyRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // Xử lý sự kiện click trong thanh điều hướng
        ImageView imgHome = findViewById(R.id.img_home);
        ImageView imgTime = findViewById(R.id.img_time);
        ImageView imgNoti = findViewById(R.id.img_noti);
        ImageView imgAccount = findViewById(R.id.img_account);
        ImageView imgHelp = findViewById(R.id.img_help);
        databaseHelper = new DatabaseHelper(this);
        imgHome.setOnClickListener(v -> {
            Intent intent = new Intent(History.this,MainActivity.class);
            startActivity(intent);
        });
        imgTime.setOnClickListener(v -> {
            Intent intent = new Intent(History.this,History.class);
            startActivity(intent);
        });
        imgNoti.setOnClickListener(v -> {
            Intent intent = new Intent(History.this,NotiActivity.class);
            startActivity(intent);
        });
        imgHelp.setOnClickListener(v -> {
            Intent intent = new Intent(History.this,SupportActivity.class);
            startActivity(intent);
        });


        List<ChargingHistoryWithStationDetails> chargingHistoryList = databaseHelper.getAllChargingHistoryWithStationDetails();
        historyRecyclerView = findViewById(R.id.rv_history);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        historyRecyclerView.setLayoutManager(layoutManager);
        ChargingHistoryAdapter adapter = new ChargingHistoryAdapter(chargingHistoryList, history -> {
            Toast.makeText(this, "Click on " + history.getStation().getAddress(), Toast.LENGTH_SHORT).show();
        });
        historyRecyclerView.setAdapter(adapter);
    }
}
