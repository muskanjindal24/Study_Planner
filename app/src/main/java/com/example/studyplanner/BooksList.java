package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

public class BooksList extends AppCompatActivity {

    PDFView myPDFviewer;
    TextView title, author;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
//ActionBar actionBar = getSupportActionBar();
        //if(actionBar!=null){
        //  actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);
        //}
        myPDFviewer = (PDFView)findViewById(R.id.pdfviewer);
        Intent intent = getIntent();
        String bookTitle = intent.getStringExtra("title");
        if(bookTitle.equals("Programming in ANSI C")){
            myPDFviewer.fromAsset("AnsiC.pdf").load();
            //  actionBar.setTitle(bookTitle);
        }
        if(bookTitle.equals("Object Oriented Programming with C++")){
            myPDFviewer.fromAsset("C++.pdf").load();
            //actionBar.setTitle(bookTitle);
        }
        if(bookTitle.equals("Java-The Complete Reference")){
            myPDFviewer.fromAsset("Java.pdf").load();
            //actionBar.setTitle(bookTitle);
        }
        if(bookTitle.equals("Web Engineering")){
            myPDFviewer.fromAsset("Web.pdf").load();
            //actionBar.setTitle(bookTitle);
        }
        if(bookTitle.equals("Computer Organization and Design Arm Edition")){
            myPDFviewer.fromAsset("COMI.pdf").load();
            //actionBar.setTitle(bookTitle);
        }
        if(bookTitle.equals("Fundamentals of Database System")){
            myPDFviewer.fromAsset("DBMS.pdf").load();
            //actionBar.setTitle(bookTitle);
        }
    }
}