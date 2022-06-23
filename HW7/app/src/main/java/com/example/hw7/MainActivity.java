package com.example.hw7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        navView.setNavigationItemSelectedListener(item -> {

            Intent it = new Intent();

            switch (item.getItemId()) {
                case R.id.item1:
                    it.setClass(this, Copyright.class);
                    startActivity(it);
                    finish();
                    break;
                case R.id.item2:
                    it.setClass(this, Direction.class);
                    startActivity(it);
                    finish();
                    break;
                case R.id.item3:
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
}