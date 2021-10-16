package com.mbtl.mansuhighschool;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatDelegate;



public class jungtaehyun extends AppCompatActivity {

    Button getBtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jungtaehyun);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함

        result = (TextView)findViewById(R.id.result);
        getBtn = (Button) findViewById(R.id.getBtn);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
            }
        });
    }
    int a = 1;
    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH) + 1;
    int day = now.get(Calendar.DAY_OF_MONTH);

    private void getWebsite(){
        Document doc = null;

        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    String m = " ";
                    String d = " ";
                    if (month < 10) {
                        m = "0" + month;
                    }
                    else m = String.valueOf(month);
                    if (day < 10){
                        d = "0" + day;
                    }
                    else d = String.valueOf(day);
                    Document doc = Jsoup.connect("https://news.naver.com/main/list.naver?mode=LS2D&sid2=250&sid1=102&mid=shm&date=" + year + m + d + "&page=" + a).get();

//                    Elements title = doc.select("a");
                    Elements content = doc.select("span.lede");


//                    builder.append(title).append("\n");
                    builder.append(content).append("\n");

                    a = a+1;
                } catch (IOException e) {
                    builder.append("Error").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();



    }
}