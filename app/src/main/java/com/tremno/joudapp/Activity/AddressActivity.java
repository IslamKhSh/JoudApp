package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tremno.joudapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        //ButterKnife
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backBtn)
    void backBtn() {
finish();
    }

    @OnClick(R.id.cityArrowUpBtn)
    void cityArrowUpBtn() {

    }

    @OnClick(R.id.cityArrowDownBtn)
    void cityArrowDownBtn() {

    }

    @OnClick(R.id.regionArrowUpBtn)
    void regionArrowUpBtn() {

    }

    @OnClick(R.id.regionArrowDownBtn)
    void regionArrowDownBtn() {

    }

    @OnClick(R.id.searchBtn)
    void searchBtn() {

    }
}
