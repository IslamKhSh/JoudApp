package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tremno.joudapp.Model.CardTypeModel;

import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderCardAdapter extends RecyclerView.Adapter<OrderCardAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<CardTypeModel> cardType;
    public  static int mSelectedItem = -1;

    public OrderCardAdapter(Context context, ArrayList<CardTypeModel> cardType) {
        this.context = context;
        this.cardType = cardType;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_type, viewGroup, false);
        return new OrderCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderCardAdapter.ViewHolder holder,final int position) {
        holder.radio_type.setText(cardType.get(position).getName()+"    "+cardType.get(position).getPrice()+" SR");
        holder.radio_type.setChecked(position == mSelectedItem);

    }

    @Override
    public int getItemCount() {
        return cardType.size();    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.radio_type)
        RadioButton radio_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(clickListener);
            radio_type.setOnClickListener(clickListener);
        }


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            }
        };

    }
}
