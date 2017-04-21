package com.vermilionsynergy.magadiskileton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    String TAG = "NETWORK WORK!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void backClick(View view){
        finish();
    }
    public void EnterClick(View view){

        Intent intent = new Intent(this, PhoneConfActivity.class);
        startActivity(intent);



        //String url = getResources().getString(R.string.host) + getResources().getString(R.string.url_reg);
        //String url = "http://stolovaya-crimea.ru/post.php?email=sergey.martishin.inbox@gmail.com&firstname=Sergey&lastname=Api&password=123123123qW@";
        //Log.d(TAG, url);
        /*ProgressDialog mProgressDialog = new ProgressDialog(SignUpActivity.this);
        mProgressDialog.setMessage("wait");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();*/

        //String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        //SignUpHandler handler = new SignUpHandler("kn21@mail.ru","ilya", "demo", "123123123qW@", android_id);
        //String result = null;
        /*try {
            //result = handler.execute("http://stolovaya-crimea.ru/post.php").get();
            result = handler.execute("http://94.158.156.180:9170/rest/api/v1/auth/customer/create?email=sergey.martishin.inbox@gmail.com&firstname=Sergey&lastname=Api&password=123123123qW@").get();
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage());
        } catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
        Log.d(TAG, result);*/


    }

    public class SignUpHandler extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();
        String email, firstname, lastname, password, device_id;
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        public SignUpHandler(String email, String firstname, String lastname, String password, String device_id) {
            this.email = email;
            this.firstname = firstname;
            this.lastname = lastname;
            this.password = password;
            this.device_id = device_id;
        }

        @Override
        protected String doInBackground(String... params) {
            RequestBody formBody = new FormBody.Builder()
/*                    .add("email", this.email)
                    .add("firstname", this.firstname)
                    .add("password", this.password)
                    .add("lastname", this.lastname)
                    .add("platform", "android")
                    .add("device_id", this.device_id)*/
                    .build();

            formBody.contentType();
            Request request = new Request.Builder()
                    //.url(params[0]).post(formBody)
                    .url(params[0]).get()
                    .addHeader("Accept", "application/json")
                    .addHeader("HTTP_CONTENT_TYPE", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response.toString());
                return response.body().string();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return null;
        }
    }
}
