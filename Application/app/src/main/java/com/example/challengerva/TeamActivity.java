package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TeamActivity extends AppCompatActivity {

    TextView teamNameTextView;
    RecyclerView teamRV;
    Button joinBtn;
    Cursor cursor;
    TeamMemberAdapter adapter;
    DBHelper db = new DBHelper(this);
    User user;
    Challenge challenge;
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        challenge = intent.getParcelableExtra("challenge");
        team = intent.getParcelableExtra("team");

        teamRV = findViewById(R.id.specificTeamRV);
        teamNameTextView = findViewById(R.id.specificTeamTextView);
        joinBtn = findViewById(R.id.joinTeamBtn);

        Cursor teamCursor = db.getTeamData("username",user.getUsername(),"challenge_id",String.valueOf(team.getChallengeID()));
        if (teamCursor.getCount() == 1)
        {
            joinBtn.setVisibility(View.GONE);
        }

        teamNameTextView.setText(team.getName());

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets the current date, for determining the date the user signed up
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String currentDate = sdf.format(date);

                String teamName = team.getName();
                boolean teamSuccess = db.insertTeam(teamName, challenge.getChallengeID(),user.getUsername());
                boolean partSuccess = db.insertParticipates(user.getUsername(),challenge.getChallengeID(),currentDate,"N","N");
                if (teamSuccess && partSuccess)
                {
                    Toast.makeText(TeamActivity.this,"Team Registration Successful",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(TeamActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(TeamActivity.this,ViewChallengeActivity.class);
                intent.putExtra("User Object", user);
                intent.putExtra("challenge", challenge);
                startActivity(intent);
                finish();
            }
        });

        cursor = db.getTeamData("team_name",team.getName(),"challenge_id",String.valueOf(team.getChallengeID()));
        showResults(cursor);
        toOtherUserProfileActivity(cursor,adapter);
    }

    public void showResults(Cursor cursor)
    {
        teamRV.setLayoutManager(new LinearLayoutManager(TeamActivity.this));
        adapter = new TeamMemberAdapter(TeamActivity.this, cursor);
        teamRV.swapAdapter(adapter,false);
    }

    public void toOtherUserProfileActivity(final Cursor cursor, TeamMemberAdapter adapter)
    {
        adapter.setOnItemClickListener(new TeamMemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(TeamActivity.this,OtherUserProfileActivity.class);
                String otherUsername = cursor.getString(2);
                Cursor otherUserCursor = db.getUserData("username",otherUsername);
                User otherUser = new User(otherUserCursor);
                otherUser.setLoggedUser(false);
                intent.putExtra("other user",otherUser);
                intent.putExtra("User Object", user);
                startActivity(intent);
            }
        });
    }
}
