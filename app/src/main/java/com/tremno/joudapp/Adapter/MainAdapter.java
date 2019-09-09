package com.tremno.joudapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.tremno.joudapp.Activity.CompanyDetailsActivity;
import com.tremno.joudapp.Model.OfferModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Context context;
    private ArrayList<OfferModel> offers;

    public MainAdapter(Context context, ArrayList<OfferModel> offers) {
        this.context = context;
        this.offers = offers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_offer_main, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.companyTv.setText(offers.get(position).getCompanyName());
        holder.offerTv.setText(offers.get(position).getOffer());
//        if (context != null)
//            Glide.with(context).load(offers.get(position)).into(holder.offerImageIv);

        holder.itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompanyDetailsActivity.class);
                intent.putExtra("offer",offers.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.companyTv)
        TextView companyTv;
        @BindView(R.id.offerTv)
        TextView offerTv;
        @BindView(R.id.offerImageIv)
        ImageView offerImageIv;
        @BindView(R.id.itemBtn)
        FrameLayout itemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
