package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tremno.joudapp.R;
import com.tremno.joudapp.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardsActivity extends AppCompatActivity {
    @BindView(R.id.cardNumEt)
    EditText cardNumEt;
    @BindView(R.id.SubDateEt)
    EditText SubDateEt;
    @BindView(R.id.expireSubEt)
    EditText expireSubEt;
    @BindView(R.id.paumentSystemEt)
    EditText paumentSystemEt;
    @BindView(R.id.dataLayout)
    LinearLayout dataLayout;
    @BindView(R.id.arrowDownDataBtn)
    ImageView arrowDownDataBtn;
    @BindView(R.id.arrowUpDataBtn)
    ImageView arrowUpDataBtn;

    private String token;
    private String visitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        //ButterKnife
        ButterKnife.bind(this);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        visitor = intent.getStringExtra(Constants.VISITOR_TAG);

    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.orderCardBtn)
    void orderCardBtn() {


        Intent intent = new Intent(CardsActivity.this, OrderCardActivity.class);
        intent.putExtra("token", token);
        if (visitor != null) {
            intent.putExtra(Constants.VISITOR_TAG, Constants.VISITOR_TAG);
        }

        startActivity(intent);

    }

    @OnClick(R.id.activateCardBtn)
    void activateCardBtn() {
        Intent intent=new Intent(CardsActivity.this, ActivateCardActivity.class);
        intent.putExtra(Constants.TOKEN,token);
        startActivity(intent);

    }

    @OnClick(R.id.turnOffCardBtn)
    void turnOffCardBtn() {
        startActivity(new Intent(CardsActivity.this, TurnOffCardActivity.class));

    }

    @OnClick(R.id.arrowDownDataBtn)
    void arrowDownDataBtn() {
        dataLayout.setVisibility(View.VISIBLE);
        arrowDownDataBtn.setVisibility(View.GONE);
        arrowUpDataBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.arrowUpDataBtn)
    void arrowUpDataBtn() {
        dataLayout.setVisibility(View.GONE);
        arrowDownDataBtn.setVisibility(View.VISIBLE);
        arrowUpDataBtn.setVisibility(View.GONE);
    }

}
