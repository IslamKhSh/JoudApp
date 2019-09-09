package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tremno.joudapp.Model.BranchInCityModel;
import com.tremno.joudapp.Model.CityBranchModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BranchInCityAdapter extends RecyclerView.Adapter<BranchInCityAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BranchInCityModel> branch;

    public BranchInCityAdapter(Context context, ArrayList<BranchInCityModel> branch) {
        this.context = context;
        this.branch = branch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_branches_in_city, viewGroup, false);
        return new BranchInCityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.branchTv.setText(branch.get(position).getBranch());

    }

    @Override
    public int getItemCount() {
        return branch.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.branchTv)
        TextView branchTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
