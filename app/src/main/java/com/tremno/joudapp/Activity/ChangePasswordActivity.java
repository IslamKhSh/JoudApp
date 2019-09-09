package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tremno.joudapp.Utils.Constants;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.passwordEt)
    EditText passwordEt;

    @BindView(R.id.confirmPasswordEt)
    EditText confirmPasswordEt;

    @BindView(R.id.codeEt)
    EditText codeEt;
    String mobile = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //ButterKnife
        ButterKnife.bind(this);

    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.confirmBtn)
    void confirmBtn() {

        changePass();
    }

    public void changePass() {

        String url = Constants.BASE_URL + "user/submitNewPassword";
        mobile = getIntent().getStringExtra("mobile");

        //the body
        JSONObject object = new JSONObject();
        try {

            String mobile = getIntent().getStringExtra("mobile");

            object.put("mobile", mobile);
            object.put("code", codeEt.getText().toString().trim());
            if (passwordEt.getText().toString().trim().equals(confirmPasswordEt.getText().toString().trim())) {
                object.put("password", passwordEt.getText().toString().trim());
            } else {
                Toast.makeText(ChangePasswordActivity.this, "The password not the same", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {

                        startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
                        Toast.makeText(ChangePasswordActivity.this, "Password Changed Correctly", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "The code is incorrect", Toast.LENGTH_LONG).show();

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
        MySingleton.getInstance(ChangePasswordActivity.this).addToRequestQueue(jsObjRequest);

    }
}
