package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tremno.joudapp.Utils.Constants;
import com.tremno.joudapp.R;
import com.tremno.joudapp.Utils.PreferenceUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckLoginActivity extends AppCompatActivity {

    PreferenceUtils pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);
        //ButterKnife
        ButterKnife.bind(this);

        pref = new PreferenceUtils(this);


        if (!pref.getUserToken().equals("0")) {

            Intent intent = new Intent(CheckLoginActivity.this, MainActivity.class);
            intent.putExtra("token",(pref.getUserToken()));
            intent.putExtra("id", pref.getpersonID());
            startActivity(intent);
        }

    }

    @OnClick(R.id.loginBtn)
    void loginBtn() {
        startActivity(new Intent(CheckLoginActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.signupBtn)
    void signupBtn() {
        startActivity(new Intent(CheckLoginActivity.this, SignupActivity.class));
    }

    @OnClick(R.id.visitorLoginBtn)
    void visitorLoginBtn() {
        Intent intent = new Intent(CheckLoginActivity.this, MainActivity.class);
        intent.putExtra(Constants.VISITOR_TAG, Constants.VISITOR_TAG);
        startActivity(intent);
    }
}
