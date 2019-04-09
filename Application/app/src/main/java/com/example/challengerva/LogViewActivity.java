package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LogViewActivity extends AppCompatActivity {

    User user;
    Challenge challenge;
    Team team;

    DBHelper db = new DBHelper(this);
    LogParentAdapter adapter;
    Cursor cursorParent;
    Cursor cursorChild;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_view);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        challenge = intent.getParcelableExtra("challenge");
        team = intent.getParcelableExtra("team");

        recyclerView = findViewById(R.id.viewLogsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cursorParent = db.getTeamData("team_name",team.getName(),"challenge_id",String.valueOf(challenge.getChallengeID()));
        cursorParent.moveToNext();
        cursorChild = db.getLogDataInnerJoin(cursorParent.getString(0),cursorParent.getInt(1), cursorParent.getString(2));
        adapter = new LogParentAdapter(this,cursorParent,cursorChild);
        recyclerView.setAdapter(adapter);

    }
}
