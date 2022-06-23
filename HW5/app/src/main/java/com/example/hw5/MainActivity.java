package com.example.hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, TextWatcher{

    double[] energy = {3.1, 4.4, 13.2, 9.7, 5.1, 3.7};
    Spinner sports;
    EditText weight, time;
    TextView rate, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sports = (Spinner) findViewById(R.id.sports);
        weight = (EditText) findViewById(R.id.weight);
        time = (EditText) findViewById(R.id.time);
        rate = (TextView) findViewById(R.id.rate);
        result = (TextView) findViewById(R.id.result);

        sports.setOnItemSelectedListener(this);
        weight.addTextChangedListener(this);
        time.addTextChangedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        rate.setText(String.valueOf(energy[position]));
        calculate(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String w = weight.getText().toString();
        String t = time.getText().toString();
        if(w.isEmpty() || w.equals(".") || t.isEmpty() || t.equals(".")) {
            result.setText("請輸入體重及運動時間");
            return;
        }

        int pos = sports.getSelectedItemPosition();
        long kcal = Math.round(energy[pos] * Double.parseDouble(w) * Double.parseDouble(t));
        result.setText(String.format("消耗能量 %d 千卡", kcal));
    }

    public void calculate(View v) {
        String w = weight.getText().toString();
        String t = time.getText().toString();
        if(w.isEmpty() || w.equals(".") || t.isEmpty() || t.equals(".")) {
            result.setText("請輸入體重及運動時間");
            return;
        }

        int pos = sports.getSelectedItemPosition();
        long kcal = Math.round(energy[pos] * Double.parseDouble(w) * Double.parseDouble(t));
        result.setText(String.format("消耗能量 %d 千卡", kcal));
    }
}