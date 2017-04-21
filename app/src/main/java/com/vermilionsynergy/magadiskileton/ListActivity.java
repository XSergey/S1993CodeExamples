package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        WebView mWebView = (WebView)findViewById(R.id.ListWV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new ListActivity.MyJavaInterface(), "app");
        mWebView.loadUrl("file:///android_asset/list.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(ListActivity.this, CartActivity.class);
            //startActivity(intent);
        }

        @android.webkit.JavascriptInterface
        public void SaleOpen() {
            Log.d("Magadi", "SaleOpen");
            Intent intent = new Intent(ListActivity.this, SaleActivity.class);
            startActivity(intent);
        }

        @android.webkit.JavascriptInterface
        public void NewOpen() {
            Log.d("Magadi", "new Click");
            Intent intent = new Intent(ListActivity.this, NewActivity.class);
            startActivity(intent);
        }
    }
}
