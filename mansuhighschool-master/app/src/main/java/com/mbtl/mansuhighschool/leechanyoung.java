package com.mbtl.mansuhighschool;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class leechanyoung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leechanyoung);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함

        Button gongji = findViewById(R.id.button1_lcy);
        Button gajung = findViewById(R.id.button2_lcy);
        Button haksa = findViewById(R.id.button3_lcy);

        gongji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ims.icehs.kr/boardCnts/list.do?boardID=21715&m=0401&s=ims"));
                startActivity(intent);



            }
        });
        gajung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ims.icehs.kr/boardCnts/list.do?boardID=21716&m=0402&s=ims"));
                startActivity(intent);

            }
        });

        haksa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ims.icehs.kr/schdList.do?m=0414&s=ims"));
                startActivity(intent);
            }
        });
        }
    }
