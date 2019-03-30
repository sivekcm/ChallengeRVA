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
    NumberPicker teamNumberPicker;
    TextView createTeamTxtView;
    TextView challengeNameTxtView;
    Button createTeamBtn;
    ArrayList<User>teamMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_create_team);

        Intent intent = getIntent();
        final User athlete = intent.getParcelableExtra("User Object");


        teamNameEditTxt = (EditText) findViewById(R.id.teamNameEditTxt);
        createTeamTxtView = (TextView)findViewById(R.id.createTeamTxtView);
        challengeNameTxtView = (TextView)findViewById(R.id.challengeNameTxtView);

        //challengeNameTxtView.setText(Challenge);
        createTeamBtn = (Button)findViewById(R.id.createTeamBtn);

        //create challenge and get the min and max number for the team
        teamNumberPicker.setMinValue(2); //should be according to the challenge
        teamNumberPicker.setMaxValue(100);
        teamNumberPicker.setWrapSelectorWheel(false);

        teamMembers = new ArrayList<>();
    }
}


