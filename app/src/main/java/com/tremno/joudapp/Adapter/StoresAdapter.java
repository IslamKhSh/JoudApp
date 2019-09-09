package com.tremno.joudapp.Adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.tremno.joudapp.Model.StoresModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {
    private Context context;
    private ArrayList<StoresModel> stores;

    public StoresAdapter(Context context, ArrayList<StoresModel> stores) {
        this.context = context;
        this.stores = stores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_vouchers_stores, viewGroup, false);
        return new StoresAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.storesOrVouchersTV.setText(stores.get(position).getStores());

        holder.vouchersOrStoresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return stores.size();
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
