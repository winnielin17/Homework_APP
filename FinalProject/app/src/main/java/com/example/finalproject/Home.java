package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class Home extends AppCompatActivity
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, AdapterView.OnItemClickListener {

    static final String db_name = "AccountingDB";
    static final String tb_name = "accounting";
    static final int max = 15;
    static final String[] FROM = new String[] {"date", "time", "category", "money"};
    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    int TransferId = 0;

    EditText edt_category, edt_money;
    Button btn_insert, btn_update, btn_delete;
    ListView lv;
    Calendar c = Calendar.getInstance();
    TextView txv_date, txv_time;

    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edt_category = (EditText) findViewById(R.id.editcategory);
        edt_money = (EditText) findViewById(R.id.editmoney);
        btn_insert = (Button) findViewById(R.id.btnInsert);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        lv = (ListView) findViewById(R.id.listview);
        txv_date = (TextView) findViewById(R.id.textviewdate);
        txv_time = (TextView) findViewById(R.id.textviewtime);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        txv_date.setOnClickListener(this);
        txv_time.setOnClickListener(this);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date VARCHAR(32)," +
                "time VARCHAR(32)," +
                "category VARCHAR(32)," +
                "money VARCHAR(16))";
        db.execSQL(createTable);

        cur = db.rawQuery("SELECT * FROM " + tb_name, null);

        if(cur.getCount() == 0) {
            addData("2022/1/1", "10：10", "零用錢", "1000");
            addData("2022/1/1", "12：20", "午餐", "100");
        }

        adapter = new SimpleCursorAdapter(this,
                R.layout.item, cur, FROM,
                new int[] {R.id.date, R.id.time, R.id.category, R.id.money},
                0);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        requery(); //自訂重新查詢及設定按鈕狀態

        navView.setNavigationItemSelectedListener(item -> {

            Intent it = new Intent();

            switch (item.getItemId()) {
                case R.id.home:
                    it.setClass(this, Home.class);
                    startActivity(it);
                    finish();
                    break;
                case R.id.copyright:
                    it.setClass(this, Copyright.class);
                    startActivity(it);
                    finish();
                    break;
                case R.id.direction:
                    it.setClass(this, Direction.class);
                    startActivity(it);
                    finish();
                    break;
                default:
                    break;
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    protected void onDestroy(){
        super.onDestroy();
        db.close(); //關閉資料庫
    }

    @Override
    public void onClick(View v) {
        if(v == txv_date) {
            new DatePickerDialog(this, this,
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH), //由0算起
                    c.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if(v == txv_time) {
            new TimePickerDialog(this, this,
                    c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.MINUTE),
                    true).show();
        }
    }

    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {
        txv_date.setText(y + "/" + (m+1) + "/" + d); //月份+1
    }

    @Override
    public void onTimeSet(TimePicker v, int h, int m) {
        txv_time.setText(h + "：" + m);
    }

    private void addData(String date, String time, String category, String money) {
        ContentValues cv = new ContentValues(4);
        cv.put(FROM[0], date);
        cv.put(FROM[1], time);
        cv.put(FROM[2], category);
        cv.put(FROM[3], money);

        db.insert(tb_name, null, cv);
    }

    private void update(String date, String time, String category, String money, int id) {
        ContentValues cv = new ContentValues(4);
        cv.put(FROM[0], date);
        cv.put(FROM[1], time);
        cv.put(FROM[2], category);
        cv.put(FROM[3], money);

        db.update(tb_name, cv, "_id=" + id, null); //更新_id所指的紀錄
    }

    private void requery() {
        cur = db.rawQuery("SELECT * FROM " + tb_name, null);
        adapter.changeCursor(cur);
        if(cur.getCount() == max) {
            btn_insert.setEnabled(false); //已達上限，停用新增鈕
        }
        else {
            btn_insert.setEnabled(true);
        }
        btn_update.setEnabled(false); //待選取項目後再啟用
        btn_delete.setEnabled(false); //待選取項目後再啟用
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position);
        txv_date.setText(cur.getString(1)); //date欄位
        txv_time.setText(cur.getString(2)); //time欄位
        edt_category.setText(cur.getString(3)); //category欄位
        edt_money.setText(cur.getString(4)); //money欄位

        TransferId = cur.getInt(0);

        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
    }

    public void onInsertUpdate(View v) {
        String str_date = txv_date.getText().toString().trim();
        String str_time = txv_time.getText().toString().trim();
        String str_category = edt_category.getText().toString().trim();
        String str_money = edt_money.getText().toString().trim();

        if(str_date.equals("日期") || str_time.equals("時間") ||
                str_category.length() == 0 || str_money.length() == 0) {
            return;
        }
        if(v.getId() == R.id.btnUpdate) {
            //取得_id值，更新含此_id的紀錄
            update(str_date, str_time, str_category, str_money, TransferId);
            Toast.makeText(Home.this,"修改成功!", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.btnInsert) {
            addData(str_date, str_time, str_category, str_money);
            Toast.makeText(Home.this,"新增成功!", Toast.LENGTH_SHORT).show();
        }
        requery(); //更新Cursor內容
    }

    public void onDelete(View v) {
        db.delete(tb_name, "_id=" + TransferId, null);
        Toast.makeText(Home.this,"刪除成功!", Toast.LENGTH_SHORT).show();
        requery(); //更新Cursor內容
    }
}