package com.bkcreative.booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText InputUsername, InputPass;
    private Button Login;
    private TextView Signup;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        FirebaseApp.initializeApp(this);


        InputUsername = findViewById(R.id.emailfill);
        InputPass = findViewById(R.id.passwordfill);
        Signup = findViewById(R.id.signup);
        Login = findViewById(R.id.login);

        Signup.setOnClickListener(this);
        Login.setOnClickListener(this);
    }

    private void Userlogin(){
        final String nama = InputUsername.getText().toString().trim();
        final String pass = InputPass.getText().toString().trim();
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.v("cobo",nama);

        reference = FirebaseDatabase.getInstance().getReference().
                child("Users").child(nama);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // ambil data password dari firbase
                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();
                    Log.v("cobo", passwordFromFirebase+"password");
                    //Validasi password dengan firebase
                    if (pass.equals(passwordFromFirebase)) {
                        //loading
                        Login.setEnabled(false);
                        Login.setText("Wait...");
                            //Save data di lokal
                            SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(username_key, InputUsername.getText().toString());
                            editor.apply();

                            Intent gotoprofil = new Intent(
                                    Login.this,Home.class);
                            startActivity(gotoprofil);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Password salah!", Toast.LENGTH_SHORT).show();
                            //loading
                            Login.setEnabled(true);
                            Login.setText("Sign In");
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email tidak ada!", Toast.LENGTH_SHORT).show();
                        //loading
                        Login.setEnabled(true);
                        Login.setText("Masuk");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


    @Override
    public void onClick(View view) {
        if (view == Login){
            Userlogin();
        }

        if (view == Signup){
            finish();
            startActivity(new Intent(this, SignUp.class));
        }

    }
    }