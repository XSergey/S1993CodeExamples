package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.vermilionsynergy.magadiskileton.ApiConfig;

public class LoginInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_login_in);
        final ImageView myImageView = (ImageView)findViewById(R.id.imageSpinner);
        final Animation animationRotateCenter = AnimationUtils.loadAnimation(LoginInActivity.this, R.anim.rotate_center);
        myImageView.startAnimation(animationRotateCenter);
        new CountDownTimer(4000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {
                final Animation animationRotateCenter = AnimationUtils.loadAnimation(LoginInActivity.this, R.anim.rotate_center);
                myImageView.startAnimation(animationRotateCenter);
            }
            public void onFinish() {
                Intent intent = new Intent(LoginInActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        }.start();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        this.getAccountFromApi(username, password);

        myImageView.setVisibility(View.GONE);

        Intent loginIntent = new Intent(LoginInActivity.this, Main2Activity.class);
        startActivity(loginIntent);
    }

    protected void getAccountFromApi(String username, String password) {
        ApiConfig api = new ApiConfig("CustomerSignIn");
        api.setParams(new String[]{username, password});
        api.buildRequest();
    }
}
