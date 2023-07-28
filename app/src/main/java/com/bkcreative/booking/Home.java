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
    DatabaseReference myRef, reference;
    TextView UserName;
    ConstraintLayout Bromo, WatuKarung, Bukit;
    ImageView bookmark;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    String destinasi_key = "";
    String destinasi_key_new ="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        UserName =findViewById(R.id.name);
        getNimLokal();
        getPlace();


        myRef = FirebaseDatabase.getInstance().getReference().
                child("Users").child(username_key_new);
        myRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("nama").getValue(String.class);
                UserName.setText(username);
                Log.v("cobo",username_key_new);
                //Log.v("cobo",username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Bromo = findViewById(R.id.bromo);
        WatuKarung = findViewById(R.id.watukarung);
        Bukit = findViewById(R.id.bukit);

        bookmark = findViewById(R.id.bookmark);


//      Clickable Gambar Destinasi
        Bromo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String destinasi = "Bromo";
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( destinasi_key , destinasi);
                editor.apply();
                Log.v("cobo",destinasi);
                // Start the main activity after the delay
                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

        WatuKarung.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String destinasi = "Watu Karung";
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(destinasi_key, destinasi);
                editor.apply();
                Log.v("cobo",destinasi);

                // Start the main activity after the delay

                Intent intent = new Intent(Home.this, Detail.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

        Bukit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String destinasi = "Bukit";
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(destinasi_key, destinasi);
                editor.apply();
                // Start the main activity after the delay
                Log.v("cobo",destinasi);

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

    private void getPlace() {


    }

    //    ambil data lokal
    public void getNimLokal(){
        SharedPreferences sharedPreferences= getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
        destinasi_key = sharedPreferences.getString(destinasi_key_new, "");

    }





}