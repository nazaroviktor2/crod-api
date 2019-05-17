package com.example.crodv01b.Fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.crodv01b.R;
import com.example.crodv01b.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class RegFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText name;
    EditText surname;
    EditText editText_email ;
    EditText editText_pass ;
    Button btn_reg ;
    public RegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.reg_EditText_name);
        surname = view.findViewById(R.id.reg_EditText_surname);
        editText_email = view.findViewById(R.id.reg_email);
       editText_pass = view.findViewById(R.id.reg_pass);

        btn_reg =view.findViewById(R.id.reg_btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_email.getText().length()!=0&&editText_pass.getText().length()!=0&&name.getText().length()!=0&&surname.getText().length()!=0) {


                    registr(editText_email.getText().toString(), editText_pass.getText().toString());
                }else
                    Snackbar.make(getView(),"Заполните все поля",Snackbar.LENGTH_SHORT).show();
                }
        });
        mAuth = FirebaseAuth.getInstance();


    }
    public void registr (String email , String password){

    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){


                mAuth = FirebaseAuth.getInstance();
                User user = new User(mAuth.getCurrentUser().getUid(),mAuth.getCurrentUser().getEmail(),
                name.getText().toString(), surname.getText().toString(),mAuth);
                user.CreateUserDB();
                Snackbar.make(getView(),"Пользователь зарегисрирован ", Snackbar.LENGTH_SHORT).show();
            }else {
                Snackbar.make(getView(),"Данный пользователь уже зарегистрирован ",Snackbar.LENGTH_SHORT).show();
            }
        }
    });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg, container, false);
    }

}
