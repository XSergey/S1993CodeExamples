package com.vermilionsynergy.magadiskileton;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequestHandler extends AsyncTask<String, Void, String> {

    //private OkHttpClient client = new OkHttpClient();
    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String response;
    //public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ApiRequestHandler(){}

    public String getResponse(){return this.response;}

    @Override
    protected String doInBackground(String... params) {

        String _response = null;
        String _url = params[0];
        try {
            URL url = new URL(_url);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            _response = builder.toString();

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            //e.printStackTrace();
        }
        //return jsonResult;

        /*OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
            //.url(params[0]).post(formBody)
            .header("Accept", "application/json")
            //.addHeader("HTTP_CONTENT_TYPE", "application/json") // ???
            .header("Content-Type", "application/json")
            .url(params[0])
            .build();

        try {
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful())
                throw new IOException("Unexpected code " + response.toString());

            _response = response.body().string();
        } catch (IOException e) {
            _response = e.getMessage();
        }*/

        this.response = _response;
        return _response;
    }
}
