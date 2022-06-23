package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnTouchListener {

    TextView txv;
    Button btn;
    View back;
    int counter = 0;
    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.textview);
        btn = (Button) findViewById(R.id.button);
        back = (View) findViewById(R.id.background);

        btn.setOnClickListener(this);
        back.setOnTouchListener(this);

        btn.setBackgroundColor(Color.rgb(3, 169, 244));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            counter = 0;
            txv.setText("" + counter);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent m) {
        if(m.getAction() == MotionEvent.ACTION_DOWN) {
            //相對於View左上角的坐標
            y1 = m.getY();
            x1 = m.getX();
        }
        if(m.getAction() == MotionEvent.ACTION_UP) {
            y2 = m.getY();
            x2 = m.getX();

            //往上 y1>y2
            if(y1 - y2 > 150) {
                counter = counter + 2;
                txv.setText("" + counter);
            }
            //往下 y2>y1
            else if(y2 - y1 > 150) {
                counter = counter - 2;
                txv.setText("" + counter);
            }
            //往左
            else if(x1 - x2 > 80) {
                counter--;
                txv.setText("" + counter);
            }
            //往右
            else if(x2 - x1 > 80) {
                counter++;
                txv.setText("" + counter);
            }
        }
        return true;
    }
}