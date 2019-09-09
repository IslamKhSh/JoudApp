package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tremno.joudapp.Adapter.MainAdapter;
import com.tremno.joudapp.Adapter.StoresAdapter;
import com.tremno.joudapp.Model.OfferModel;
import com.tremno.joudapp.Model.StoresModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoresActivity extends AppCompatActivity {

    @BindView(R.id.storesRV)
    RecyclerView storesRV;

    StoresAdapter adapter;
    ArrayList<StoresModel> stores;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        //ButterKnife
        ButterKnife.bind(this);

        stores = new ArrayList<>();
        stores.add(new StoresModel("medical service"));
        stores.add(new StoresModel("fashion"));
        stores.add(new StoresModel("coffee"));

        adapter = new StoresAdapter(StoresActivity.this, stores);
        LinearLayoutManager layoutManager = new LinearLayoutManager(StoresActivity.this);
        storesRV.setAdapter(adapter);
        storesRV.setLayoutManager(layoutManager);

    }



    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

}
