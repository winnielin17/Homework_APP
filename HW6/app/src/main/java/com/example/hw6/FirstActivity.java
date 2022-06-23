package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    Button btn_act, btn_fin;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn_act = (Button) findViewById(R.id.button1);
        btn_fin = (Button) findViewById(R.id.button2);
        txv = (TextView) findViewById(R.id.textview);

        btn_act.setBackgroundColor(Color.rgb(33, 150, 243));
        btn_fin.setBackgroundColor(Color.rgb(33, 150, 243));

        Intent it = getIntent();
        String s = it.getStringExtra("紀錄");
        if(s.length() == 0) {
            txv.setText("1");
        }
        else {
            txv.setText(s + " " + getResources().getString(R.string.arrow) + " 1");
        }
    }

    public void onActivate(View v) {
        Intent it2 = new Intent(this, SecondActivity.class);
        it2.putExtra("紀錄", txv.getText().toString());
        startActivityForResult(it2, 100);
    }

    public void onFinish(View v) {
        Intent itback = new Intent();
        itback.putExtra("紀錄", txv.getText().toString());
        setResult(RESULT_OK, itback);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);

        if(resultCode == RESULT_OK) {
            txv.setText(it.getStringExtra("紀錄") + " " +
                    getResources().getString(R.string.arrow) + " 1");
        }
    }
}