package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AthleteTeamSelectionActivity extends AppCompatActivity {
    TextView challengeNameTxtView;
    RecyclerView teamSelectionRecyclerView;
    TeamAdapter adapter;
    DBHelper db = new DBHelper(this);

    Cursor cursor;
    User user;
    Challenge challenge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_team_selection);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        challenge =intent.getParcelableExtra("challenge");

        challengeNameTxtView = (TextView)findViewById(R.id.challengeNameTxtView);
        teamSelectionRecyclerView = findViewById(R.id.teamSelectionRecyclerView);


        cursor = db.getTeamData("challenge_id",String.valueOf(challenge.getChallengeID()),"team_name");

        showResults(cursor);

        teamSelectionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toTeamActivity(cursor,adapter);

    }

    public void showResults(Cursor cursor)
    {
        teamSelectionRecyclerView.setLayoutManager(new LinearLayoutManager(AthleteTeamSelectionActivity.this));
        adapter = new TeamAdapter(AthleteTeamSelectionActivity.this, cursor);
        teamSelectionRecyclerView.swapAdapter(adapter,false);
    }

    public void toTeamActivity(final Cursor cursor,TeamAdapter adapter)
    {
        adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                cursor.moveToPosition(position);
                Team team = new Team(cursor);
                Intent intent = new Intent(AthleteTeamSelectionActivity.this,TeamActivity.class);
                intent.putExtra("challenge",challenge);
                intent.putExtra("User Object",user);
                intent.putExtra("team",team);
                startActivity(intent);
            }
        });
    }
}