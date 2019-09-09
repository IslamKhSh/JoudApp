package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tremno.joudapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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
}
