package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        WebView mWebView = (WebView)findViewById(R.id.NewWV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new NewActivity.MyJavaInterface(), "app");
        mWebView.loadUrl("file:///android_asset/new.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(NewActivity.this, CartActivity.class);
            //startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void SaleOpen() {
            Intent intent = new Intent(NewActivity.this, SaleActivity.class);
            startActivity(intent);
        }
    }
}
