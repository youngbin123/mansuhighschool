package com.mbtl.mansuhighschool;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class subjectimer extends AppCompatActivity {

    private Button mStartBtn, mStopBtn, mRecordBtn, mPauseBtn;
    private TextView mTimeTextView, mRecordTextView, mRecordTextDifView;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectimer);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#4ea1d3"));
        }

        mStartBtn = (Button) findViewById(R.id.btn_start);
        mStopBtn = (Button) findViewById(R.id.btn_stop);
        mRecordBtn = (Button) findViewById(R.id.btn_record);
        mPauseBtn = (Button) findViewById(R.id.btn_pause);
        mTimeTextView = (TextView) findViewById(R.id.timeView);
        mRecordTextView = (TextView) findViewById(R.id.recordView);
        mRecordTextDifView =  (TextView) findViewById(R.id.recorddifView);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                mStopBtn.setVisibility(View.VISIBLE);
                mRecordBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.VISIBLE);
                isRunning = true;

                timeThread = new Thread(new timeThread());
                timeThread.start();
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                mRecordBtn.setVisibility(View.GONE);
                mStartBtn.setVisibility(View.VISIBLE);
                mPauseBtn.setVisibility(View.GONE);
                mRecordTextView.setText("");
                mRecordTextDifView.setText("");
                timeThread.interrupt();
            }
        });

        mRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecordTextView.getText().toString().length() < 2){
                    mRecordTextDifView.setText("00:00:00:00");
                } else{
                    String mcaluculate = mTimeTextView.getText().toString().replace(":","");
                    int mH = Integer.parseInt(mcaluculate.substring(0,2));
                    int mM = Integer.parseInt(mcaluculate.substring(2,4));
                    int mS = Integer.parseInt(mcaluculate.substring(4,6));
                    int ms = Integer.parseInt(mcaluculate.substring(6,8));
                    int mresult = ms + mS*100 + mM*6000 + mH*36000;
                    String mStack = mRecordTextView.getText().toString().substring(0,11).replace(":","");
                    mH = Integer.parseInt(mStack.substring(0,2));
                    mM = Integer.parseInt(mStack.substring(2,4));
                    mS = Integer.parseInt(mStack.substring(4,6));
                    ms = Integer.parseInt(mStack.substring(6,8));
                    mresult =  mresult - (ms + mS*100 + mM*6000 + mH*36000);
                    ms = mresult  % 100;
                    mS = (mresult / 100) % 60;
                    mM = (mresult  / 100) / 60 % 60;
                    mH = (mresult  / 100) / 3600;
                    mcaluculate = String.format("%02d:%02d:%02d:%02d", mH, mM, mS, ms);
                    mRecordTextDifView.setText(mcaluculate + "\n" + mRecordTextDifView.getText());
                }
                mRecordTextView.setText(mTimeTextView.getText().toString()+"\n"+mRecordTextView.getText());
            }
        });

        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
                if (isRunning) {
                    mPauseBtn.setText("일시정지");
                } else {
                    mPauseBtn.setText("시작");
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int hour = (msg.arg1 / 100) / 3600;
            int min = (msg.arg1 / 100) / 60 % 60;
            //1000이 1초 1000*60 은 1분 1000*60*10은 10분 1000*60*60은 한시간

            @SuppressLint("DefaultLocale") String result = String.format("%02d:%02d:%02d:%02d", hour, min, sec, mSec);
            mTimeTextView.setText(result);
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(9);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                mTimeTextView.setText("");
                                mTimeTextView.setText("00:00:00:00");

                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }
}