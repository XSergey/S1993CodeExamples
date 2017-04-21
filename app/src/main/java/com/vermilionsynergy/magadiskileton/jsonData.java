package com.vermilionsynergy.magadiskileton;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ultron on 23.02.17.
 */

public class jsonData {
    public static String host = "http://45.55.56.69/";
    public String last_url = "";
    public static String TAG = "JSON";
    public static ArrayList<MagadiListItem> items;
    public static String SECTION_NAME = "";

    private static Activity activity;
    private static itemListActivity.itemListAdapter adapter;

    public void getData2(){
        new ParseTask().execute();
    }
    public void set_section_name(String section_name){
        SECTION_NAME = section_name;
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }
    private String getData(String url){
        ParseTask pt = new ParseTask();
        pt.setUrl(url);
        pt.execute();
        return pt.getJson();
    }
    public void getMenu(){
        String url = this.host + "/rest/api/v1/collection/category?tree=true";
        Log.d("URL", url);
        String object = this.getData(url);
        Log.d("MAGADI MENU",object);
    }

    public void getList(String params){
        String url = this.host + "/rest/api/v1/collection/product" + params;
        ListTask t = new ListTask();
        t.setUrl(url);
        t.execute();
    }

    public void setAdapter(itemListActivity.itemListAdapter adapter) {
        this.adapter = adapter;
    }

    private class ListTask extends AsyncTask<Void, Void, String> {

        private HttpURLConnection urlConnection = null;
        private BufferedReader reader = null;
        private JSONObject result;
        private String jsonResult = "";
        private String url = "";
        private ProgressDialog mProgressDialog;

        private JSONObject object;

        public JSONObject getObject(){
            return object;
        }
        public void setUrl(String url){
            this.url = url;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage("loading data...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с сервера
            try {
                URL url = new URL(this.url);

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

                jsonResult = builder.toString();

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
                //e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            items.clear();
            // выводим целиком полученную json-строку
            try {
                JSONArray ar = new JSONArray(json);
                for (int i = 0; i < ar.length(); i++) {
                    JSONObject item = ar.getJSONObject(i);
                    MagadiListItem it = new MagadiListItem(item.getString("entity_id"), item.getString("name"), SECTION_NAME, item.getString("price"), item.getString("product_image"));
                    items.add(it);

                }

                /*JSONObject ob = ar.getJSONObject(0);
                ob = ob.getJSONObject("children");
                for(Iterator<String> iter = ob.keys(); iter.hasNext();) {
                    String key = iter.next();
                    Log.d(TAG+" key", key);
                }
                *//**//*for(int i = 0; i< ar.length(); i++){
                    JSONObject ob2 = ar.getJSONObject(i);
                    Log.d(TAG + i, ob2.toString());
                }*//**//**/
            } catch (JSONException e) {
                mProgressDialog.dismiss();
                e.printStackTrace();
            }
            mProgressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        JSONObject result;
        String jsonResult = "";
        String url = "";
        String jsonR = "";

        private JSONObject object;

        public JSONObject getObject(){
            return object;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getJson(){
            return jsonR;
        }

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с сервера
            try {
                URL url = new URL(this.url);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setUseCaches(false);
                urlConnection.setAllowUserInteraction(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                jsonResult = builder.toString();
            }
            catch (IOException ex) {
                Log.e("httptest","IOException");
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
                //e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            jsonR = json;
            // выводим целиком полученную json-строку
            //Log.d(TAG, json);
            try {
                JSONArray ar = new JSONArray(json);
                JSONObject ob = ar.getJSONObject(0);
                ob = ob.getJSONObject("children");
                for(Iterator<String> iter = ob.keys(); iter.hasNext();) {
                    String key = iter.next();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*JSONObject dataJsonObject;
            String secondName;

            try {

                object = new JSONObject(json);
                JSONArray cats = object.getJSONArray("cats");

                // получаем информацию для индекса 1, т.е. вторая запись
                *//*JSONObject secondObject = cats.getJSONObject(1);
                secondName = secondObject.getString("name");
                Log.d(TAG, "Второе имя: " + secondName);*//*

                // перебираем и выводим контакты каждого кота
                *//*for (int i = 0; i < cats.length(); i++) {
                    JSONObject cat = cats.getJSONObject(i);

                    JSONObject contacts = cat.getJSONObject("contact");

                    String phone = contacts.getString("mobile");
                    String email = contacts.getString("email");
                    String skype = contacts.getString("skype");

                    Log.d(TAG, cats.getJSONObject(i).getString("name"));
                    Log.d(TAG, "phone: " + phone);
                    Log.d(TAG, "email: " + email);
                    Log.d(TAG, "skype: " + skype);
                }*//*

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }
}
