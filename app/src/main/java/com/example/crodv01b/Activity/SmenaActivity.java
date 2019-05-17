package com.example.crodv01b.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crodv01b.Fragment.IdSmenaFragment;
import com.example.crodv01b.Fragment.SmenaFragment;
import com.example.crodv01b.R;

public class SmenaActivity extends AppCompatActivity implements IdSmenaFragment.OnClickFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smena);
    }

    @Override
    public void onClick() {
        getSupportFragmentManager().beginTransaction().remove(new IdSmenaFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.smena_fragment,new SmenaFragment()).commit();
    }
}
