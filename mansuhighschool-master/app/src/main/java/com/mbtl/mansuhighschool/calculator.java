package com.mbtl.mansuhighschool;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class calculator extends AppCompatActivity
{
    Button sum_value,mp,sum;
    TextView value;
    EditText put_value,put_value2,put_value3;

    int value1, value2, value3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        sum = findViewById(R.id.button10);
        mp = findViewById(R.id.button11);
        sum_value = findViewById(R.id.sum_value);

        value = findViewById(R.id.value);

        put_value = findViewById(R.id.put_value);
        put_value2 = findViewById(R.id.put_value2);
        put_value3 = findViewById(R.id.put_value3);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (TextUtils.isEmpty(put_value.getText())){value1 = 0;}
                else{ value1 = Integer.parseInt(put_value.getText().toString());}
                if (TextUtils.isEmpty(put_value2.getText())){value2 = 0;}
                else{value2 = Integer.parseInt(put_value2.getText().toString());}
                if (TextUtils.isEmpty(put_value3.getText())){value3 = 0;}
                else{value3= Integer.parseInt(put_value3.getText().toString());}

                int answer = value3*(2*value1 + (value1-1)*value2) / 2;
                value.setText(String.valueOf(answer));
            }
        });
        mp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (TextUtils.isEmpty(put_value.getText())){value1 = 0;}
                else{ value1 = Integer.parseInt(put_value.getText().toString());}
                if (TextUtils.isEmpty(put_value2.getText())){value2 = 0;}
                else{value2 = Integer.parseInt(put_value2.getText().toString());}
                if (TextUtils.isEmpty(put_value3.getText())){value3 = 0;}
                else{value3= Integer.parseInt(put_value3.getText().toString());}
                int finalanswer = 0;
                if (value2 < 1 && value2 >-1)
                {
                    double answer = value1*(1-Math.pow(value2, value3)) / (1-value2);
                    finalanswer = (int) Math.round(answer);
                }
                else
                {
                    double answer = value1*(Math.pow(value2, value3)-1) / (value2-1);
                    finalanswer = (int) Math.round(answer);
                }
                value.setText(String.valueOf(finalanswer));
            }
        });
    }
}