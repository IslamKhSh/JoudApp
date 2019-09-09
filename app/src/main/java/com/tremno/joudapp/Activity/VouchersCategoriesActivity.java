package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tremno.joudapp.Adapter.VoucherssCategoriesAdapter;
import com.tremno.joudapp.Model.VoucherssCategoriesModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VouchersCategoriesActivity extends AppCompatActivity {

    @BindView(R.id.vouchersRV)
    RecyclerView vouchersRV;

    VoucherssCategoriesAdapter adapter;
    ArrayList<VoucherssCategoriesModel> Vouchers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voutchers_categories);
        //ButterKnife
        ButterKnife.bind(this);

        Vouchers = new ArrayList<>();
        Vouchers.add(new VoucherssCategoriesModel("medical service"));
        Vouchers.add(new VoucherssCategoriesModel("fashion"));
        Vouchers.add(new VoucherssCategoriesModel("coffee"));

        adapter = new VoucherssCategoriesAdapter(VouchersCategoriesActivity.this, Vouchers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(VouchersCategoriesActivity.this);
        vouchersRV.setAdapter(adapter);
        vouchersRV.setLayoutManager(layoutManager);
    }


    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

}
