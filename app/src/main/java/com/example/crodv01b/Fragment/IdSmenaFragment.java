package com.example.crodv01b.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.crodv01b.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class IdSmenaFragment extends Fragment {
private EditText eT_idSmena;
private Button btn;
private FirebaseDatabase database;
private FirebaseUser currUser;
    public IdSmenaFragment() {
        // Required empty public constructor
    }
public interface OnClickFragment{
        void onClick();
}

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        eT_idSmena = view.findViewById(R.id.idSmena_editText_id);
        btn = view.findViewById(R.id.idSmena_btn);
        database = FirebaseDatabase.getInstance();
        currUser = FirebaseAuth.getInstance().getCurrentUser();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eT_idSmena.length()!=0){

                    final String idSmena = eT_idSmena.getText().toString();
                     DatabaseReference refSmena= database.getReference("Smena");
                  
                    // refSmena.orderByChild("id").equalTo(idSmena).addListenerForSingleValueEvent(new ValueEventListener()
                    refSmena.orderByKey().equalTo(idSmena).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("Test", "Datasnap Shots: " + dataSnapshot.getValue());
                            if(dataSnapshot.exists()){
                                DatabaseReference refUser = FirebaseDatabase.getInstance().getReference("users").child(currUser.getUid());
                                refUser.child("Smena").setValue(idSmena);
                               ((OnClickFragment)getActivity()).onClick();

                            }
                            else {
                                Snackbar.make(view ,"Данная смена не найдина",Snackbar.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else {
                    Snackbar.make(view,"Заполните поле",Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_id_smena, container, false);
    }

}
