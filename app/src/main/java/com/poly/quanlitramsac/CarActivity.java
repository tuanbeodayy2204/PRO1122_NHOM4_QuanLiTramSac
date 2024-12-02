package com.poly.quanlitramsac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.quanlitramsac.Adapter.StationAdapter;
import com.poly.quanlitramsac.Model.Station;

import java.util.List;

public class CarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StationAdapter stationAdapter;
    private List<Station> stationList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        databaseHelper = new DatabaseHelper(this);

        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load stations from database
        stationList = databaseHelper.getAllStations();
        stationAdapter = new StationAdapter(this, stationList);
        recyclerView.setAdapter(stationAdapter);

        // Nút thêm điểm sạc
        Button addStationButton = findViewById(R.id.addStationButton);
        addStationButton.setOnClickListener(v -> {
            Intent intent = new Intent(CarActivity.this, AddStationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh dữ liệu sau khi quay lại
        stationList.clear();
        stationList.addAll(databaseHelper.getAllStations());
        stationAdapter.notifyDataSetChanged();
    }
}
