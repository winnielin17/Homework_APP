package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    RadioGroup rg;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup) findViewById(R.id.radiogroup);
        back = findViewById(R.id.background);

        int[] cb_id = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3,
                       R.id.checkbox4, R.id.checkbox5, R.id.checkbox6,
                       R.id.checkbox7, R.id.checkbox8, R.id.checkbox9};

        for(int id:cb_id) {
            CheckBox cb = (CheckBox) findViewById(id);
            cb.setOnCheckedChangeListener(this);
        }
    }

    public void showColor(View v) {
        if(rg.getCheckedRadioButtonId() == R.id.rb_red) {
            back.setBackgroundColor(Color.rgb(244, 67, 54));
        }
        else if(rg.getCheckedRadioButtonId() == R.id.rb_orange) {
            back.setBackgroundColor(Color.rgb(255, 152, 0));
        }
        else if(rg.getCheckedRadioButtonId() == R.id.rb_yellow) {
            back.setBackgroundColor(Color.rgb(255, 235, 59));
        }
        else if(rg.getCheckedRadioButtonId() == R.id.rb_green) {
            back.setBackgroundColor(Color.rgb(76, 175, 80));
        }
        else if(rg.getCheckedRadioButtonId() == R.id.rb_blue) {
            back.setBackgroundColor(Color.rgb(33, 150, 243));
        }
        else if(rg.getCheckedRadioButtonId() == R.id.rb_white) {
            back.setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

    ArrayList<CompoundButton> select = new ArrayList<>();
    int counter = 0;

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
        int visible = View.GONE;
        String str = "";

        if(check) {
            counter++;
            select.add(compoundButton);
        }
        else {
            counter--;
            select.remove(compoundButton);
        }

        if(counter == 0) {
            visible = View.GONE;
            str = "請選擇菜色";
        }
        else if(counter < 3) {
            visible = View.GONE;
            str = "選擇的菜色少於3樣!!\n請增加選取菜色";
        }
        else if(counter == 3) {
            visible = View.VISIBLE;
        }
        else if(counter == 4) {
            visible = View.VISIBLE;
        }
        else if(counter > 4) {
            visible = View.GONE;
            str = "選擇的菜色多於4樣!!\n請減少選取菜色";
        }

        ((Button) findViewById(R.id.button)).setVisibility(visible);
        ((TextView) findViewById(R.id.txv_order)).setText(str);
    }

    public void showOrder(View v) {
        String str = "";

        if(counter == 3) {
            str = "總共50元";
        }
        else if(counter == 4) {
            str = "總共60元";
        }

        ((TextView) findViewById(R.id.txv_order)).setText(str);
    }
}