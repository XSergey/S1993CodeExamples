package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Cart3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart3);
        WebView mWebView = (WebView)findViewById(R.id.Cart3WV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Cart3Activity.MyJavaInterface(), "cart");
        mWebView.loadUrl("file:///android_asset/cart3.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void goBack(){
            finish();
        }

        @android.webkit.JavascriptInterface
        public void next(){
            Intent intent = new Intent(Cart3Activity.this, Cart4Activity.class);
            startActivity(intent);
        }
    }
}
