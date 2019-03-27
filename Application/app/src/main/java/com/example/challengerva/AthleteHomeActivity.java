package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AthleteHomeActivity extends AppCompatActivity {

    Button searchChallengeBtn;
    Button athleteChallengeBtn;
    Button profileBtn;
    Button leaderboardBtn;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_home);

        Intent intent = getIntent();
        final User user = intent.getParcelableExtra("User Object");

        searchChallengeBtn = findViewById(R.id.athleteHomeSearchChallengeBtn);
        athleteChallengeBtn = findViewById(R.id.athleteHomeYourChallengeBtn);
        profileBtn = findViewById(R.id.athleteHomeProfileBtn);
        leaderboardBtn = findViewById(R.id.athleteHomeLeaderboardBtn);
        logoutBtn = findViewById(R.id.athleteHomeLogoutBtn);

        searchChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(AthleteHomeActivity.this,ChallengeSearchActivity.class);
                startActivity(searchIntent);
            }
        });

        athleteChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent challengeIntent = new Intent(AthleteHomeActivity.this,AthleteViewChallengeActivity.class);
                challengeIntent.putExtra("User Object", user);
                startActivity(challengeIntent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                Intent logoutIntent = new Intent(AthleteHomeActivity.this,LoginActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}
