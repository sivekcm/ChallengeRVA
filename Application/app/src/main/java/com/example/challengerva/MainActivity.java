package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Login Button
        Button loginActivityLoginBtn = (Button) findViewById(R.id.loginActivityLoginButton);
        loginActivityLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            }
        });

        //Forgot Username Button
        Button forgotUsernameActivityBtn = (Button) findViewById(R.id.loginActivityForgotUsernameButton);
        forgotUsernameActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotUsernameIntent = new Intent(getApplicationContext(), ForgotUsernameActivity.class);
                startActivity(forgotUsernameIntent);
            }
        });

        //Forgot Password Button
        Button forgotPasswordActivityBtn = (Button) findViewById(R.id.loginActivityForgotPasswordButton);
        forgotPasswordActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent forgotPasswordIntent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
               startActivity(forgotPasswordIntent);
            }
        });

    }



}
