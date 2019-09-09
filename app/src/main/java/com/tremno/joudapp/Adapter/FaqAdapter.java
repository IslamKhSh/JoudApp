package com.tremno.joudapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tremno.joudapp.Model.FaqModel;
import com.tremno.joudapp.Model.VouchersModel;
import com.tremno.joudapp.R;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<FaqModel> Faq;

    public FaqAdapter(Context context, ArrayList<FaqModel> Faq) {
        this.context = context;
        this.Faq = Faq;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_faq, viewGroup, false);
        return new FaqAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  final int position) {
        holder.questionTv.setText(Faq.get(position).getQuestion());
        holder.answerTv.setText(Faq.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        return Faq.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.questionTv)
        TextView questionTv;

        @BindView(R.id.answerTv)
        TextView answerTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
