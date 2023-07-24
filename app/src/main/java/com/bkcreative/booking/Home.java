package com.bkcreative.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class Home extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ConstraintLayout des1 = findViewById(R.id.des1);
        ConstraintLayout des2 = findViewById(R.id.des2);
        ImageView bookmark = findViewById(R.id.bookmark);

        des1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the main activity after the delay
                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

        bookmark.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the main activity after the delay
                Intent intent = new Intent(Home.this, Bookmark.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });
    }



}