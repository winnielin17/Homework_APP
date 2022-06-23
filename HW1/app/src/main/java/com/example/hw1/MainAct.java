package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainAct extends AppCompatActivity {
    TextView mytxv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        mytxv = (TextView) findViewById(R.id.txv);
    }

    public void show7(View v) {
        mytxv.setText("7");
    }

    public void show8(View v) {
        mytxv.setText("8");
    }

    public void show9(View v) {
        mytxv.setText("9");
    }

    public void show4(View v) {
        mytxv.setText("4");
    }

    public void show5(View v) {
        mytxv.setText("5");
    }

    public void show6(View v) {
        mytxv.setText("6");
    }

    public void show1(View v) {
        mytxv.setText("1");
    }

    public void show2(View v) {
        mytxv.setText("2");
    }

    public void show3(View v) {
        mytxv.setText("3");
    }

    public void show_name(View v) {
        mytxv.setText("我是佩瑩!");
    }

    public void show0(View v) {
        mytxv.setText("0");
    }

    public void show_dlt(View v) {
        mytxv.setText("請選擇按鈕");
    }
}