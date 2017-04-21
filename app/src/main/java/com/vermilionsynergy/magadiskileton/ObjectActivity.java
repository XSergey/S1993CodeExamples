package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        WebView mWebView = (WebView)findViewById(R.id.ObjectWV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new ObjectActivity.MyJavaInterface(), "app");
        mWebView.loadUrl("file:///android_asset/object.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(ObjectActivity.this, CartActivity.class);
            //startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void NewOpen() {
            Intent intent = new Intent(ObjectActivity.this, NewActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void goBack(){
            finish();
        }

    }
}
