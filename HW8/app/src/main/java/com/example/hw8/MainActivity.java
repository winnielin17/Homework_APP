package com.example.hw8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    static final String db_name = "ProductDB";
    static final String tb_name = "product";
    static final int max = 8;
    static final String[] FROM = new String[] {"name", "price"};
    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    int TransferId = 0;

    EditText edt_name, edt_price;
    Button btn_insert, btn_update, btn_delete;
    ListView lv;

    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = (EditText) findViewById(R.id.editName);
        edt_price = (EditText) findViewById(R.id.editPrice);
        btn_insert = (Button) findViewById(R.id.btnInsert);
        btn_update = (Button) findViewById(R.id.btnUpdate);
        btn_delete = (Button) findViewById(R.id.btnDelete);
        lv = (ListView) findViewById(R.id.listview);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + tb_name +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(32), " +
                "price VARCHAR(16))";
        db.execSQL(createTable);

        cur = db.rawQuery("SELECT * FROM " + tb_name, null);

        if(cur.getCount() == 0) {
            addData("??????", "35");
            addData("?????????", "30");
            addData("?????????", "30");
        }

        adapter = new SimpleCursorAdapter(this,
                R.layout.item, cur, FROM,
                new int[] {R.id.name, R.id.price},
                0);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        requery(); //???????????????????????????????????????

        navView.setNavigationItemSelectedListener(item -> {

            Intent it = new Intent();

            switch (item.getItemId()) {
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
                case R.id.home:
                    it.setClass(this, MainActivity.class);
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
        db.close(); //???????????????
    }

    private void addData(String name, String price) {
        ContentValues cv = new ContentValues(2);
        cv.put(FROM[0], name);
        cv.put(FROM[1], price);

        db.insert(tb_name, null, cv);
    }

    private void update(String name, String price, int id) {
        ContentValues cv = new ContentValues(2);
        cv.put(FROM[0], name);
        cv.put(FROM[1], price);

        db.update(tb_name, cv, "_id=" + id, null); //??????_id???????????????
    }

    private void requery() {
        cur = db.rawQuery("SELECT * FROM " + tb_name, null);
        adapter.changeCursor(cur);
        if(cur.getCount() == max) {
            btn_insert.setEnabled(false); //??????????????????????????????
        }
        else {
            btn_insert.setEnabled(true);
        }
        btn_update.setEnabled(false); //???????????????????????????
        btn_delete.setEnabled(false); //???????????????????????????
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position);
        edt_name.setText(cur.getString(1)); //name??????
        edt_price.setText(cur.getString(2)); //price??????

        TransferId = cur.getInt(0);

        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
    }

    public void onInsertUpdate(View v) {
        String str_name = edt_name.getText().toString().trim();
        String str_price = edt_price.getText().toString().trim();
        if(str_name.length() == 0 || str_price.length() == 0) {
            return;
        }
        if(v.getId() == R.id.btnUpdate) {
            //??????_id??????????????????_id?????????
            update(str_name, str_price, TransferId);
        }
        else if(v.getId() == R.id.btnInsert) {
            addData(str_name, str_price);
        }
        requery(); //??????Cursor??????
    }

    public void onDelete(View v) {
        db.delete(tb_name, "_id=" + TransferId, null);
        requery(); //??????Cursor??????
    }
}