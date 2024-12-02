package com.poly.quanlitramsac;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DangkiActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki);

        // Ánh xạ
        emailEditText = findViewById(R.id.registerUsernameEditText);
        passwordEditText = findViewById(R.id.registerPasswordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        // Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Xử lý nút Đăng ký
        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(DangkiActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(DangkiActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đăng ký với Firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(DangkiActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            finish(); // Quay lại màn hình trước
                        } else {
                            Toast.makeText(DangkiActivity.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
