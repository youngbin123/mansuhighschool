package com.mbtl.mansuhighschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class leejaewoo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leejaewoo);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함

        Button subject_timer = findViewById(R.id.button1_ljw);
        Button exam_timer = findViewById(R.id.button2_ljw);
        Button mockexam_timer = findViewById(R.id.button3_ljw);

        subject_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), subjectimer.class);
                startActivity(intent);

            }
        });

        exam_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), examtimer.class);
                startActivity(intent);

            }
        });

        mockexam_timer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), mockexamtimer.class);
                startActivity(intent);

            }
        });
    }
}