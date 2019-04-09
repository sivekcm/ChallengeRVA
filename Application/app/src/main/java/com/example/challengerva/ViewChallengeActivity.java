package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            availabilityTextView,
            rateChallengeTxtView;

    RatingBar rateChallengeBar;

    String challengeName,
            coachName,
            description,
            startDate,
            endDate,
            availability;

    int challengeID,
            difficulty,
            minTeam,
            maxTeam;

    boolean isTeam;

    Button registerChallengeButton,
           logBtn,
           leaderboardBtn,
           viewTeamBtn;

    Challenge challenge;
    Team team;
    DBHelper db = new DBHelper(this);

    User user;

    /**********************
     * onCreate method
     * @descript: will run the activity upon launch
     * @param: (bundle) savedInstanceState
     *
     **********************/

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_view);

        final Intent intent = getIntent();
        challenge = intent.getParcelableExtra("challenge");
        user = intent.getParcelableExtra("User Object");

        //instantiate DBHelper for database methods

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
        logBtn = findViewById(R.id.challengeLogBtn);
        leaderboardBtn = findViewById(R.id.challengeLeaderboardBtn);
        viewTeamBtn = findViewById(R.id.viewTeamBtn);

        rateChallengeTxtView = findViewById(R.id.rateChallengeTxtView);
        rateChallengeBar = findViewById(R.id.rateChallengeBar);
        int challengeRating = Integer.parseInt(String.valueOf((int)rateChallengeBar.getRating()));

        challengeID = challenge.getChallengeID();
        challengeName = challenge.getChallengeName();
        coachName = challenge.getCoachAssigned();
        description = challenge.getChallengeDescription();
        startDate = challenge.getStartDate();
        endDate = challenge.getEndDate();
        difficulty = challenge.getDifficulty();
        isTeam = challenge.isTeam();
        minTeam = challenge.getMinTeam();
        maxTeam = challenge.getMaxTeam();

        Cursor cursor = db.getParticipatesData("username",user.getUsername(),"challenge_id",String.valueOf(challenge.getChallengeID()));
        Cursor userChallengeCursor = db.getChallengeData("username",user.getUsername());

        //if the user has completed that challenge
        rateChallengeTxtView.setVisibility(View.VISIBLE);
        rateChallengeBar.setVisibility(View.VISIBLE);



        if (cursor.getCount() == 1) {
            registerChallengeButton.setVisibility(View.GONE);
            if (isTeam)
            {
                Cursor getTeamCursor = db.getTeamData("username",user.getUsername(),"challenge_id",String.valueOf(challenge.getChallengeID()));
                team = new Team(getTeamCursor);
            }
            else
            {
                viewTeamBtn.setVisibility(View.GONE);
            }
        }
        else {
            logBtn.setVisibility(View.GONE);
            leaderboardBtn.setVisibility(View.GONE);
            viewTeamBtn.setVisibility(View.GONE);
        }

        registerChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if (isTeam) {
                    /**Direct to team creation activity**/
                    intent = new Intent(ViewChallengeActivity.this, AthleteCreateTeamActivity.class);
                    intent.putExtra("challenge", challenge);
                    intent.putExtra("User Object", user);
                    startActivity(intent);
                }
                else
                {
                    //Gets the current date, for determining the date the user signed up
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String currentDate = sdf.format(date);
                    boolean success = db.insertParticipates(user.getUsername(),challengeID,currentDate,"N");
                    if (success)
                    {
                        Toast.makeText(ViewChallengeActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(ViewChallengeActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                    }

                    intent = new Intent(ViewChallengeActivity.this, ChallengeSearchActivity.class);
                    startActivity(intent);
                }

            }
        });

        viewTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toTeamIntent = new Intent(ViewChallengeActivity.this,TeamActivity.class);
                toTeamIntent.putExtra("team",team);
                toTeamIntent.putExtra("User Object",user);
                toTeamIntent.putExtra("challenge",challenge);

                startActivity(toTeamIntent);
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogIntent = new Intent(ViewChallengeActivity.this,LogChallengeActivity.class);
                toLogIntent.putExtra("activity", "ViewChallengeActivity");
                toLogIntent.putExtra("User Object", user);
                toLogIntent.putExtra("challenge", challenge);

                startActivity(toLogIntent);
            }
        });



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
        difficultyTextView.setText(String.valueOf(difficulty));
        if (isTeam) {
            teamTypeTextView.setText("Team");
        }
        else
        {
            teamTypeTextView.setText("Single");
        }
        minTeamTextView.setText(String.valueOf(minTeam));
        maxTeamTextView.setText(String.valueOf(maxTeam));
        availabilityTextView.setText(availability);


    }

}
