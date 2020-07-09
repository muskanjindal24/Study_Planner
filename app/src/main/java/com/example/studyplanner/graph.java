package com.example.studyplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class graph extends AppCompatActivity {

    private FirebaseAuth mauth;
    private Button btn2;
    private EditText totalhr;
    private EditText totalsubj;
    private DatabaseReference mDatabase;
    String thr,tsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_schedule1);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        totalsubj= (EditText) findViewById(R.id.totalsub);
        totalhr=(EditText) findViewById(R.id.totalhrs);



        btn2= (Button) findViewById(R.id.add_data_further);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tsub=totalsubj.getText().toString();
                thr=totalhr.getText().toString();
                int i=Integer.parseInt(tsub);
                int j=Integer.parseInt(thr);
                if(j<=(i/2)){
                    Toast.makeText(graph.this,"Total hours is less!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent1 = new Intent(getApplicationContext(), FinalData.class);
                    intent1.putExtra("hi", tsub);
                    intent1.putExtra("hello", thr);
                    startActivity(intent1);
                    writeNewUser(thr, tsub);
                }
            }
        });



        /*Toolbar toolbar = (Toolbar) findViewById(R.id.graph);
        setSupportActionBar(toolbar);
        getSupportActionBar(). setTitle("Graph");*/

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

    private void writeNewUser(String thr, String tsub) {
        ChildTotalData ad= new ChildTotalData(thr,tsub);
        mDatabase.child("TotalData").setValue(ad);
    }

}