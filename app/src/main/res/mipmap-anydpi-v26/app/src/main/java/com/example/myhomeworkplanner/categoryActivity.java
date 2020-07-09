package com.example.myhomeworkplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myhomeworkplanner.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;

public class categoryActivity extends AppCompatActivity {

    TextView questionTxt,textView4;
    Button b1,b2,b3,b4;
    int correct=0;
    int wrong=0;
    int total=0;

    int computerCount=0;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        b1 = (Button) findViewById(R.id.OptionA);
        b2 = (Button) findViewById(R.id.OptionB);
        b3 = (Button) findViewById(R.id.OptionC);
        b4 = (Button) findViewById(R.id.OptionD);
        questionTxt = (TextView) findViewById(R.id.Questions);
        textView4 =(TextView)findViewById(R.id.textView4);
        updateQuestion();
        reverseTimer(60,textView4);

    }

    public void updateQuestion()
    {
        String db;
        computerCount++;
        if(computerCount>10)
        {
            Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(categoryActivity.this,ResultActivity.class);
            myIntent.putExtra("total",String.valueOf(total));
            myIntent.putExtra("correct",String.valueOf(correct));
            myIntent.putExtra("incorrect",String.valueOf(wrong));
            startActivity(myIntent);
        }
        else
        {
            Intent i = getIntent();
            db = i.getStringExtra("db");
            int x = Integer.parseInt(db);
            switch(x)
            {
                case 0:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT1").child("Questions").child(String.valueOf(computerCount));
                    break;
                case 1:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT2").child("C++Questions").child(String.valueOf(computerCount));
                    break;
                case 2:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT3").child("JavaQuestions").child(String.valueOf(computerCount));
                    break;
                case 3:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT4").child("DbmsQuestions").child(String.valueOf(computerCount));
                    break;
                case 4:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT5").child("WebQuestions").child(String.valueOf(computerCount));
                    break;
                case 5:
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Quiz").child("Categories").child("CAT6").child("ComiQuestions").child(String.valueOf(computerCount));
                    break;

            }

            total++;
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    questionTxt.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());


                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b1.getText().toString().equals(question.answer))
                            {
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                correct = correct +1;
                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                                wrong = wrong+1;

                                b1.setBackgroundColor(Color.RED);

                                if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }



                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b2.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b3.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b4.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 1500);




                            }
                        }
                    });


                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b2.getText().toString().equals(question.answer))
                            {
                                correct = correct + 1;
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b2.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 2000);
                            }
                            else
                            {
                                wrong++;
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                b2.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }



                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b2.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b3.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b4.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }
                        }
                    });


                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b3.getText().toString().equals(question.answer))
                            {
                                correct++;
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();

                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b3.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 2000);
                            }
                            else
                            {
                                wrong++;
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();


                                b3.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }




                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b2.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b3.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b4.setBackgroundColor(Color.parseColor("#F6FAFB"));

                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b4.getText().toString().equals(question.answer))
                            {
                                correct++;
                                Toast.makeText(getApplicationContext(),"Correct answer",Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b4.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 2000);
                            }
                            else
                            {
                                wrong++;
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();

                                b4.setBackgroundColor(Color.RED);

                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b2.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b3.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        b4.setBackgroundColor(Color.parseColor("#F6FAFB"));
                                        updateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    public void reverseTimer(int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(categoryActivity.this,ResultActivity.class);
                myIntent.putExtra("total",String.valueOf(total));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIntent);
            }
        }.start();
    }
}