package com.example.crodv01b.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.crodv01b.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    @Override
    public void onStart() {
        super.onStart();



       // FirebaseUser currentUser = mAuth.getCurrentUser();
       // Snackbar.make(findViewById(android.R.id.content),"вход frnbd", Snackbar.LENGTH_SHORT).show();

        //обновления интерфейса
    }

}
