package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.widget.Button;

import java.util.ArrayList;

public class AthleteCreateTeamActivity extends AppCompatActivity {
    EditText teamNameEditTxt;
    NumberPicker teamNumberPicker;
    TextView createTeamTxtView;
    Button createTeamBtn;
    ArrayList<User>teamMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_create_team);

        Intent intent = getIntent();
        final User athlete = intent.getParcelableExtra("User Object");

        teamNameEditTxt = (EditText) findViewById(R.id.teamNameEditTxt);
        teamNumberPicker = (NumberPicker) findViewById(R.id.teamNumberPicker);
        createTeamTxtView = (TextView)findViewById(R.id.createTeamTxtView);
        createTeamBtn = (Button)findViewById(R.id.createTeamBtn);

        //create challenge and get the min and max number for the team

        teamNumberPicker.setMinValue(2);
        teamNumberPicker.setMaxValue(100);
        teamNumberPicker.setWrapSelectorWheel(false);

        teamMembers = new ArrayList<>();
    }
}


