package com.example.studyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Quiz extends AppCompatActivity {

    GridLayout grid;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        grid = (GridLayout)findViewById(R.id.grid);
        setSingleEvent(grid);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.quiz);
        setSupportActionBar(toolbar);
        getSupportActionBar(). setTitle("Quiz Whizz");*/


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_schedule2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_schedule:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivityHP.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_schedule1:
                        startActivity(new Intent(getApplicationContext()
                                ,graph.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.nav_schedule2:

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

    private void setSingleEvent(GridLayout grid){
        for(int i = 0;i < grid.getChildCount(); i++){

            CardView cardview = (CardView)grid.getChildAt(i);
            final int finali = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Toast.makeText(Quiz.this, "Get ready for Quiz", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(Quiz.this,categoryActivity.class);
                    myIntent.putExtra("db",String.valueOf(finali));
                    startActivity(myIntent);
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                mauth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}