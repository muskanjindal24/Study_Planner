package com.example.myhomeworkplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityHP extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_h_p);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_schedule);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_schedule:

                        return true;

                    case R.id.nav_schedule1:
                        startActivity(new Intent(getApplicationContext()
                                ,graph.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_schedule2:
                        startActivity(new Intent(getApplicationContext()
                                ,Quiz.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_schedule3:
                        startActivity(new Intent(getApplicationContext()
                                ,material.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}