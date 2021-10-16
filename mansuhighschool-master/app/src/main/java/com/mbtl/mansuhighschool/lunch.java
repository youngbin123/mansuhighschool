package com.mbtl.mansuhighschool;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class lunch extends AppCompatActivity {

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String getTime = dateFormat.format(date);
        return getTime;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.menu1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("알레르기 유발 식품").setMessage("1.난류, 2.우유, 3.메밀, 4.땅콩, 5.대두, 6.밀\n7.고등어, 8.게, 9.새우, 10.돼지고기\n11.복숭아, 12.토마토, 13.아황산염\n14.호두,15.닭고기, 16.쇠고기, 17.오징어\n18.조개류(굴,전복,홍합 등)");

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String day = getTime();
        int day2 = Integer.parseInt(day) + 4;
        String day3 = String.valueOf(day2);
        String key = "https://open.neis.go.kr/hub/mealServiceDietInfo?Type=xml&pIndex=1&pSize=100&fd6fe841f69543cf85df91467f4151c4&ATPT_OFCDC_SC_CODE=E10&SD_SCHUL_CODE=7310332&MMEAL_SC_CODE=2&"
                + "MLSV_FROM_YMD=" + day + "&MLSV_TO_YMD=" + day3;
        String result = null;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch);
        Log.i("오늘날짜", key);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//핸드폰이 다크모드여도 앱 배경 안변하게 함


        StrictMode.enableDefaults();
        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false, indays = false, inmenu = false;

        String days = null, menu = null, word = null;
        String []menus = null;


        try{
            URL url = new URL("https://open.neis.go.kr/hub/mealServiceDietInfo?Type=xml&pIndex=1&pSize=100&fd6fe841f69543cf85df91467f4151c4&ATPT_OFCDC_SC_CODE=E10&SD_SCHUL_CODE=7310332&MMEAL_SC_CODE=2&"
                    + "MLSV_FROM_YMD=" + day + "&MLSV_TO_YMD=" + day3); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("MLSV_YMD")){ //title 만나면 내용을 받을수 있게 하자
                            indays = true;
                        }
                        if(parser.getName().equals("DDISH_NM")){ //address 만나면 내용을 받을수 있게 하자
                            inmenu = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(indays){ //isTitle이 true일 때 태그의 내용을 저장.
                            days = parser.getText();
                            indays = false;
                        }
                        if(inmenu){ //isAddress이 true일 때 태그의 내용을 저장.
                            menu = parser.getText();
                            menus = menu.split("<br/>");
                            inmenu = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("row")){
                            status1.setText(status1.getText()+"날짜: "+ days +"\n메뉴: "+ menus[0] +"\n"+menus[1]
                            +"\n"+menus[2]+"\n"+menus[3]+"\n"+menus[4]+"\n\n");
                            String content = status1.getText().toString();
                            SpannableString spannableString = new SpannableString(content);

                            word = "날짜: "+days+"";
                            int start = content.indexOf(word);
                            int end = start + word.length();

                            spannableString.setSpan(new RelativeSizeSpan(1.3f), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);


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
