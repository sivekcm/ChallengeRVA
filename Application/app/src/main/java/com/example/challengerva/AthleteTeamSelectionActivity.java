package com.example.challengerva;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class AthleteTeamSelectionActivity extends AppCompatActivity {
    TextView challengeNameTxtView;
    TextView selectTeamTxtView;
    RecyclerView teamSelectionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_team_selection);

        challengeNameTxtView = (TextView)findViewById(R.id.challengeNameTxtView);
        selectTeamTxtView = (TextView)findViewById(R.id.challengeNameTxtView);
        teamSelectionRecyclerView = findViewById(R.id.teamSelectionRecyclerView);
    }
}