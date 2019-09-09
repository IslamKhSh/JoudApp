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

public class ActivateCardCodeActivity extends AppCompatActivity {
    @BindView(R.id.verifyCodeEt)
    EditText verifyCodeEt;

    String verifyCode, cardNum;

    PreferenceUtils pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_card_code);
        //ButterKnife
        ButterKnife.bind(this);

        pref = new PreferenceUtils(this);


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
        verifyCode = verifyCodeEt.getText().toString().trim();
        cardNum = getIntent().getStringExtra(Constants.CARD_NUM);
        activateCard(verifyCode,cardNum);
    }

    public void activateCard(String code,String phone) {

        String url = Constants.BASE_URL + "card/active/confirm";


        //the body
        JSONObject object = new JSONObject();
        try {
            object.put("code", code);
            object.put("card_number", phone);
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

                        Toast.makeText(ActivateCardCodeActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ActivateCardCodeActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ActivateCardCodeActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Constants.TAG, error.toString());
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + pref.getUserToken());
                headers.put("X-Language", "");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(ActivateCardCodeActivity.this).addToRequestQueue(jsObjRequest);

    }
}
