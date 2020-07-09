package com.example.studyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FinalData extends AppCompatActivity {
    private Button next;
    private Button add;
    private EditText subj;
    private EditText rat;
    private TextView tv;
    private DatabaseReference mDatabase;
    List<String> sublst=new ArrayList<String>();
    List<Integer> ratlst=new ArrayList<Integer>();
    String strsub, strrat, tsub, thr;
    int cnt;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference("TotalSubj");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_data);

        tv=(TextView) findViewById(R.id.gotoMuskan);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(getApplicationContext(), Quiz.class);
                startActivity(i1);
            }
        });



        cnt=0;
        Intent i=getIntent();
        tsub=i.getStringExtra("hi");
        thr=i.getStringExtra("hello");


        mDatabase = FirebaseDatabase.getInstance().getReference();
        subj=findViewById(R.id.sub_name);
        rat=findViewById(R.id.ratings);



        tv=findViewById(R.id.tv);
        final int isub=Integer.parseInt(tsub);
        final int ihr=Integer.parseInt(thr);




        add= (Button) findViewById(R.id.add_database);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strsub=subj.getText().toString();
                strrat=rat.getText().toString();
                int rat=Integer.parseInt(strrat);
                ratlst.add((10-rat));
                cnt=cnt+1;
                if(cnt==isub){
                    add.setVisibility(view.INVISIBLE);
                    next.setVisibility(view.VISIBLE);
                }

                writeNewUser(tsub, strrat,cnt, strsub,"");
                Toast.makeText(FinalData.this, "Data Added", Toast.LENGTH_LONG).show();

            }
        });


        next= (Button) findViewById(R.id.final_show);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calc(isub);
                //int j=isub+ihr;
                //Toast.makeText(FinalData.this, String.valueOf(j), Toast.LENGTH_LONG).show();

                //intent1.putExtra("ji",tsub);
                /*for (int k = 1; k <= isub; k++) {
                    ref.child(String.valueOf(isub)).child(String.valueOf(k)).child("Rating").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String i = snapshot.getValue().toString();
                            int j = 0;
                            if (i != null) {
                                j = Integer.parseInt(i);
                            }
                            ratlst.add((10 - j));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }*/

                //Toast.makeText(FinalData.this, String.valueOf(ratlst.get(0)),Toast.LENGTH_LONG).show();


                int p=0;
                for(int w=0; w<isub; w++){
                    p+=ratlst.get(w);
                }


                for(int z=1; z<=isub; z++){
                    int l=ratlst.get(z-1);
                    int ans;
                    if(p!=0) {
                        ans = (l * ihr * 60) / p;
                    }
                    else{
                        ans=0;
                    }
                   ref.child(String.valueOf(isub)).child(String.valueOf(z)).child("Algo").setValue(String.valueOf(ans));

                    Intent intent1=new Intent(getApplicationContext(),Done.class);
                    intent1.putExtra("ji",tsub);
                    intent1.putExtra("pi",thr);
                    startActivity(intent1);
                    //Toast.makeText(FinalData.this, tsub,Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void calc(int isub) {
        for (int k = 1; k <= isub; k++) {
            ref.child(String.valueOf(isub)).child(String.valueOf(k)).child("Rating").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String i = snapshot.getValue().toString();
                    int j = 0;
                    if (i != null) {
                        j = Integer.parseInt(i);
                    }
                    ratlst.add((10 - j));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void writeNewUser(String tsub, String strrat, int i, String strsub,String algo) {
        ActualData ad= new ActualData(strrat,strsub,algo);


        mDatabase.child("TotalSubj").child(tsub).child(String.valueOf(i)).setValue(ad);
    }
}