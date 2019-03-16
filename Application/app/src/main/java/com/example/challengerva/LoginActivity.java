package com.example.challengerva;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;
    Button btn;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        btn = findViewById(R.id.loginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean testUser = db.updateUser("Rick", "rickrock900",
                        "900", "Rick",
                        "Johnson", "1998-12-04", "2019-03-06",
                        "rickster@fake.org", 0, "Y", "coach");


                boolean testTeam = db.updateTeam(1, 2, 92147, 42176, "Rick",
                        "john", null, null);

                boolean testLB = db.updateLeaderBoard(1, "Rick", 20, "Rick", 1);

                boolean testPart = db.updateParticipates("Rick", 2, "Rick", 21, "2019-06-24");

                if (testUser && testTeam && testLB && testPart) {
                    Toast.makeText(LoginActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(LoginActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });

        register = findViewById(R.id.createAccTextView);
        register.setOnClickListener(new View.OnClickListener() {
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

        final TextView forgotPassword = findViewById(R.id.forgotPassTextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toForgotPass = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(toForgotPass);

            }
        });
    }
}
