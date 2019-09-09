package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.tremno.joudapp.Adapter.MainAdapter;
import com.tremno.joudapp.Model.UserModel;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.Utils.Constants;
import com.tremno.joudapp.Model.OfferModel;
import com.tremno.joudapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    //init the views
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.navView)
    NavigationView nav_view;
    @BindView(R.id.profileIv)
    CircleImageView profileIv;
    @BindView(R.id.profileVisitorIv)
    CircleImageView profileVisitorIv;
    @BindView(R.id.profileTv)
    TextView profileTv;
    @BindView(R.id.myAccountBtn)
    FrameLayout myAccountBtn;
    @BindView(R.id.SubscribeBtn)
    FrameLayout SubscribeBtn;
    String visitor;
    @BindView(R.id.signOutBtn)
    LinearLayout signOutBtn;
    @BindView(R.id.cardsBtn)
    FrameLayout cardsBtn;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.offersRv)
    RecyclerView offersRv;


    MainAdapter adapter;
    ArrayList<OfferModel> offers;

    private String token;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife
        ButterKnife.bind(this);

        visitor = getIntent().getStringExtra(Constants.VISITOR_TAG);
        if (visitor != null) {
            if (visitor.equals(Constants.VISITOR_TAG)) {
                profileTv.setText(getString(R.string.visitor));
                profileIv.setVisibility(View.GONE);
                profileVisitorIv.setVisibility(View.VISIBLE);
                myAccountBtn.setVisibility(View.GONE);
                SubscribeBtn.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
            }
        }

        offers = new ArrayList<>();
        offers.add(new OfferModel("Ezaby pharmacy", "Get 5% discount when you use Joud card"));
        offers.add(new OfferModel("Ezaby pharmacy", "Get 5% discount when you use Joud card"));
        offers.add(new OfferModel("Ezaby pharmacy", "Get 5% discount when you use Joud card"));

        adapter = new MainAdapter(MainActivity.this, offers);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        offersRv.setAdapter(adapter);
        offersRv.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        id = (intent.getIntExtra("id", 0));
        token = intent.getStringExtra("token");
        System.out.println(id);
        System.out.println(token);

        showUserData();


    }

    //open navigation Button action
    @OnClick(R.id.menuBtn)
    void open_navigation() {

        drawer_layout.openDrawer(Gravity.START);
    }

    @OnClick(R.id.purchaseVoutcherBtn)
    void purchaseVoutcherBtn() {
        startActivity(new Intent(MainActivity.this, VouchersCategoriesActivity.class));

    }

    @OnClick(R.id.storesBtn)
    void storesBtn() {
        startActivity(new Intent(MainActivity.this, StoresActivity.class));
    }

    @OnClick(R.id.myAccountBtn)
    void myAccountBtn() {

        Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("id", id);
        startActivity(intent);


    }

    @OnClick(R.id.cardsBtn)
    void cardsBtn() {

        Intent intent = new Intent(MainActivity.this, CardsActivity.class);
        intent.putExtra("token", token);

        if (visitor != null) {
            intent.putExtra(Constants.VISITOR_TAG, Constants.VISITOR_TAG);
        }
        startActivity(intent);


    }

    @OnClick(R.id.languageBtn)
    void languageBtn() {
        startActivity(new Intent(MainActivity.this, ChangeLanguageActivity.class));
    }

    @OnClick(R.id.faqBtn)
    void faqBtn() {
        startActivity(new Intent(MainActivity.this, FAQActivity.class));

    }

    @OnClick(R.id.contactUsBtn)
    void contactUsBtn() {
        startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
    }

    @OnClick(R.id.aboutUsBtn)
    void aboutUsBtn() {
        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

    }

    @OnClick(R.id.termsAndConditionsBtn)
    void termsAndConditionsBtn() {
        startActivity(new Intent(MainActivity.this, TermAndConditionsActivity.class));
    }

    @OnClick(R.id.signOutBtn)
    void signOutBtn() {
        startActivity(new Intent(MainActivity.this, CheckLoginActivity.class));

    }

    public void showUserData() {

        Intent intent = getIntent();
        id = (intent.getIntExtra("id", 0));
        token = intent.getStringExtra("token");

        String url = Constants.BASE_URL + "user/profile/" + id;

        //the body
        JSONObject object = new JSONObject();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {

                        JSONObject data = response.getJSONObject("data");
                        profileTv.setText(data.getString("name"));
                        Log.d(Constants.TAG, data.getString("image"));

                        Glide.with(MainActivity.this).load(Constants.IMAGE_URL + data.getString("image")).into(profileIv);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Constants.TAG, "error ----->   " + error.toString());

            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsObjRequest);

    }
}

