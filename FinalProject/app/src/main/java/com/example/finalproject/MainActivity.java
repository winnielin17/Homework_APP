package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText useraccount,userpassword;
    private Button userregister,signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useraccount = (EditText)findViewById(R.id.useraccount);
        userpassword = (EditText)findViewById(R.id.userpassword);
        userregister = (Button)findViewById(R.id.userregister);
        signin = (Button)findViewById(R.id.signin);

        userregister.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.userregister) {
            Intent it = new Intent(MainActivity.this, Register.class);
            startActivity(it);
        }else if(v.getId() == R.id.signin) {
            String userid = useraccount.getText().toString();
            String userpasswd = userpassword.getText().toString();
            //取得sharedpreference
            SharedPreferences preference = getSharedPreferences("data", MODE_PRIVATE);
            //判斷登入畫面輸入的帳號密碼是否跟註冊的帳號密碼一樣
            if(userid.equals(preference.getString("id","winnielin")) &&
                    userpasswd.equals(preference.getString("password","110816003"))){
                SharedPreferences.Editor edit = preference.edit();
                edit.apply();
                //是的話顯示成功登入
                Toast.makeText(MainActivity.this,"登入成功!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this, Home.class);
                startActivity(it);
            }else {
                //不是則顯示登入失敗
                Toast.makeText(MainActivity.this,"登入失敗", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainActivity.this,"Error!!!", Toast.LENGTH_SHORT).show();
        }
    }
}