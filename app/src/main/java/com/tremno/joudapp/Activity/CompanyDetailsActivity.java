package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tremno.joudapp.Adapter.BranchInCityAdapter;
import com.tremno.joudapp.Adapter.CityBranchAdapter;
import com.tremno.joudapp.Adapter.VouchersAdapter;
import com.tremno.joudapp.Model.BranchInCityModel;
import com.tremno.joudapp.Model.CityBranchModel;
import com.tremno.joudapp.Model.VouchersModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyDetailsActivity extends AppCompatActivity {
    @BindView(R.id.companyImgIv)
    ImageView companyImgIv;

    @BindView(R.id.offerTv)
    TextView offerTv;

    @BindView(R.id.cityRv)
    RecyclerView cityRv;


    CityBranchAdapter adapterCity;
    ArrayList<CityBranchModel> city;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        //ButterKnife
        ButterKnife.bind(this);

        city = new ArrayList<>();
        city.add(new CityBranchModel("cairo"));
        city.add(new CityBranchModel( "alex"));
        city.add(new CityBranchModel( "tanta"));

        adapterCity = new CityBranchAdapter(CompanyDetailsActivity.this, city);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CompanyDetailsActivity.this);
        cityRv.setAdapter(adapterCity);
        cityRv.setLayoutManager(layoutManager);



    }



    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }
}
