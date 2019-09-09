package com.tremno.joudapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.tremno.joudapp.MySingleton;
import com.tremno.joudapp.R;
import com.tremno.joudapp.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyAccountActivity extends AppCompatActivity {
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.emailTv)
    TextView emailTv;
    @BindView(R.id.profileIv)
    CircleImageView profileIv;

    private String token;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        //ButterKnife
        ButterKnife.bind(this);

        showUserData();
    }

    /* close the activity
     */
    @OnClick(R.id.backBtn)
    void backBtn() {
        finish();
    }

    @OnClick(R.id.editBtn)
    void editBtn() {
        Intent intent = new Intent(MyAccountActivity.this, EditAccountActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("id", (int) id);
        intent.putExtra("name", nameTv.getText());
        startActivity(intent);

    }

    @OnClick(R.id.profileIv)
    void pickImage() {
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
                        nameTv.setText(data.getString("name"));
                        phoneTv.setText(data.getString("mobile"));
                        emailTv.setText(data.getString("email"));
                        Log.d(Constants.TAG,data.getString("image"));

                        if (data.getString("image")!=null){
                            Glide.with(MyAccountActivity.this).load(Constants.IMAGE_URL+data.getString("image")).into(profileIv);
                        }

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
        MySingleton.getInstance(MyAccountActivity.this).addToRequestQueue(jsObjRequest);

    }

}
