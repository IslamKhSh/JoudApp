package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tremno.joudapp.Model.UserModel;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.R;
import com.tremno.joudapp.Utils.Constants;
import com.tremno.joudapp.Utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivateCardActivity extends AppCompatActivity {
    @BindView(R.id.cardNumEt)
    EditText cardNumEt;

    @BindView(R.id.phoneNumEt)
    EditText phoneNumEt;

    String cardNum, phoneNum ;

    PreferenceUtils pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_card);
        //ButterKnife
        ButterKnife.bind(this);

        pref = new PreferenceUtils(this);

//        String token = pref.getUserToken();


//        Log.d(Constants.TAG, "----> " + token);
    }

    /**
     * close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.submitBtn)
    void submitBtn() {
        cardNum = cardNumEt.getText().toString().trim();
        phoneNum = phoneNumEt.getText().toString().trim();

        Log.d(Constants.TAG, "----> " + cardNum);
        Log.d(Constants.TAG, "----> " + phoneNum);

        activateCard(cardNum, phoneNum);
    }

    public void activateCard(final String card, final String phone) {

        String url = Constants.BASE_URL + "card/active";


        //the body
        JSONObject object = new JSONObject();
        try {
            object.put("mobile", phone);
            object.put("card_number", card);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("My Project", "response ----->  " + response.toString());
                try {
                    String status = response.getString("status");
                    if (status.equals("true")) {

                        Toast.makeText(ActivateCardActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();
                        Log.d(Constants.TAG, "Phone ----> " + phone);
                        Log.d(Constants.TAG, "Card ----> " + card);
                        Intent intent = new Intent(ActivateCardActivity.this, ActivateCardCodeActivity.class);
                        intent.putExtra(Constants.CARD_NUM, card);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ActivateCardActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Constants.TAG, "error ---> " + error.toString());
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + pref.getUserToken());
                headers.put("X-Language", "ar");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(ActivateCardActivity.this).addToRequestQueue(jsObjRequest);

    }
}
