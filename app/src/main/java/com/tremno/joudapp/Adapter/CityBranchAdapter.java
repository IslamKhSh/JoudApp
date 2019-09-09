package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tremno.joudapp.Activity.CompanyDetailsActivity;
import com.tremno.joudapp.Model.BranchInCityModel;
import com.tremno.joudapp.Model.CityBranchModel;
import com.tremno.joudapp.Model.FaqModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityBranchAdapter extends RecyclerView.Adapter<CityBranchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CityBranchModel> city;

    public CityBranchAdapter(Context context, ArrayList<CityBranchModel> city) {
        this.context = context;
        this.city = city;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_city_branch, viewGroup, false);
        return new CityBranchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cityTv.setText(city.get(position).getCity());


    }

    @Override
    public int getItemCount() {
        return city.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.cityTv)
        TextView cityTv;

        @BindView(R.id.cityArrowUpBtn)
        ImageView cityArrowUpBtn;

        @BindView(R.id.cityArrowDownBtn)
        ImageView cityArrowDownBtn;

        @BindView(R.id.branchesRv)
        RecyclerView branchesRv;

        BranchInCityAdapter adapterBranch;
        ArrayList<BranchInCityModel> Branch;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Branch = new ArrayList<>();
            Branch.add(new BranchInCityModel("mohandsin"));
            Branch.add(new BranchInCityModel("doki"));


            adapterBranch = new BranchInCityAdapter(context, Branch);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
            branchesRv.setAdapter(adapterBranch);
            branchesRv.setLayoutManager(layoutManager2);
        }

        @OnClick(R.id.cityArrowUpBtn)
        void cityArrowUpBtn() {
            branchesRv.setVisibility(View.GONE);
            cityArrowDownBtn.setVisibility(View.VISIBLE);
            cityArrowUpBtn.setVisibility(View.GONE);
        }

        @OnClick(R.id.cityArrowDownBtn)
        void cityArrowDownBtn() {
            branchesRv.setVisibility(View.VISIBLE);
            cityArrowUpBtn.setVisibility(View.VISIBLE);
            cityArrowDownBtn.setVisibility(View.GONE);
        }
    }
}



