package com.poly.quanlitramsac;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class PaymentActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    databaseHelper = new DatabaseHelper(this);
        // Ánh xạ view
        TextView chargeDetails = findViewById(R.id.chargeDetails);
        Button paymentButton = findViewById(R.id.paymentButton);

        // Lấy dữ liệu từ Intent
        String address = getIntent().getStringExtra("ADDRESS");
        int stationId = getIntent().getIntExtra("STATION_ID", 0);
        String type = getIntent().getStringExtra("TYPE");
        int pricePerHour = getIntent().getIntExtra("PRICE_PER_HOUR", 0);
        int hours = getIntent().getIntExtra("HOURS", 0); // Lấy số giờ từ Intent

        // Tính tổng tiền
        int total = pricePerHour * hours;

        // Hiển thị thông tin thanh toán
        chargeDetails.setText("Địa chỉ: " + address +
                "\nLoại sạc: " + type +
                "\nGiá: " + pricePerHour + " VND/1h" +
                "\nSố giờ: " + hours + " tiếng" +
                "\nTổng tiền: " + total + " VND");

        // Xử lý nút thanh toán
        paymentButton.setOnClickListener(v -> {
        databaseHelper.addChargingHistory(
                stationId,
                hours,
                total
        );
            Toast.makeText(PaymentActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng màn hình thanh toán
        });
    }
}
