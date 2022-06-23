package com.example.hw8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class Direction extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

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
}