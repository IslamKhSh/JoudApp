package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
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
import com.tremno.joudapp.Utils.PreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private String token;
    @BindView(R.id.phoneNumEt)
    EditText phoneNumEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.keepLoginCb)
    CheckBox keepLoginCb;

    PreferenceUtils pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ButterKnife
        ButterKnife.bind(this);
        pref = new PreferenceUtils(this);
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.loginBtn)
    void loginBtn() {
        loginUser();
    }

    @OnClick(R.id.visitorLoginBtn)
    void visitorLoginBtn() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(Constants.VISITOR_TAG, Constants.VISITOR_TAG);
        startActivity(intent);
    }

    @OnClick(R.id.forgetPassBtn)
    void forgetPassBtn() {
        startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
    }

    @OnClick(R.id.signupBtn)
    void signupBtn() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }


    public void loginUser() {

        checkAllTV();

        String url = Constants.BASE_URL + "user/login";

        //the body
        JSONObject object = new JSONObject();
        try {

            object.put("login", phoneNumEt.getText().toString().trim());
            object.put("password", passwordEt.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {
                        token = response.getString("token");

                        JSONObject data = response.getJSONObject("data");

                        Log.d(Constants.TAG, data.toString());

                        if (data.length() == 0) {
                            Toast.makeText(LoginActivity.this, "email or password is not correct", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (keepLoginCb.isChecked()) {

                            pref.setUserToken(token);
                            pref.setPersonId((int) data.get("id"));
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("token",token);
                        intent.putExtra("id",(int) data.get("id"));
                        startActivity(intent);


                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();

                        startActivity(intent);


                    } else {
                        Toast.makeText(LoginActivity.this, "Email Or Password Is Incorrect", Toast.LENGTH_LONG).show();

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
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsObjRequest);

    }

    public void checkAllTV() {
        if (phoneNumEt.getText().toString().matches("") || passwordEt.getText().toString().matches("")) {
            Toast.makeText(LoginActivity.this, "All fields must be filled", Toast.LENGTH_LONG).show();

        }


    }
}

