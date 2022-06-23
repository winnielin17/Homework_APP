package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity
        implements View.OnClickListener {

    private Button clear,confirm;
    private EditText userid,userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userid = (EditText)findViewById(R.id.userid);
        userpassword = (EditText)findViewById(R.id.userpassword);
        confirm = (Button)findViewById(R.id.confirm);
        clear = (Button)findViewById(R.id.clear);

        confirm.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confirm) {
            //新增一個sharedpreference
            SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //存入資料
            editor.putString("id", userid.getText().toString());
            editor.putString("password", userpassword.getText().toString());
            editor.apply();

            String str = userid.getText().toString();
            String str1 = userpassword.getText().toString();
            //判斷輸入的值是否為空值
            if(TextUtils.isEmpty(str)) {
                Toast.makeText(Register.this, "帳號不能為空",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(str1)) {
                Toast.makeText(Register.this, "密碼不能為空", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Register.this,"註冊成功!",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Register.this,MainActivity.class);
                startActivity(intent1);
            }
        }else if(v.getId() == R.id.clear) {
            userid.setText("");
            userpassword.setText("");
        }else {
            Toast.makeText(Register.this, "Error!!!", Toast.LENGTH_SHORT).show();
        }
    }
}