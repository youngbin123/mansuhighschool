package com.mbtl.mansuhighschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;


public class time extends AppCompatActivity {

    String grade = "";
    String classroom = "";

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String getTime = dateFormat.format(date);
        return getTime;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String day = getTime();
        String year = day.substring(0,4);
        String classroom3 = "";
        int day1 = Integer.parseInt(day) + 1;
        String day1s = String.valueOf(day1);





        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함

        String key = "https://open.neis.go.kr/hub/hisTimetable?KEY=96ccdda702004f19b91eb5dee7fb038c&ATPT_OFCDC_SC_CODE=E10&SD_SCHUL_CODE=7310332&AY="
                + year  +"&DGHT_CRSE_SC_NM=%EC%A3%BC%EA%B0%84&ORD_SC_NM=%EC%9D%BC%EB%B0%98%EA%B3%84&DDDEP_NM=7%EC%B0%A8%EC%9D%BC%EB%B0%98&GRADE="
                + grade + "&CLRM_NM" + classroom + "&CLASS_NM=" + classroom + "&TI_FROM_YMD=" + day + "&TI_TO_YMD=" + day;

        Log.i("오늘날짜", key);

        EditText et1 = (EditText) findViewById(R.id.grade);
        EditText et2 = (EditText) findViewById(R.id.classroom);
        Button getgrade=findViewById(R.id.getgrade);

        getgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grade = et1.getText().toString() ;
                classroom = et2.getText().toString() ;
                Toast.makeText(getApplicationContext(), "이제부터 해당 학년과 반의 시간표를 불러옵니다.", Toast.LENGTH_LONG).show();
            }
        });

        Button time1=findViewById(R.id.time1);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), time1.class);
                intent.putExtra("학년", grade);
                intent.putExtra("반", classroom);
                startActivity(intent);
            }
        });

        Button time2=findViewById(R.id.time2);
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), time2.class);
                intent.putExtra("학년", grade);
                intent.putExtra("반", classroom);
                startActivity(intent);
            }
        });

        Button time3=findViewById(R.id.time3);
        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), time3.class);
                intent.putExtra("학년", grade);
                intent.putExtra("반", classroom);
                startActivity(intent);

            }
        });
        Button time4=findViewById(R.id.time4);
        time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), time4.class);
                intent.putExtra("학년", grade);
                intent.putExtra("반", classroom);
                startActivity(intent);
            }
        });
        Button time5=findViewById(R.id.time5);
        time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), time5.class);
                intent.putExtra("학년", grade);
                intent.putExtra("반", classroom);
                startActivity(intent);
            }
        });
    }
}