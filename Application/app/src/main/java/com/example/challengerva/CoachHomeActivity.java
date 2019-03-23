package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoachHomeActivity extends AppCompatActivity {

    Button coachChallengeBtn;
    Button profileBtn;
    Button leaderboardBtn;
    Button logoutBtn;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_home);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");

        coachChallengeBtn = findViewById(R.id.coachHomeYourChallengesBtn);
        profileBtn = findViewById(R.id.coachHomeProfileBtn);
        leaderboardBtn = findViewById(R.id.coachHomeLeaderboardBtn);
        logoutBtn = findViewById(R.id.coachHomeLogoutBtn);

        coachChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachHomeActivity.this,CoachActivity.class);
                intent.putExtra("User Object", user);
                startActivity(intent);
            }
        });

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(CoachHomeActivity.this,LoginActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}
