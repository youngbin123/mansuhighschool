package com.mbtl.mansuhighschool;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class timetable extends AppCompatActivity {
    String grade = "";
    String classroom = "";
    String semester = "";

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
        String year = day.substring(0,3);
        String classroom3 = "";
        int day1 = Integer.parseInt(day) + 1;
        int day2 = Integer.parseInt(day) + 2;
        int day3 = Integer.parseInt(day) + 3;
        int day4 = Integer.parseInt(day) + 4;
        String day1s = String.valueOf(day1);
        String day2s = String.valueOf(day2);
        String day3s = String.valueOf(day3);
        String day4s = String.valueOf(day4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함

        int classroom2 = Integer.parseInt(classroom);

        String key = "https://open.neis.go.kr/hub/hisTimetable?KEY=96ccdda702004f19b91eb5dee7fb038c&ATPT_OFCDC_SC_CODE=E10&SD_SCHUL_CODE=7310332&AY="
                + year  +"&DGHT_CRSE_SC_NM=%EC%A3%BC%EA%B0%84&ORD_SC_NM=%EC%9D%BC%EB%B0%98%EA%B3%84&DDDEP_NM=7%EC%B0%A8%EC%9D%BC%EB%B0%98&GRADE="
                + 2 + "&CLRM_NM" + 05 + "&CLASS_NM=" + 5 + "&TI_FROM_YMD=" + day + "&TI_TO_YMD=" + day3;

        StrictMode.enableDefaults();
        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!
        boolean initem = false, intimetable = false;
        String timetable = null;

        try{
            URL url = new URL("https://open.neis.go.kr/hub/hisTimetable?KEY=96ccdda702004f19b91eb5dee7fb038c&ATPT_OFCDC_SC_CODE=E10&SD_SCHUL_CODE=7310332&AY="
                    + year + "&DGHT_CRSE_SC_NM=%EC%A3%BC%EA%B0%84&ORD_SC_NM=%EC%9D%BC%EB%B0%98%EA%B3%84&DDDEP_NM=7%EC%B0%A8%EC%9D%BC%EB%B0%98&GRADE="
                    + 2 + "&CLRM_NM" + 05 + "&CLASS_NM=" + 5 + "&TI_FROM_YMD=" + day + "&TI_TO_YMD=" + day
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("ITRT_CNTNT")){
                            intimetable = true;
                        }
                        if(parser.getName().equals("message")){
                            status1.setText(status1.getText()+"에러");
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때

                        if(intimetable){ //isAddress이 true일 때 태그의 내용을 저장.
                            timetable = parser.getText();
                            intimetable = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("row")){
                            status1.setText(status1.getText()+"시간표"+timetable);
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("데이터를 불러올 수 없습니다. \n인터넷 연결을 확인해주세요.");
        }


}
}