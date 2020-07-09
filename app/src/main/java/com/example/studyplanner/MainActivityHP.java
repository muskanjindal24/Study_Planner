package com.example.studyplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Data;

public class MainActivityHP extends AppCompatActivity {
    private FloatingActionButton fabbtn;
    // Write a message to the database
    private DatabaseReference mdatabase;
    private FirebaseAuth mauth;

    private RecyclerView recyclerView;

    //update input fields
    private EditText subnameup;
    private EditText subdateup;
    private EditText subnoteup;
    private Button btnDeleteup;
    private Button btnUpdateup;
    private Button btnSave;
    private Button btnCancle;

    //variable

    private String name;
    private String note;
    private String date;
    private String post_key;

    EditText ed;
    Calendar due;
    int day, month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_h_p);
        mauth = FirebaseAuth.getInstance();
        FirebaseUser muser = mauth.getCurrentUser();
        String uid = muser.getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("TaskNote").child(uid);
        mdatabase.keepSynced(true);

        fabbtn = findViewById(R.id.fab_btn);

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar_custom_schedule);
        setSupportActionBar(toolbar);
        getSupportActionBar(). setTitle("My Task");

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivityHP.this);
                LayoutInflater inflater = LayoutInflater.from(MainActivityHP.this);
                View myview = inflater.inflate(R.layout.custominputfields,null);
                mydialog.setView(myview);
                final AlertDialog dialog = mydialog.create();
                dialog.setCancelable(false);
                final EditText subname  = myview.findViewById(R.id.editsubname);
                final EditText note = myview.findViewById(R.id.editnote);
                final EditText duedate = myview.findViewById(R.id.editduedate);


                btnSave = myview.findViewById(R.id.btn_save);
                btnCancle = myview.findViewById(R.id.btn_cancle);

              /*  ed.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View view) {
                        ed = (EditText)findViewById(R.id.editduedate);
                        due = Calendar.getInstance();

                        day = due.get(Calendar.DAY_OF_MONTH);
                        month = due.get(Calendar.MONTH);
                        year = due.get(Calendar.YEAR);

                        month= month+1;
                        ed.setText(day+"/"+month+"/"+year);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivityHP.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1 = i1 + 1;
                                ed.setText(i2+"/"+i1+"/"+i);

                            }
                        },year,month,day);
                        datePickerDialog.show();
                    }
                });*/

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String msubname = subname.getText().toString().trim();
                        String mnote = note.getText().toString().trim();
                        String mduedate = duedate.getText().toString().trim();

                        if(TextUtils.isEmpty(msubname))
                        {
                            subname.setError("Required Field..");
                            return;
                        }
                        if(TextUtils.isEmpty(mnote))
                        {
                            subname.setError("Required Field..");
                            return;
                        }
                        else if(TextUtils.isEmpty(mduedate))
                        {
                            subname.setError("Required Field..");
                            return;
                        }
                        String id = mdatabase.push().getKey();
                     //   String datee = DateFormat.getDateInstance().format(new Date());
                        // data data = new data(msubname,mnote,mduedate,id,datee);
                        Data data = new Data(msubname,mnote,mduedate,id);
                        assert id != null;
                        mdatabase.child(id).setValue(data);
                        Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

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
    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseRecyclerAdapter<Data,MyViewHolder>adapter = new FirebaseRecyclerAdapter<Data, MyViewHolder>(
                Data.class,
                R.layout.item_data,
                MyViewHolder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, final Data data, final int i) {
                viewHolder.setSubnote(data.getNote());
                viewHolder.setSubdate(data.getDate());
                viewHolder.setSubname(data.getsubname());

                viewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        post_key = getRef(i).getKey();
                        name=data.getsubname();
                        note = data.getNote();
                        date = data.getDate();
                        updateData();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void updateData(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivityHP.this);
        LayoutInflater inflater = LayoutInflater.from(MainActivityHP.this);
        View myview = inflater.inflate(R.layout.updateinputfields,null);
        mydialog.setView(myview);
        final AlertDialog dialog = mydialog.create();

        subdateup = myview.findViewById(R.id.updateduedate);
        subnameup = myview.findViewById(R.id.updatesubname);
        subnoteup = myview.findViewById(R.id.updatenote);

        subnameup.setText(name);
        subnameup.setSelection(name.length());

        subnoteup.setText(note);
        subnoteup.setSelection(note.length());

        subdateup.setText(date);
        subdateup.setSelection(date.length());

        btnDeleteup = myview.findViewById(R.id.btn_dlt_update);
        btnUpdateup = myview.findViewById(R.id.btn_update_upd);

        btnUpdateup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=subnameup.getText().toString().trim();
                note=subnoteup.getText().toString().trim();
                date=subdateup.getText().toString().trim();

                Data data = new Data(name, note, date ,post_key);
                mdatabase.child(post_key).setValue(data);

                dialog.dismiss();
            }
        });

        btnDeleteup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabase.child(post_key).removeValue();
                dialog.dismiss();
            }
        });
        dialog.show();
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