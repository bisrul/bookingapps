package com.bkcreative.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView signup = findViewById(R.id.signup);
        Button login = findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Start the main activity after the delay
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        });

    }
}
