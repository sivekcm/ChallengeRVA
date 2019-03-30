package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Button;

import java.util.ArrayList;

public class AthleteCreateTeamActivity extends AppCompatActivity {
    EditText teamNameEditTxt;
    TextView createTeamTxtView;
    TextView challengeNameTxtView;
    Button createTeamBtn;
    Button joinTeamBtn;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_create_team);

        Intent intent = getIntent();
        final User athlete = intent.getParcelableExtra("User Object");


        teamNameEditTxt = (EditText) findViewById(R.id.teamNameEditTxt);
        createTeamTxtView = (TextView) findViewById(R.id.createTeamTxtView);
        challengeNameTxtView = (TextView) findViewById(R.id.challengeNameTxtView);

        createTeamBtn = (Button) findViewById(R.id.createTeamBtn);
        //joinTeamBtn = (Button)findViewById(R.id.joinTeamBtn);

        //challengeNameTxtView.setText(db.getChallengeData("name", 3));
        createTeamBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String teamName = teamNameEditTxt.getText().toString();
                db.insertTeam(teamName, 000000, null);
            }
        });
    }

}



