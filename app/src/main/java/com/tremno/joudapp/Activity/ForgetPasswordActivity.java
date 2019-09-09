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

public class ForgetPasswordActivity extends AppCompatActivity {
    @BindView(R.id.phoneEt)
    EditText phoneEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
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
        ForgetPass();
    }

    public void ForgetPass() {

        String url = Constants.BASE_URL + "user/forgetPassword";

        //the body
        JSONObject object = new JSONObject();
        try {

            object.put("mobile", phoneEt.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {

                        Intent intent = new Intent(ForgetPasswordActivity.this, ChangePasswordActivity.class);
                        intent.putExtra("mobile", phoneEt.getText().toString().trim());
                        startActivity(intent);
                        Toast.makeText(ForgetPasswordActivity.this, "Phone Number is correct", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(ForgetPasswordActivity.this, "Phone Number is incorrect", Toast.LENGTH_LONG).show();

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
        MySingleton.getInstance(ForgetPasswordActivity.this).addToRequestQueue(jsObjRequest);

    }
}
