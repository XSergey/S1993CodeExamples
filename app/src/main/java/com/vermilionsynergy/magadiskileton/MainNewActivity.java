package com.vermilionsynergy.magadiskileton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Magadi", "created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
    }
}
