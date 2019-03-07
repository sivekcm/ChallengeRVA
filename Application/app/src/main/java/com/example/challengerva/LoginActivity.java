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
                boolean testUser = db.insertUser("Rick", "111", "Rick",
                        "Johnson", "1998-12-04", "2019-03-06",
                        "rickster@fake.org", 0, "N", "coach");

                boolean testChallenge = db.insertChallenge(2, "challenge2",
                       "Rick", "2019-03-06", 1, "Team",
                       "hard", "strength");

                boolean testTeam = db.insertTeam(1, 2, "Rick",
                        null,null,null);

                boolean testLB = db.insertLeaderBoard(1,"Rick",0);

                boolean testPart = db.insertParticipates("Rick", 2, "2019-06-02");

                if (testUser && testChallenge && testTeam && testLB && testPart)
                {
                    Toast.makeText(LoginActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(LoginActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });

    }
}
