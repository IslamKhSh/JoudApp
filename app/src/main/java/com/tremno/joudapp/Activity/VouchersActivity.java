package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tremno.joudapp.Adapter.MainAdapter;
import com.tremno.joudapp.Adapter.VouchersAdapter;
import com.tremno.joudapp.Model.OfferModel;
import com.tremno.joudapp.Model.VouchersModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VouchersActivity extends AppCompatActivity {

    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.voutcherRv)
    RecyclerView voutcherRv;

    VouchersAdapter adapter;
    ArrayList<VouchersModel> Vouchers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers);
        //ButterKnife
        ButterKnife.bind(this);


        Vouchers = new ArrayList<>();
        Vouchers.add(new VouchersModel("Get 5% discount when you use Joud card"));
        Vouchers.add(new VouchersModel( "Get 5% discount when you use Joud card"));
        Vouchers.add(new VouchersModel( "Get 5% discount when you use Joud card"));

        adapter = new VouchersAdapter(VouchersActivity.this, Vouchers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(VouchersActivity.this);
        voutcherRv.setAdapter(adapter);
        voutcherRv.setLayoutManager(layoutManager);
    }


    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }
}
