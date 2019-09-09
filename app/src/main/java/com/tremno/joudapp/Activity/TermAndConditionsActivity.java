package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tremno.joudapp.R;

import butterknife.ButterKnife;

public class TermAndConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_conditions);
        //ButterKnife
        ButterKnife.bind(this);
    }
}
