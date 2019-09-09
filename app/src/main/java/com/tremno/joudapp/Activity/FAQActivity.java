package com.tremno.joudapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tremno.joudapp.Adapter.FaqAdapter;
import com.tremno.joudapp.Adapter.MainAdapter;
import com.tremno.joudapp.Model.FaqModel;
import com.tremno.joudapp.Model.OfferModel;
import com.tremno.joudapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FAQActivity extends AppCompatActivity {

    FaqAdapter adapter;
    ArrayList<FaqModel> Faq;

    @BindView(R.id.faqRv)
    RecyclerView faqRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        //ButterKnife
        ButterKnife.bind(this);

        Faq = new ArrayList<>();
        Faq.add(new FaqModel("Ezaby pharmacy??", "Get 5% discount when you use Joud card"));
        Faq.add(new FaqModel("Ezaby pharmacy??", "Get 5% discount when you use Joud card"));
        Faq.add(new FaqModel("Ezaby pharmacy??", "Get 5% discount when you use Joud card"));

        adapter = new FaqAdapter(FAQActivity.this, Faq);
        LinearLayoutManager layoutManager = new LinearLayoutManager(FAQActivity.this);
        faqRv.setAdapter(adapter);
        faqRv.setLayoutManager(layoutManager);

    }



    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }
}
