package com.poly.quanlitramsac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.quanlitramsac.Adapter.StationAdapter;
import com.poly.quanlitramsac.Model.Station;

import java.util.List;

public class ManageStationsActivity extends AppCompatActivity {

    private RecyclerView stationsRecyclerView;
    private StationAdapter stationAdapter;
    private DatabaseHelper databaseHelper;
    private List<Station> stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stations);

        // Initialize database and RecyclerView
        databaseHelper = new DatabaseHelper(this);
        stationsRecyclerView = findViewById(R.id.stationsRecyclerView);
        stationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch station list from database
        stationList = databaseHelper.getAllStations();
        stationAdapter = new StationAdapter(this, stationList);
        stationsRecyclerView.setAdapter(stationAdapter);
    }


}
