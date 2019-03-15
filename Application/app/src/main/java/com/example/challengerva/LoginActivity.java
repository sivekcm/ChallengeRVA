package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;

    TextView userTextView;
    TextView passTextView;
    TextView forgotUserTextView;
    TextView forgotPassTextView;
    TextView registerTextView;

    EditText userEditText;
    EditText passEditText;

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        userTextView = findViewById(R.id.userTextView);
        passTextView = findViewById(R.id.passTextView);
        forgotUserTextView = findViewById(R.id.forgotUserTextView);
        forgotPassTextView = findViewById(R.id.forgotPassTextView);
        registerTextView = findViewById(R.id.createAccTextView);

        userEditText = findViewById(R.id.loginUserEditText);
        passEditText = findViewById(R.id.loginPassEditText);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();



                if (!hasAllFields(username,password))
                {
                    AlertMessage.AlertMessage("Missing Fields",
                            "Please fill in every required field",
                            LoginActivity.this);
                }
                Cursor userData = db.getUserData(username,password);

                if (hasAllFields(username, password) && userData.getCount() == 0)
                {
                    AlertMessage.AlertMessage("Invalid Credentials",
                            "We could not find an account with this username or password",
                            LoginActivity.this);
                    passEditText.setText("");
                }
                else if (userData.getCount() == 1)
                {
                    Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                    Cursor cursor = db.getUserData(username, password);
                }




            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(toRegister);

            }
        });

        TextView forgotUser = findViewById(R.id.forgotUserTextView);
        forgotUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toForgotUser = new Intent(getApplicationContext(), ForgotUserActivity.class);
                startActivity(toForgotUser);

            }
        });
    }

    public static boolean hasAllFields(String user, String pass)
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            return false;
        }
        return true;
    }
}
