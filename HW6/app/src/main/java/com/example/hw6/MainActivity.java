package com.example.hw6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.textview);

        if(txv.getText().toString().length() == 0) {
            Intent it = new Intent(this, FirstActivity.class);
            it.putExtra("紀錄", txv.getText().toString());
            startActivityForResult(it, 100);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);

        if(resultCode == RESULT_OK) {
            txv.setText(it.getStringExtra("紀錄"));
        }
    }
}