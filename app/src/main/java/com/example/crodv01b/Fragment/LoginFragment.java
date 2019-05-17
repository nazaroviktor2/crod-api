package com.example.crodv01b.Fragment;


import android.content.Intent;
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

import com.example.crodv01b.Activity.NavigationActivity;
import com.example.crodv01b.R;
import com.example.crodv01b.Activity.RegActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class LoginFragment extends Fragment {
    private EditText    email;
    private EditText    pass;
    private Button      btn_reg;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        email   = view.findViewById(R.id.login_editText_email);
        pass    = view.findViewById(R.id.login_editText_pass);
        btn_reg = view.findViewById(R.id.login_btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {// переход на регистрацию
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), RegActivity.class));

                            }
        });
        Button btn_login=view.findViewById(R.id.login_btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {//авторизация
            @Override
            public void onClick(View v) {
                if (email.getText().length()!=0&&pass.getText().length()!=0) {//запонины ли поля
                    singing(email.getText().toString(), pass.getText().toString());
                }
                else {
                    Snackbar.make(getView(), "Видите почту и пароль ", Snackbar.LENGTH_SHORT).show();
                }
                }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

      currentUser = mAuth.getCurrentUser();

      if (currentUser!=null){
          startActivity(new Intent(getActivity().getApplicationContext(), NavigationActivity.class));

      }
      else {
          Snackbar.make(getView(),"Авторизируйтесь",Snackbar.LENGTH_SHORT);
      }


    }
    public void singing(String email , String pass){//запрос к базе
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar.make(getView(),"Вход выполнин", Snackbar.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity().getApplicationContext(),NavigationActivity.class));
            }
                else {
                    Snackbar.make(getView(),"Не верный логин или пароль ",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
