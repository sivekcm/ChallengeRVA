package com.example.challengerva;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ChallengeActivity extends AppCompatActivity {
    TextView createChallenge;
    TextView challengeType;
    TextView registrationType;

    Spinner challengeTypeList;
    Spinner registrationTypeList;


    EditText challengeName;
    EditText coachAssigned;
    EditText challengeDescription;
    EditText difficulty;
    EditText startDate;
    EditText endDate;

    Button submitChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchallenge_form);

        createChallenge = findViewById(R.id.createChallenge);
        challengeType = findViewById(R.id.challengeTypeTextView);
        registrationType = findViewById(R.id.registrationTypeTextView);

        challengeTypeList = findViewById(R.id.ChallengeType);
        registrationTypeList = findViewById(R.id.registrationType);

        challengeName = findViewById(R.id.challengeName);
        coachAssigned = findViewById(R.id.CoachAssigned);
        challengeDescription = findViewById(R.id.challengeDescription);
        difficulty = findViewById(R.id.difficulty);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        submitChallenge = findViewById(R.id.ButtonSubmitChallenge);
        submitChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ChallengeActivity.this);

                String name = challengeName.getText().toString();
                String coach = coachAssigned.getText().toString();
                String start = startDate.getText().toString();
                String end = endDate.getText().toString();
                //int duration -> need to add to challenge form
                String type = registrationType.getText().toString();
                String diff = difficulty.getText().toString();
                String category = challengeType.getText().toString();

            }
        });
    }

}
