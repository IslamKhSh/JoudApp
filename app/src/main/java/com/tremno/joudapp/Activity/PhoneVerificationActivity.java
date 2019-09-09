package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tremno.joudapp.Utils.Constants;
import com.tremno.joudapp.Model.UserModel;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneVerificationActivity extends AppCompatActivity {
    @BindView(R.id.verifyCodeEt)
    EditText verifyCodeEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        //ButterKnife
        ButterKnife.bind(this);
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.submitBtn)
    void submitBtn() {
        verifyPhone();


    }

    @OnClick(R.id.skipBT)
    void skipBT() {
        UserModel user = (UserModel) getIntent().getSerializableExtra("user");
        System.out.println(user.getEmail());
        Intent intent = new Intent(PhoneVerificationActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void verifyPhone() {

        String url = Constants.BASE_URL + "user/verify";


        //the body
        JSONObject object = new JSONObject();
        try {

            object.put("mobile", getIntent().getStringExtra("mobile"));
            object.put("code", verifyCodeEt.getText().toString().trim());
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

                        JSONObject data = response.getJSONObject("data");

                        UserModel user = new UserModel();
                        user.setId((Integer) data.get("id"));
                        user.setFullName((String) data.get("name"));
                        user.setPhone((String) data.get("mobile"));
                        user.setEmail((String) data.get("email"));
                        user.setIsActive((Integer) data.get("is_active"));

                        Toast.makeText(PhoneVerificationActivity.this, "Done", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PhoneVerificationActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);


                    } else {
                        Toast.makeText(PhoneVerificationActivity.this, "code not true", Toast.LENGTH_LONG).show();

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
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(PhoneVerificationActivity.this).addToRequestQueue(jsObjRequest);


    }


}
