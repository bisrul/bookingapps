package com.bkcreative.booking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText nameFill, emailFill, passwordFill;
    DatabaseReference myRef2;
    private Button register;
    private TextView login;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        nameFill = findViewById(R.id.namefill);
        emailFill = findViewById(R.id.emailfill);
        passwordFill = findViewById(R.id.passwordfill);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        String USERNAME_KEY = "usernamekey";
        String username_key = "";

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }



    private void registerUser() {
        final String name = nameFill.getText().toString().trim();
        final String email = emailFill.getText().toString().trim();
        final String password = passwordFill.getText().toString().trim();


        final String USERNAME_KEY = "usernamekey";
        final String username_key = "";

        if (name.isEmpty()) {
            nameFill.setError(getString(R.string.input_error_name));
            nameFill.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailFill.setError(getString(R.string.input_error_email_invalid));
            emailFill.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailFill.setError(getString(R.string.input_error_email_invalid));
            emailFill.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordFill.setError(getString(R.string.input_error_password));
            passwordFill.requestFocus();
            return;

        }
        if (password.length() < 6) {
            passwordFill.setError(getString(R.string.input_error_password_length));
            passwordFill.requestFocus();
            return;
        }

        final String text = emailFill.getText().toString().trim();
        Log.v("cobo",text);
        Query myRef = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("email").equalTo(text);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {
                if (dataSnapshot2.exists()) {
                    Toast toast = Toast.makeText(SignUp.this,
                            "Email Sudah Tertaftar", Toast.LENGTH_LONG);
                    toast.show();
                    emailFill.setError("Email Sudah Terdaftar");
                    emailFill.requestFocus();
                    register.setEnabled(true);
                    register.setText("Register");
                } else {

                    //menyimpan data local storage
                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username_key, email);
                    editor.apply();
                    //Simpan Database
                    myRef2 = FirebaseDatabase.getInstance().getReference("User").child(email);
                    myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.v("cobo", email + "  2");
                            Log.v("cobo", name + "  2");
                            dataSnapshot.getRef().child("name").setValue(name);
                            dataSnapshot.getRef().child("email").setValue(email);
                            dataSnapshot.getRef().child("password").setValue(password);
                            Toast.makeText(SignUp.this, "Register Succesfully", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
            }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                registerUser();
                break;
        }

        if (v==login){
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }
}




// Now you can check various network capabilities using the networkCapabilities object.



//                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//                        NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//                                if((wifi != null & datac != null) && (wifi.isConnected()| datac.isConnected())){
//                            //pindah activity
//                            Intent gotoregistwo=new Intent(Signup.this,Home.class);
//                            startActivity(gotoregistwo);
//                            finishAffinity();
//                        }else
//                        { Toast toa = Toast.makeText(Signup.this,
//                                "Tidak ada koneksi, pastikan koneksi anda terhubung dan tidak buruk", Toast.LENGTH_LONG);
//                            toa.show();
//                            register.setEnabled(true);
//                            register.setText("Lanjut");
//                        }
//
//                    }
//
//                }
