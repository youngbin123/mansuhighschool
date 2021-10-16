package com.mbtl.mansuhighschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class yooyoungbin extends AppCompatActivity {
    private Button 앞;
    private Button 오른쪽;
    private Button 왼쪽;
    private Button 뒤;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yooyoungbin);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게

        앞 = findViewById(R.id.앞);
        앞.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(packageContext: mansuhighschool.this,m10.class)

            }
        });


        오른쪽 = findViewById(R.id.오른쪽);
        오른쪽.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(packageContext: mansuhighschool.this,m15.class)

            }
        });


        왼쪽 = findViewById(R.id.왼쪽);
        왼쪽.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(packageContext: mansuhighschool.this,m3.class)

            }
        });


        뒤 = findViewById(R.id.뒤);
        뒤.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(packageContext: mansuhighschool.this,m8.class)
            }
        }
    }
}