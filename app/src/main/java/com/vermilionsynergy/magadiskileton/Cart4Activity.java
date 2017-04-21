package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Cart4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart4);
        WebView mWebView = (WebView)findViewById(R.id.Cart4WV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Cart4Activity.MyJavaInterface(), "cart");
        mWebView.loadUrl("file:///android_asset/cart4.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void goBack(){
            finish();
        }

        @android.webkit.JavascriptInterface
        public void next(){
            Intent intent = new Intent(Cart4Activity.this, Cart5Activity.class);
            startActivity(intent);
        }
    }
}
