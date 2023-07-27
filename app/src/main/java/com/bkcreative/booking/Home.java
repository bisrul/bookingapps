package com.bkcreative.booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends AppCompatActivity{
    DatabaseReference myRef;
    TextView UserName;
    ConstraintLayout des1, des2;
    ImageView bookmark;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        UserName =findViewById(R.id.name);
        getNimLokal();

        myRef = FirebaseDatabase.getInstance().getReference().
                child("Users").child(username_key_new);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("nama").getValue(String.class);
                UserName.setText(username);
                //Log.v("cobo",username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        des1 = findViewById(R.id.des1);
        des2 = findViewById(R.id.des2);
        bookmark = findViewById(R.id.bookmark);


//      Clickable Gambar Destinasi
        des1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the main activity after the delay
                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

        des2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the main activity after the delay
                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });


//      Button navbar
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

//    simpan lokal
    public void getNimLokal(){
        SharedPreferences sharedPreferences= getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }

}