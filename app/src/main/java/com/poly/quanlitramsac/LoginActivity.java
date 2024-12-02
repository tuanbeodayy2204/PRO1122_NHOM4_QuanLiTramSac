package com.poly.quanlitramsac;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView, registerTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Ánh xạ
        emailEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        registerTextView = findViewById(R.id.registerTextView);
        ImageView checkpass = findViewById(R.id.checkpass);
        final boolean[] isPasswordVisible = {false};

        // Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        // xử lis đóng mở hiển thị mật khẩu
        checkpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    // Ẩn mật khẩu
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkpass.setImageResource(R.drawable.mat);  // Icon "mắt đóng"
                } else {
                    // Hiển thị mật khẩu
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkpass.setImageResource(R.drawable.matmo);  // Icon "mắt mở"
                }

                // Lưu trạng thái
                isPasswordVisible[0] = !isPasswordVisible[0];

                // Đảm bảo con trỏ (cursor) vẫn ở đúng vị trí sau khi thay đổi inputType
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        // Xử lý nút Đăng nhập
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đăng nhập với Firebase
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            // Điều hướng đến màn hình chính hoặc Dashboard
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Xử lý quên mật khẩu
        forgotPasswordTextView.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, QuenmatkhauActivity.class)));

        // Xử lý chuyển sang màn hình Đăng ký
        registerTextView.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, DangkiActivity.class)));
    }
}
