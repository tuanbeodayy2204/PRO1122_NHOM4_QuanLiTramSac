package com.poly.quanlitramsac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.poly.quanlitramsac.Model.Station;

public class AddStationActivity extends AppCompatActivity {

    private EditText addressEditText, quantityEditText, priceEditText, statusEditText, typeEditText, latitudeEditText, longitudeEditText;
    private Button saveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);

        databaseHelper = new DatabaseHelper(this);

        // Ánh xạ các trường nhập liệu
        addressEditText = findViewById(R.id.addressEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        priceEditText = findViewById(R.id.priceEditText);
        statusEditText = findViewById(R.id.statusEditText);
        typeEditText = findViewById(R.id.typeEditText);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        saveButton = findViewById(R.id.saveStationButton);

        // Xử lý khi nhấn nút Lưu
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewStation();
            }
        });
    }

    private void addNewStation() {
        // Lấy dữ liệu từ các trường nhập liệu
        String address = addressEditText.getText().toString();
        String quantity = quantityEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String status = statusEditText.getText().toString();
        String type = typeEditText.getText().toString();
        String latitude = latitudeEditText.getText().toString();
        String longitude = longitudeEditText.getText().toString();

        // Kiểm tra dữ liệu
        if (address.isEmpty() || quantity.isEmpty() || price.isEmpty() || status.isEmpty() || type.isEmpty() || latitude.isEmpty() || longitude.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Station
        Station newStation = new Station(
                address,
                Integer.parseInt(quantity),
                price,
                status,
                type,
                Double.parseDouble(latitude),
                Double.parseDouble(longitude)
        );

        // Thêm vào cơ sở dữ liệu
        databaseHelper.addStation(newStation);
        Toast.makeText(this, "Thêm trạm sạc thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
