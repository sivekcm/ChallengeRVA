package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

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
           previousLogsBtn,
           leaderboardBtn,
           viewTeamBtn;

    CircleImageView profilePicImageView;

    Challenge challenge;
    Team team;
    DBHelper db = new DBHelper(this);

    User user;
    User coachUser;

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
        Cursor coachUserCursor = db.getUserData("username",challenge.getCoachAssigned());
        coachUserCursor.moveToNext();
        coachUser = new User(coachUserCursor);

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
        previousLogsBtn = findViewById(R.id.challengeLogBtn);
        leaderboardBtn = findViewById(R.id.challengeLeaderboardBtn);
        viewTeamBtn = findViewById(R.id.viewTeamBtn);
        profilePicImageView = findViewById(R.id.challengeViewProfilePicImageView);

        rateChallengeTxtView = findViewById(R.id.rateChallengeTxtView);
        rateChallengeBar = findViewById(R.id.rateChallengeBar);
        int challengeRating = Integer.parseInt(String.valueOf((int)rateChallengeBar.getRating()));

        challengeID = challenge.getChallengeID();
        challengeName = challenge.getChallengeName();
        coachName = "Coach: " + challenge.getCoachAssigned();
        description = "Description: " + challenge.getChallengeDescription();
        startDate = "Begins: " + challenge.getStartDate();
        endDate = "Ends: " + challenge.getEndDate();
        difficulty = challenge.getDifficulty();
        isTeam = challenge.isTeam();
        minTeam = challenge.getMinTeam();
        maxTeam = challenge.getMaxTeam();

        String difficultyView = "Difficulty: " + difficulty;
        String minTeamView = "Minimum team members: " + minTeam;
        String maxTeamView = "Maximum team members: " + maxTeam;


        Cursor cursor = db.getParticipatesData("username",user.getUsername(),"challenge_id",String.valueOf(challenge.getChallengeID()));
//        Cursor userChallengeCursor = db.getChallengeData("username",user.getUsername());

        //if the user has completed that challenge
        rateChallengeTxtView.setVisibility(View.VISIBLE);
        rateChallengeBar.setVisibility(View.VISIBLE);



        if (cursor.getCount() == 1) {
            registerChallengeButton.setVisibility(View.GONE);
            if (isTeam)
            {
                Cursor getTeamCursor = db.getTeamData("username",user.getUsername(),"challenge_id",String.valueOf(challenge.getChallengeID()));
                getTeamCursor.moveToNext();
                team = new Team(getTeamCursor);
            }
            else
            {
                viewTeamBtn.setVisibility(View.GONE);
            }
        }
        else {
            previousLogsBtn.setVisibility(View.GONE);
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
                    boolean success = db.insertParticipates(user.getUsername(),challengeID,currentDate,"N","N");
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
                    finish();
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

        previousLogsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogViewIntent = new Intent(ViewChallengeActivity.this,LogViewActivity.class);
                toLogViewIntent.putExtra("activity", "ViewChallengeActivity");
                toLogViewIntent.putExtra("User Object", user);
                toLogViewIntent.putExtra("challenge", challenge);
                toLogViewIntent.putExtra("team", team);

                startActivity(toLogViewIntent);
            }
        });

        coachNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPublicProfileActivityIntent = new Intent(ViewChallengeActivity.this,PublicProfileActivity.class);
                toPublicProfileActivityIntent.putExtra("other user", coachUser);
                toPublicProfileActivityIntent.putExtra("User Object", user);
                startActivity(toPublicProfileActivityIntent);
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
            availability = "Availability: Not Available";
        }
        else if(startDate.compareTo(currentDate) < 0){
            availability = "Availability: Available";
        }
        else if(startDate.compareTo(currentDate) == 0){
            availability = "Availability: Starts today, register quickly!";
        }
        else{
            availability = "Availability: Conditional for availability didn't work.";
        }

        challengeNameTextView.setText(challengeName);
        coachNameTextView.setText(coachName);
        descriptionTextView.setText(description);
        startDateTextView.setText(startDate);
        endDateTextView.setText(endDate);
        difficultyTextView.setText(difficultyView);
        if (isTeam) {
            teamTypeTextView.setText("Type: Team");
        }
        else
        {
            teamTypeTextView.setText("Type: Single");
        }
        minTeamTextView.setText(String.valueOf(minTeamView));
        maxTeamTextView.setText(String.valueOf(maxTeamView));
        availabilityTextView.setText(availability);

        byte[] image = coachUser.getImage();
        DisplayImage.display(this,profilePicImageView,image);
    }

}
