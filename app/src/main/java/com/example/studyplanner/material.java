package com.example.studyplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class material extends AppCompatActivity {
    private FirebaseAuth mauth;

    ListView listView;
    String[] bookName = {"Programming in ANSI C", "Object Oriented Programming with C++","Java-The Complete Reference", "Web Engineering",
            "Computer Organization and Design Arm Edition", "Fundamentals of Database System"};
    String[] bookAuthor = {"Author: E Balaguruswamy", "Author: E Balaguruswamy", "Author: Herbert Schildt", "Author: Robin Nixon", "Author: David Patterson, John Hennessy", "Author: Elmasri, Navathe"};
    int images[] = {R.drawable.ansic, R.drawable.cplus, R.drawable.javafinal, R.drawable.webfinal, R.drawable.comifinal, R.drawable.databasefinal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.material);
        setSupportActionBar(toolbar);
        getSupportActionBar(). setTitle("Material");*/

        setContentView(R.layout.activity_material);
        listView = findViewById(R.id.listView);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_schedule3);
        MyAdapter myAdapter = new MyAdapter(this, bookName, bookAuthor, images);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtra("title", bookName[0]);
                    intent.putExtra("author", bookAuthor[0]);
                    intent.putExtra("position", ""+0);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[1]);
                    intent.putExtra("title", bookName[1]);
                    intent.putExtra("author", bookAuthor[1]);
                    intent.putExtra("position", ""+1);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[2]);
                    intent.putExtra("title", bookName[2]);
                    intent.putExtra("author", bookAuthor[2]);
                    intent.putExtra("position", ""+2);
                    startActivity(intent);
                }
                if(position==3){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[3]);
                    intent.putExtra("title", bookName[3]);
                    intent.putExtra("author", bookAuthor[3]);
                    intent.putExtra("position", ""+3);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[4]);
                    intent.putExtra("title", bookName[4]);
                    intent.putExtra("author", bookAuthor[4]);
                    intent.putExtra("position", ""+4);
                    startActivity(intent);
                }
                if(position==5){
                    Intent intent = new Intent(getApplicationContext(), BooksList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[5]);
                    intent.putExtra("title", bookName[5]);
                    intent.putExtra("author", bookAuthor[5]);
                    intent.putExtra("position", ""+5);
                    startActivity(intent);
                }
            }
        });
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
                        startActivity(new Intent(getApplicationContext()
                                ,Quiz.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_schedule3:

                        return true;
                }
                return false;
            }
        });
    }
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String bTitle[];
        String bAuthor[];
        int bImage[];
        MyAdapter (Context c, String title[], String author[], int image[]){
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.bTitle =title;
            this.bAuthor =author;
            this.bImage = image;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView bkTitle = row.findViewById(R.id.textView1);
            TextView bkAuthor = row.findViewById(R.id.textView2);
            images.setImageResource(bImage[position]);
            bkTitle.setText(bTitle[position]);
            bkAuthor.setText(bAuthor[position]);
            return row;
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