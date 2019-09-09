package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.tremno.joudapp.Adapter.OrderCardAdapter;
import com.tremno.joudapp.Model.CardTypeModel;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.R;
import com.tremno.joudapp.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderCardActivity extends AppCompatActivity {
    @BindView(R.id.NameEt)
    EditText NameEt;

    @BindView(R.id.phoneEt)
    EditText phoneEt;

    @BindView(R.id.emailEt)
    EditText emailEt;

    @BindView(R.id.passwordEt)
    EditText passwordEt;

    @BindView(R.id.carTypeRG)
    RadioGroup carTypeRG;

    @BindView(R.id.cardTypeRV)
    RecyclerView cardTypeRV;

    @BindView(R.id.visitorData)
    LinearLayout visitorData;


    @BindView(R.id.checkBankTransferBtn)
    FrameLayout checkBankTransferBtn;

    @BindView(R.id.checkCashBtn)
    FrameLayout checkCashBtn;

    @BindView(R.id.paymentArrowUpBtn)
    ImageView paymentArrowUpBtn;

    @BindView(R.id.paymentArrowDownBtn)
    ImageView paymentArrowDownBtn;

    @BindView(R.id.checkBankTransferIv)
    ImageView checkBankTransferIv;


    @BindView(R.id.checkcashIv)
    ImageView checkcashIv;

    String paymentMethod;




    ArrayList<CardTypeModel> CardTypes;
    OrderCardAdapter adapter;

    String token;

    String visitor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_card);
        //ButterKnife
        ButterKnife.bind(this);
        showCardType();
        visitor = getIntent().getStringExtra(Constants.VISITOR_TAG);
        if (visitor != null) {

            visitorData.setVisibility(View.VISIBLE);
            System.out.println(visitor);
        }

    }

    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.requestBtn)
    void requestBtn() {
        orderCard();
    }

    public void showCardType() {

        String url = Constants.BASE_URL + "card/types";
        JSONObject object = new JSONObject();
        try {

            object.put("X-Language", "ar");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {


                        CardTypes = new ArrayList<>();
                        JSONArray data = response.getJSONArray("data");

                        if (data != null) {
                            for (int i = 0; i < data.length(); i++) {

                                CardTypeModel card = new CardTypeModel();

                                card.setName((String) data.getJSONObject(i).get("name"));
                                card.setPrice(data.getJSONObject(i).get("price").toString());
                                card.setId((int) data.getJSONObject(i).get("id"));

                                CardTypes.add(card);

                            }
                            adapter = new OrderCardAdapter(OrderCardActivity.this, CardTypes);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(OrderCardActivity.this);
                            cardTypeRV.setAdapter(adapter);
                            cardTypeRV.setLayoutManager(layoutManager);

                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Language", "ar");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(OrderCardActivity.this).addToRequestQueue(jsObjRequest);


    }

    public void orderCard() {
        Intent intent = getIntent();
        if (visitor == null) {

            token = intent.getStringExtra("token");
        }
        String url = Constants.BASE_URL + "card/order";


        CardTypeModel cardType = CardTypes.get(OrderCardAdapter.mSelectedItem);

        JSONObject object = new JSONObject();
        try {

            if (visitor != null) {

                object.put("name", NameEt.getText().toString());
                object.put("email", emailEt.getText().toString());
                object.put("mobile", phoneEt.getText().toString());
                object.put("password", passwordEt.getText().toString());
            }
            object.put("type_id", cardType.getId());
            object.put("amount", cardType.getPrice());
            object.put("payment_method",paymentMethod);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {

                        if (visitor != null) {
                            Toast.makeText(OrderCardActivity.this, "تم الطلب بنجاح وانشاء حساب لك", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(OrderCardActivity.this, CardsActivity.class));

                        }

                        Toast.makeText(OrderCardActivity.this, "تم الطلب بنجاح", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(OrderCardActivity.this, CardsActivity.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                if (visitor == null) {
                    headers.put("X-Language", "ar");
                    headers.put("Authorization", "Bearer " + token);
                }
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(OrderCardActivity.this).addToRequestQueue(jsObjRequest);


    }

    @OnClick(R.id.paymentArrowDownBtn)
    void paymentArrowDownBtn() {
        paymentArrowUpBtn.setVisibility(View.VISIBLE);
        paymentArrowDownBtn.setVisibility(View.GONE);
        checkBankTransferBtn.setVisibility(View.VISIBLE);
        checkCashBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.paymentArrowUpBtn)
    void paymentArrowUpBtn() {
        paymentArrowDownBtn.setVisibility(View.VISIBLE);
        paymentArrowUpBtn.setVisibility(View.GONE);
        checkBankTransferBtn.setVisibility(View.GONE);
        checkCashBtn.setVisibility(View.GONE);
    }

    @OnClick(R.id.checkBankTransferBtn)
    void checkBankTransferBtn() {
        checkBankTransferIv.setVisibility(View.VISIBLE);
        checkcashIv.setVisibility(View.GONE);
        paymentMethod="BankTransfer";
    }

    @OnClick(R.id.checkCashBtn)
    void checkCashBtn() {
        checkcashIv.setVisibility(View.VISIBLE);
        checkBankTransferIv.setVisibility(View.GONE);
        paymentMethod="cashe";
    }
}










