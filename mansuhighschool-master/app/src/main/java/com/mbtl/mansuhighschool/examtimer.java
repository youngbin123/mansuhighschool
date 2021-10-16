package com.mbtl.mansuhighschool;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
public class examtimer extends AppCompatActivity {
    LinearLayout timeCountSettingLV, timeCountLV;
    EditText hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn, pauseBtn, stopBtn;
    int hour, minute, second;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examtimer);
        timeCountSettingLV = (LinearLayout)findViewById(R.id.timeCountSettingLV);
        timeCountLV = (LinearLayout)findViewById(R.id.timeCountLV);
        hourET = (EditText)findViewById(R.id.hourET);
        minuteET = (EditText)findViewById(R.id.minuteET);
        secondET = (EditText)findViewById(R.id.secondET);
        hourTV = (TextView)findViewById(R.id.hourTV);
        minuteTV = (TextView)findViewById(R.id.minuteTV);
        secondTV = (TextView)findViewById(R.id.secondTV);
        finishTV = (TextView)findViewById(R.id.finishTV);
        startBtn = (Button)findViewById(R.id.startBtn);
        pauseBtn = (Button)findViewById(R.id.btn_pause);
        stopBtn= (Button)findViewById(R.id.btn_stop);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);
                hourTV.setText(hourET.getText().toString());
                minuteTV.setText(minuteET.getText().toString());
                secondTV.setText(secondET.getText().toString());
                if (TextUtils.isEmpty(hourET.getText())) {hour = 0; } else { hour = Integer.parseInt(hourET.getText().toString()); }
                if (TextUtils.isEmpty(minuteET.getText())) { minute = 0; } else { minute = Integer.parseInt(minuteET.getText().toString()); }
                if (TextUtils.isEmpty(secondET.getText())) { second = 0; } else { second = Integer.parseInt(secondET.getText().toString()); }
                v.setVisibility(View.GONE);
                stopBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                pauseBtn.setText("일시정지");
                isRunning = true;

                timeThread = new Thread(new examtimer.timeThread());
                timeThread.start();
            }

        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = !isRunning;
                if (isRunning) {
                    pauseBtn.setText("일시정지");
                } else {
                    pauseBtn.setText("시작");
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                startBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);
                timeThread.interrupt();
                timeCountSettingLV.setVisibility(View.VISIBLE);
                timeCountLV.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message m) {
            int sec = (m.arg1 / 100) % 60;
            int hour = (m.arg1 / 100) / 3600;
            int min = (m.arg1 / 100) / 60 % 60;

            if(min+sec+hour == 0){
                hourTV.setText("00");
                minuteTV.setText("00");
                secondTV.setText("00");
                timeThread.interrupt();
            } else {

                hourTV.setText(String.valueOf(hour));
                minuteTV.setText(String.valueOf(min));
                secondTV.setText(String.valueOf(sec));

            }

        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            if (TextUtils.isEmpty(hourET.getText())) {hour = 0; } else { hour = Integer.parseInt(hourET.getText().toString()); }
            if (TextUtils.isEmpty(minuteET.getText())) { minute = 0; } else { minute = Integer.parseInt(minuteET.getText().toString()); }
            if (TextUtils.isEmpty(secondET.getText())) { second = 0; } else { second = Integer.parseInt(secondET.getText().toString()); }
            int i = hour *360000 + minute * 6000 + second * 100;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤

                    Message msg = new Message();
                    msg.arg1 = i--;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(9);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                hourTV.setText("00");
                                minuteTV.setText("00");
                                secondTV.setText("00");
                                hourET.setText("00");
                                minuteET.setText("00");
                                secondET.setText("00");

                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }
}