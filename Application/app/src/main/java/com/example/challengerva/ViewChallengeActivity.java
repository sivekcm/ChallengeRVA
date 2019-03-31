package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class ViewChallengeActivity extends AppCompatActivity{

    //Declaration of variables implemented in UI
    TextView challengeNameTextView,
            coachNameTextView,
            descriptionTextView,
            startDateTextView,
            endDateTextView,
            difficultyTextView,
            teamTypeTextView,
            minTeamTextView,
            maxTeamTextView,
            availabilityTextView;

    String challengeName,
            coachName,
            description,
            startDate,
            endDate,
            teamType,
            availability;

    int difficulty,
        minTeam,
        maxTeam;

    Button registerChallengeButton;

    Challenge challenge;


    /**********************
     * onCreate method
     * @descript: will run the activity upon launch
     * @param: (bundle) savedInstanceState
     *
     **********************/

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_view);

        Intent intent = getIntent();
        challenge = intent.getParcelableExtra("challenge");

        //instantiate DBHelper for database methods
        //final DBHelper challenge = new DBHelper(ViewChallengeActivity.this);

        challengeNameTextView = findViewById(R.id.view_challengeName);
        coachNameTextView = findViewById(R.id.view_coachName);
        descriptionTextView = findViewById(R.id.view_description);
        startDateTextView = findViewById(R.id.view_startDate);
        endDateTextView = findViewById(R.id.view_endDate);
        difficultyTextView = findViewById(R.id.view_difficulty);
        teamTypeTextView = findViewById(R.id.view_teamType);
        minTeamTextView = findViewById(R.id.view_minTeam);
        maxTeamTextView = findViewById(R.id.view_maxTeam);
        availabilityTextView = findViewById(R.id.view_Availability);
        registerChallengeButton = findViewById(R.id.registerChallengeBtn);



        registerChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Direct to team creation activity**/
                Intent intent = new Intent(ViewChallengeActivity.this,AthleteCreateTeamActivity.class);
                intent.putExtra("challenge",challenge);
                startActivity(intent);

            }
        });

        challengeName = challenge.getChallengeName();
        coachName = challenge.getCoachAssigned();
        description = challenge.getChallengeDescription();
        startDate = challenge.getStartDate();
        endDate = challenge.getEndDate();
        difficulty = challenge.getDifficulty();
        teamType = challenge.getType();
        minTeam = challenge.getMinTeam();
        maxTeam = challenge.getMaxTeam();

        //Check for availability

        //Defaults to today's date
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        String currentDate = year + "-"  + month + "-" + day;

        if(startDate.compareTo(currentDate)> 0){
            availability = "Not Available";
        }
        else if(startDate.compareTo(currentDate) < 0){
            availability = "Available";
        }
        else if(startDate.compareTo(currentDate) == 0){
            availability = "Starts today, register quickly!";
        }
        else{
            availability = "Conditional for availability didn't work booohoo";
        }

        challengeNameTextView.setText(challengeName);
        coachNameTextView.setText(coachName);
        descriptionTextView.setText(description);
        startDateTextView.setText(startDate);
        endDateTextView.setText(endDate);
        difficultyTextView.setText(difficulty);
        teamTypeTextView.setText(teamType);
        minTeamTextView.setText(minTeam);
        maxTeamTextView.setText(maxTeam);
        availabilityTextView.setText(availability);


    }

}
