package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tremno.joudapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeLanguageActivity extends AppCompatActivity {
    @BindView(R.id.checkArabicIv)
    ImageView checkArabicIv;

    @BindView(R.id.checkEnglishIv)
    ImageView checkEnglishIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.checkArabicBtn)
    void checkArabicBtn() {
        checkArabicIv.setVisibility(View.VISIBLE);
        checkEnglishIv.setVisibility(View.GONE);
    }

    @OnClick(R.id.checkEnglishBtn)
    void checkEnglishBtn() {
        checkEnglishIv.setVisibility(View.VISIBLE);
        checkArabicIv.setVisibility(View.GONE);
    }
}
