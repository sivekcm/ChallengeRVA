package com.example.challengerva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class AthleteHomeActivity extends AppCompatActivity {

    Button searchChallengeBtn;
    Button athleteChallengeBtn;
    Button profileBtn;
    Button leaderboardBtn;
    Button logoutBtn;
    Button searchUser;

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
        searchUser = findViewById(R.id.athleteHomeSearchUserBtn);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        DailyReminder.dailyReminder(this, alarmManager, user.getUsername());

        searchChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(AthleteHomeActivity.this,ChallengeSearchActivity.class);
                searchIntent.putExtra("User Object", user);
                startActivity(searchIntent);
            }
        });

        athleteChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent challengeIntent = new Intent(AthleteHomeActivity.this,AthleteViewChallengeActivity.class);
                challengeIntent.putExtra("User Object", user);
                challengeIntent.putExtra("activity","AthleteHomeActivity");
                startActivity(challengeIntent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAthleteProfileIntent = new Intent(AthleteHomeActivity.this,AthleteProfileActivity.class);
                toAthleteProfileIntent.putExtra("User Object", user);
                startActivity(toAthleteProfileIntent);
            }
        });

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLeaderboardInent = new Intent(AthleteHomeActivity.this,LeaderboardUniversal.class);
                toLeaderboardInent.putExtra("User Object", user);
                startActivity(toLeaderboardInent);
            }
        });

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSearchUserIntent = new Intent(AthleteHomeActivity.this,UserSearchActivity.class);
                toSearchUserIntent.putExtra("User Object", user);

                startActivity(toSearchUserIntent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(AthleteHomeActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
            }
        });
    }
}
