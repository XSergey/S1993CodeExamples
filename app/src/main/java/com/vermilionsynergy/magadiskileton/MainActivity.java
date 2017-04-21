package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    public Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tf = Typeface.createFromAsset(getAssets(), "fonts/segoeui.ttf");
        WebView mWebView = (WebView)findViewById(R.id.MainWV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new MainActivity.MyJavaInterface(), "app");
        mWebView.loadUrl("file:///android_asset/main.html");
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void cartOpen() {
            //Intent intent = new Intent(MainActivity.this, CartActivity.class);
            //startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void SaleOpen() {
            Log.d("Magadi", "SaleOpen");
            Intent intent = new Intent(MainActivity.this, SaleActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void NewOpen() {
            Log.d("Magadi", "new Click");
            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void mainItemOpen() {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        }
    }
}
