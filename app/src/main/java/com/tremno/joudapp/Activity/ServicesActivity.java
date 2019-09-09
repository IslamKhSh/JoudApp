package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tremno.joudapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesActivity extends AppCompatActivity {

    @BindView(R.id.servicesRv)
    RecyclerView servicesRv;
    @BindView(R.id.titleTv)
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }
}
