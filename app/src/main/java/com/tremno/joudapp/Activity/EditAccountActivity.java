package com.tremno.joudapp.Activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static java.net.URLConnection.guessContentTypeFromName;

public class EditAccountActivity extends AppCompatActivity {
    @BindView(R.id.phoneEt)
    EditText phoneEt;
    @BindView(R.id.emailEt)
    EditText emailEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.conformPasswordEt)
    EditText conformPasswordEt;
    @BindView(R.id.profileIv)
    CircleImageView profileIv;

    private int id;
    private String token;

    PreferenceUtils pref;

    //image
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;

    private Uri filePath;

    String imagePath;

    File userPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
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

    @OnClick(R.id.saveBtn)
    void saveBtn() {

//        editUser();
        addUserImage();

    }

    @OnClick(R.id.profileIv)
    void pickImage() {
        showFileChooser();
    }

    public void editUser() {

        Intent intent = getIntent();
        id = (intent.getIntExtra("id", 0));
        token = intent.getStringExtra("token");
        String name = intent.getStringExtra("name");

        String url = Constants.BASE_URL + "update/profile/" + id;

        //the body
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("mobile", phoneEt.getText().toString().trim());
            object.put("email", emailEt.getText().toString().trim());
            if (passwordEt.getText().toString().trim().equals(conformPasswordEt.getText().toString().trim())) {
                object.put("password", passwordEt.getText().toString().trim());
            } else {
                Toast.makeText(EditAccountActivity.this, "The password not the same", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.PUT, url, object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(Constants.TAG, "response ----->  " + response.toString());

                try {

                    String status = response.getString("status");

                    if (status.equals("true")) {

                        JSONObject data = response.getJSONObject("data");

                        Intent intent = new Intent(EditAccountActivity.this, MyAccountActivity.class);
                        intent.putExtra("token", token);
                        intent.putExtra("id", (int) data.get("id"));
                        startActivity(intent);


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
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + token);
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(EditAccountActivity.this).addToRequestQueue(jsObjRequest);


    }

    //upload image
    public void addUserImage() {
//        imagePath = new File(getPath(filePath));

        Log.d(Constants.TAG, userPhotoFile.toString());
        String url = Constants.BASE_URL + "user/image/upload";

        //the body
        JSONObject object = new JSONObject();
        try {

            object.put("image", userPhotoFile);

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

                        Toast.makeText(EditAccountActivity.this, response.getString("message"), Toast.LENGTH_LONG).show();
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
                headers.put("X-Language", "ar");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(EditAccountActivity.this).addToRequestQueue(jsObjRequest);


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profileIv.setImageBitmap(bitmap);

                String selectedMediaPath;

                Cursor cursor = getContentResolver().query(filePath, null,null, null, null);

                if (cursor == null )
                    selectedMediaPath = filePath.getPath();
                else {
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    selectedMediaPath = cursor.getString(idx);
                }
                cursor.close();

                 userPhotoFile = new File(selectedMediaPath);
//                RequestBody userFileRequest = RequestBody.create(MediaType.parse(guessContentTypeFromName(userPhotoFile.getName())), userPhotoFile);
//
//                MultipartBody.Part mediaPart; // send it with retrofit as parameter with @Part
//                try {
//                    mediaPart = MultipartBody.Part.createFormData("image", userPhotoFile.getName(), userFileRequest);
//                } catch (Exception IllegalArgumentException) {
//                    mediaPart = MultipartBody.Part.createFormData("image", "not_supported_file_name", userFileRequest);
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
//        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return b;
    }




}

