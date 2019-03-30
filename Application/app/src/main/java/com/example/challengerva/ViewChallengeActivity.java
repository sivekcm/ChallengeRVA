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
    TextView view_challengeName,
            view_coachName,
            view_description,
            view_startDate,
            view_endDate,
            view_difficulty,
            view_teamType,
            view_minTeam,
            view_maxTeam,
            view_Availability;

    String challenge_name,
            coach_name,
            description,
            startDate,
            endDate,
            team_type,
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
        challenge = intent.getParcelableExtra("Challenge Object");

        //instantiate DBHelper for database methods
        //final DBHelper challenge = new DBHelper(ViewChallengeActivity.this);

        view_challengeName = findViewById(R.id.view_challengeName);
        view_coachName = findViewById(R.id.view_coachName);
        view_description = findViewById(R.id.view_description);
        view_startDate = findViewById(R.id.view_startDate);
        view_endDate = findViewById(R.id.view_endDate);
        view_difficulty = findViewById(R.id.view_difficulty);
        view_teamType = findViewById(R.id.view_teamType);
        view_minTeam = findViewById(R.id.view_minTeam);
        view_maxTeam = findViewById(R.id.view_maxTeam);
        view_Availability = findViewById(R.id.view_Availability);


        registerChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Direct to team creation activity**/
                Intent intent = new Intent(ViewChallengeActivity.this,AthleteCreateTeamActivity.class);
                startActivity(intent);

            }
        });

        challenge_name = challenge.getChallengeName();
        coach_name = challenge.getCoachAssigned();
        description = challenge.getChallengeDescription();
        startDate = challenge.getStartDate();
        endDate = challenge.getEndDate();
        difficulty = challenge.getDifficulty();
        team_type = challenge.getType();
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


    }

}
