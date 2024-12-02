package com.poly.quanlitramsac;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class QuenmatkhauActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendCodeButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmatkhau);

        // Ánh xạ
        emailEditText = findViewById(R.id.emailEditText);
        sendCodeButton = findViewById(R.id.sendCodeButton);

        // Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Xử lý gửi email đặt lại mật khẩu
        sendCodeButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(QuenmatkhauActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gửi email đặt lại mật khẩu
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuenmatkhauActivity.this, "Email đặt lại mật khẩu đã được gửi", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(QuenmatkhauActivity.this, "Thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
