package com.example.crodv01b;

import android.app.Activity;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ResourceBundle;

public class User extends Activity {
    private String Uid;
    private String email;
    private String name;
    private String surname;
    private FirebaseAuth mAuth;
    private String tutor = "0";

    public User(String uid, String email, String name, String surname, FirebaseAuth mAuth) {
         this.Uid = uid;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.mAuth = mAuth;
    }


    public User() {
    }

    public void CreateUserDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("users");

       myRef.child(Uid).child("Email").setValue(email);
        myRef.child(Uid).child("Name").setValue(name);
        myRef.child(Uid).child("Surname").setValue(surname);
        myRef.child(Uid).child("Tutor").setValue(tutor);
        myRef.child(Uid).child("Smena").setValue(0);
        myRef.child(Uid).child("Koment").setValue(0);
      /*  myRef.child(Uid).child(getString(R.string.db_name)).setValue(name);
        myRef.child(Uid).child(getString(R.string.db_surnName)).setValue(surname);
        myRef.child(Uid).child(getString(R.string.db_tutor)).setValue(tutor);*/
        //myRef.child(Uid).child(getString(R.string.db_idSmenа)).setValue(0);
    }


}