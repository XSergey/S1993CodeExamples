package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void welcomeClick(View view){
        //jsonData jd = new jsonData();
        //jd.getList();
        Intent intent = new Intent(splashActivity.this, LoginActivity.class);
        //Intent intent = new Intent(splashActivity.this, itemListActivity.class);
        //Intent intent = new Intent(splashActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
