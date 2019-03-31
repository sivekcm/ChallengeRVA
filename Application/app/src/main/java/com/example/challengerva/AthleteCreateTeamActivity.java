package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AthleteCreateTeamActivity extends AppCompatActivity {
    EditText teamNameEditTxt;
    TextView createTeamTxtView;
    TextView challengeNameTxtView;
    Button createTeamBtn;
    Button joinTeamBtn;
    DBHelper db = new DBHelper(this);
    Challenge challenge;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_create_team);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        challenge = intent.getParcelableExtra("challenge");


        teamNameEditTxt = (EditText) findViewById(R.id.teamNameEditTxt);
        createTeamTxtView = (TextView) findViewById(R.id.createTeamTxtView);
        challengeNameTxtView = (TextView) findViewById(R.id.challengeNameTxtView);

        createTeamBtn = (Button)findViewById(R.id.createTeamBtn);
        joinTeamBtn = (Button)findViewById(R.id.joinTeamBtn);
        joinTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AthleteCreateTeamActivity.this,AthleteTeamSelectionActivity.class);
                intent.putExtra("User Object", user);
                intent.putExtra("challenge", challenge);
                startActivity(intent);
            }
        });


        //challengeNameTxtView.setText(db.getChallengeData("name", 3));
        createTeamBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Gets the current date, for determining the date the user signed up
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String currentDate = sdf.format(date);
                String teamName = teamNameEditTxt.getText().toString();
                boolean teamSuccess = db.insertTeam(teamName, challenge.getChallengeID(),user.getUsername());
                boolean partSuccess = db.insertParticipates(user.getUsername(),challenge.getChallengeID(),currentDate,"N");
                if (teamSuccess && partSuccess)
                {
                    Toast.makeText(AthleteCreateTeamActivity.this,"Team Registration Successful",Toast.LENGTH_LONG);
                }
                else
                {
                    Toast.makeText(AthleteCreateTeamActivity.this,"Something went wrong",Toast.LENGTH_LONG);
                }

                Intent intent = new Intent(AthleteCreateTeamActivity.this,ViewChallengeActivity.class);
                intent.putExtra("User Object", user);
                intent.putExtra("challenge", challenge);
                startActivity(intent);
            }
        });
    }

}
