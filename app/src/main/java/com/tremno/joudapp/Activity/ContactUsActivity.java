package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tremno.joudapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends AppCompatActivity {
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.emailTv)
    TextView emailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.twitterBtn)
    void twitterBtn() {

    }

    @OnClick(R.id.facebookBtn)
    void facebookBtn() {

    }

    @OnClick(R.id.instaBtn)
    void instaBtn() {

    }
}
