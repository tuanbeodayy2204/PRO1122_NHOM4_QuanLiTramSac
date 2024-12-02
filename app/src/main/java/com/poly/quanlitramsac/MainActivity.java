package com.poly.quanlitramsac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.poly.quanlitramsac.Adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPagerSlider;
    private CircleIndicator3 circleIndicator;
    private SliderAdapter sliderAdapter;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView oto = findViewById(R.id.oto);

        // Xử lý sự kiện click trong thanh điều hướng
        ImageView imgHome = findViewById(R.id.img_home);
        ImageView imgTime = findViewById(R.id.img_time);
        ImageView imgNoti = findViewById(R.id.img_noti);
        ImageView imgAccount = findViewById(R.id.img_account);
        ImageView imgHelp = findViewById(R.id.img_help);

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imgTime.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, History.class);
            startActivity(intent);
        });
        imgNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotiActivity.class);
                startActivity(intent);
            }
        });
        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });

        oto.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CarActivity.class);
            startActivity(intent);
        });

        // Khởi tạo slideshow
        viewPagerSlider = findViewById(R.id.view_pager_slider);
        circleIndicator = findViewById(R.id.circle_indicator);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.uudai); // Thay bằng các hình ảnh của bạn
        images.add(R.drawable.uudai2); // Thay bằng các hình ảnh của bạn
        images.add(R.drawable.uudai3); // Thay bằng các hình ảnh của bạn

        sliderAdapter = new SliderAdapter(this, images);
        viewPagerSlider.setAdapter(sliderAdapter);
        circleIndicator.setViewPager(viewPagerSlider);

        // Tự động chạy slideshow
        viewPagerSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000); // Slide thay đổi sau 3 giây
            }
        });
    }

    // Runnable cho slideshow
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPagerSlider.setCurrentItem((viewPagerSlider.getCurrentItem() + 1) % sliderAdapter.getItemCount());
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable); // Dừng slideshow khi activity tạm dừng
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000); // Tiếp tục slideshow khi activity hoạt động trở lại
    }
}