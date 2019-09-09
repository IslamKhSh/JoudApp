package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tremno.joudapp.Model.VouchersModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VouchersAdapter extends RecyclerView.Adapter<VouchersAdapter.ViewHolder> {

    private Context context;
    private ArrayList<VouchersModel> vouchers;

    public VouchersAdapter(Context context, ArrayList<VouchersModel> vouchers) {
        this.context = context;
        this.vouchers = vouchers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_voutcher, viewGroup, false);
        return new VouchersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.offerTv.setText(vouchers.get(position).getVouchersDetail());



    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.offerTv)
        TextView offerTv;

        @BindView(R.id.imageIv)
        ImageView imageIv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
