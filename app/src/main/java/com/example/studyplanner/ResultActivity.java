package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView t2_correct;
    String total,correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        t2_correct = (TextView)findViewById(R.id.correct);
        Intent i = getIntent();
        total = i.getStringExtra("total");
        correct = i.getStringExtra("correct");
        setValues();
    }
    private void setValues()
    {
        t2_correct.setText(correct);
    }
}