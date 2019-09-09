package com.tremno.joudapp.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;

import com.tremno.joudapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivationActivity extends AppCompatActivity {


    @BindView(R.id.verifyCodeEt)
    EditText verifyCodeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_activation);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /**
     * close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.submitBtn)
    void submitBtn() {

    }


}

