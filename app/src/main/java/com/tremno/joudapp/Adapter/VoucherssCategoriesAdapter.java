package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.tremno.joudapp.Activity.CompanyDetailsActivity;
import com.tremno.joudapp.Activity.VouchersActivity;
import com.tremno.joudapp.Model.VoucherssCategoriesModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoucherssCategoriesAdapter extends RecyclerView.Adapter<VoucherssCategoriesAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<VoucherssCategoriesModel> vouchers;

    public VoucherssCategoriesAdapter(Context context, ArrayList<VoucherssCategoriesModel> vouchers) {
        this.context = context;
        this.vouchers = vouchers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_vouchers_stores, viewGroup, false);
        return new VoucherssCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.storesOrVouchersTV.setText(vouchers.get(position).getVoucher());

        holder.vouchersOrStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VouchersActivity.class);
                intent.putExtra("vouchers",vouchers.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vouchersOrStoresBtn)
        FrameLayout vouchersOrStoresBtn;
        @BindView(R.id.storesOrVouchersTV)
        TextView storesOrVouchersTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
