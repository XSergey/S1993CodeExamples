package com.vermilionsynergy.magadiskileton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void backClick(View view){
        finish();
    }

    public void facebookClick(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Need to integrate with Facebook", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);

        /*EditText username = (EditText) findViewById(R.id.username);
        String _username = username.getText().toString().trim();

        EditText password = (EditText) findViewById(R.id.password);
        String _password = password.getText().toString().trim();

        if (_username.isEmpty() || _password.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please, add your account information.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            intent.putExtra("username", _username);
            intent.putExtra("password", _password);
            startActivity(intent);
        }*/

        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, LoginInActivity.class);

        EditText username = (EditText) findViewById(R.id.username);
        String _username = username.getText().toString().trim();

        EditText password = (EditText) findViewById(R.id.password);
        String _password = password.getText().toString().trim();

        if (_username.isEmpty() || _password.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                "Please, add your account information.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            intent.putExtra("username", _username);
            intent.putExtra("password", _password);
            startActivity(intent);
        }
    }
}
