package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class Cart2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        WebView mWebView = (WebView)findViewById(R.id.Cart2WV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Cart2Activity.MyJavaInterface(), "cart");
        mWebView.loadUrl("file:///android_asset/cart2.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void goBack(){
            finish();
        }

        @android.webkit.JavascriptInterface
        public void next(){
            Intent intent = new Intent(Cart2Activity.this, Cart3Activity.class);
            startActivity(intent);
        }
    }
}
