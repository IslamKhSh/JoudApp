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

public class SignupActivity extends AppCompatActivity {
    private String token;

    @BindView(R.id.personEt)
    EditText personEt;
    @BindView(R.id.phoneNumEt)
    EditText phoneNumEt;
    @BindView(R.id.emailEt)
    EditText emailEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.confirmPasswordEt)
    EditText confirmPasswordEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife
        ButterKnife.bind(this);

    }

    @OnClick(R.id.signupBtn)
    void signup() {
        checkAllTV();
        addUser();
    }


    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }


    public void addUser() {

        String url = Constants.BASE_URL + "user/register";

        //the body
        JSONObject object = new JSONObject();
        try {

            //todo here to change the user id
            object.put("name", personEt.getText().toString().trim());
            object.put("mobile", phoneNumEt.getText().toString().trim());
            object.put("email", emailEt.getText().toString().trim());
            if (passwordEt.getText().toString().trim().equals(confirmPasswordEt.getText().toString().trim())) {
                object.put("password", passwordEt.getText().toString().trim());
            } else {
                Toast.makeText(SignupActivity.this, "The password not the same", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(Constants.TAG, "response ----->  " + response.toString());

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {
                        token = response.getString("token");


                        JSONObject data = response.getJSONObject("data");



                        Toast.makeText(SignupActivity.this, "Done", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("id",(int) data.get("id"));
                        startActivity(intent);


                    } else {
                        Toast.makeText(SignupActivity.this, "email is been registered", Toast.LENGTH_LONG).show();

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
        MySingleton.getInstance(SignupActivity.this).addToRequestQueue(jsObjRequest);


    }




    public void checkAllTV() {

        if (personEt.getText().toString().matches("") || phoneNumEt.getText().toString().matches("") || emailEt.getText().toString().matches("") || passwordEt.getText().toString().matches("") || confirmPasswordEt.getText().toString().matches("")) {
            Toast.makeText(SignupActivity.this, "All fields must be filled", Toast.LENGTH_LONG).show();

        }
        if (phoneNumEt.length() < 11) {
            Toast.makeText(SignupActivity.this, "phone must be at least 11 number", Toast.LENGTH_LONG).show();

        }
        if (passwordEt.getText().toString().matches("") || passwordEt.length() < 8) {
            Toast.makeText(SignupActivity.this, "password must be at least 8 number", Toast.LENGTH_LONG).show();

        }
    }
}