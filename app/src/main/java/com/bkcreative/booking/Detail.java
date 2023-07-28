package com.bkcreative.booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Detail extends AppCompatActivity{
    DatabaseReference myRef;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    String destinasi_key = "";
    String destinasi_key_new ="";
    private String Destinasi;

    ImageView Gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gambar = findViewById(R.id.gambar_detail);
        getNimLokal();
        String destinasi = destinasi_key;
        Log.v("detail",destinasi);

        if (destinasi_key.equals("Bromo")) {
            Gambar.setImageResource(R.drawable.bromo_detail);
        }
        if (destinasi_key.equals("Watu Karung")) {
            Gambar.setImageResource(R.drawable.watu_karung_detail);

        }
        if (destinasi_key.equals("Bukit")) {
            Gambar.setImageResource(R.drawable.bukit_detail);
        }

        setContentView(R.layout.detail);


        ImageView back = findViewById(R.id.back);
        TextView booknow = findViewById(R.id.booknow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail.this, Home.class);
                startActivity(intent);
                finish();

            }
        });

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tujuan = "Bromo";
                final String lokasi = "Probolinggo, Jawa timur";
                final String harga   = "60K";

                myRef = FirebaseDatabase.getInstance().getReference("Pesanan").child("nama");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("tujuan").setValue(tujuan);
                        dataSnapshot.getRef().child("lokasi").setValue(lokasi);
                        dataSnapshot.getRef().child("harga").setValue(harga);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(Detail.this,Bookmark.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getNimLokal(){
        SharedPreferences sharedPreferences= getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
        destinasi_key_new = sharedPreferences.getString(destinasi_key, "");

    }
}