package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class Cart5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart5);
        WebView mWebView = (WebView)findViewById(R.id.Cart5WV);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Cart5Activity.MyJavaInterface(), "cart");
        mWebView.loadUrl("file:///android_asset/cart5.html");
    }
    private class MyJavaInterface {
        @android.webkit.JavascriptInterface
        public void goBack(){
            finish();
        }
        @android.webkit.JavascriptInterface
        public void object(){
            Intent intent = new Intent(Cart5Activity.this, ObjectActivity.class);
            startActivity(intent);
        }
        @android.webkit.JavascriptInterface
        public void buy(){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "success!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(Cart5Activity.this, Main2Activity.class);
            startActivity(intent);
        }
    }

}
