package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class SaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        WebView mWebView = (WebView)findViewById(R.id.SaleWV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new SaleActivity.MyJavaInterface(), "app");
        mWebView.loadUrl("file:///android_asset/sale.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(SaleActivity.this, CartActivity.class);
            //startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void NewOpen() {
            Intent intent = new Intent(SaleActivity.this, NewActivity.class);
            startActivity(intent);
        }
    }
}
