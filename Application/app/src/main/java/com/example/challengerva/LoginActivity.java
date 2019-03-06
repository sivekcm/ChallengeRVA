package com.example.challengerva;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        btn = findViewById(R.id.loginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean test = db.insertUser("jac","123", "Jack", "Thorne",
                                  "1999-04=12", "2019-03-06", "john@fake.com",
                                0, "N", "athlete");
                if (test)
                {
                    Toast.makeText(LoginActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });

    }
}
