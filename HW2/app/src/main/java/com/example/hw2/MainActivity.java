package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText lname, fname, phone;
    TextView txv;
    View back, lback, fback, pback;
    Button btn_ch, btn_co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lname = (EditText) findViewById(R.id.edit_last);
        fname = (EditText) findViewById(R.id.edit_first);
        phone = (EditText) findViewById(R.id.edit_phone);
        txv = (TextView) findViewById(R.id.edit_view);
        back = findViewById(R.id.background);
        lback = findViewById(R.id.lname_back);
        fback = findViewById(R.id.fname_back);
        pback = findViewById(R.id.phone_back);
        btn_ch = (Button) findViewById(R.id.btn_check);
        btn_co = (Button) findViewById(R.id.btn_color);
    }

    public void show(View v) {
        if(lname.getText().toString().equals("") || fname.getText().toString().equals("") ||
                phone.getText().toString().equals("")) {
            txv.setText("欄位輸入未完成，\n請完成後再按下確定鍵。");
            txv.setTextColor(Color.rgb(255, 0, 0));
        }
        else {
            txv.setText(lname.getText().toString() +
                        fname.getText() + "的電話是\n" +
                        phone.getText());
            txv.setTextColor(Color.rgb(0, 0, 0));
        }
    }

    public void randomColor(View v) {
        Random x = new Random();
        int red = x.nextInt(256);
        int green = x.nextInt(256);
        int blue = x.nextInt(256);

        back.setBackgroundColor(Color.rgb(red, green, blue));
        lback.setBackgroundColor(Color.rgb(red - 20, green + 20, blue - 15));
        fback.setBackgroundColor(Color.rgb(red + 10, green - 15, blue + 20));
        pback.setBackgroundColor(Color.rgb(red - 30, green + 50, blue - 25));
        btn_ch.setBackgroundColor(Color.rgb(red + 25, green - 20, blue + 15));
        btn_co.setBackgroundColor(Color.rgb(red + 25, green - 20, blue + 15));
        lname.setTextColor(Color.rgb(red - 50, green - 50, blue - 50));
        fname.setTextColor(Color.rgb(red + 30, green + 30 , blue + 30));
        phone.setTextColor(Color.rgb(red + 50, green + 50 , blue + 50));
        btn_ch.setTextColor(Color.rgb(red - 30 , green - 30, blue - 30));
        btn_co.setTextColor(Color.rgb(red - 30 , green - 30, blue - 30));
    }
}