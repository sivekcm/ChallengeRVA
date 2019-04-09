package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AthleteProfileActivity extends AppCompatActivity {
    //Declares Text View
    TextView athleteNameTxtView;
    TextView athleteProfileTxtView;
    TextView athleteUsernameTxtView;

    //Declares Buttons
    Button athleteViewChallengesBtn;
    Button athleteViewFriendsBtn;
    Button resetProfileBtn;
    Button deleteAcctBtn;

    //Declaring Recycler View
    RecyclerView athleteChallengesRView;

    final DBHelper db = new DBHelper(AthleteProfileActivity.this);
    final User athlete = null;
    final Challenge chal = null;

    Intent intent = getIntent();
    final User user = intent.getParcelableExtra("User Object");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_profile);

        //Initializes textViews to their respective UI elements
        athleteNameTxtView = (TextView) findViewById(R.id.coachNameTxtView);
        athleteProfileTxtView = (TextView) findViewById(R.id.statusTxtView);
        athleteUsernameTxtView = (TextView) findViewById(R.id.coachUserNameTxtView);

        //Initializes Buttons to their respective UI elements
        athleteViewChallengesBtn = (Button) findViewById(R.id.createChallengeBtn);
        athleteViewFriendsBtn = (Button) findViewById(R.id.athleteViewFriendsBtn);
        deleteAcctBtn = (Button) findViewById(R.id.deleteAcctBtn);
        resetProfileBtn = (Button) findViewById(R.id.resetProfileBtn);

        //Initializing Recycler View
        athleteChallengesRView = (RecyclerView) findViewById(R.id.athleteChallengeRV);

        //Setting the name and user name text views to the athlete name and user name
        athleteNameTxtView.setText(user.getFirstName());
        athleteUsernameTxtView.setText(user.getUsername());

    }

    public void fillChallengesList() {
        ArrayList<String> athleteChallengeArray = new ArrayList<>();


    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void deleteAcct(final User user) {
        deleteAcctBtn.setOnClickListener(new View.OnClickListener() {

                                             @Override
                                             public void onClick(View view) {
                                                 String username = user.getUsername();
                                                 Cursor userData = db.getUserData("username", username);
                                                 Cursor challangeUserData = db.getChallengeData("username", username);
                                                 int challegeID = (challangeUserData.getInt(0));
                                                 String challengeIDSt = String.valueOf(challangeUserData.getInt(0));
                                                 Cursor teamUserData = db.getTeamData("username", username);
                                                 Cursor logUserData = db.getLogData(username, challegeID);
                                                 Cursor participateUserData = db.getParticipatesData("username", username);
                                                 Cursor leaderBoard = db.getUniversalLeaderBoardData("username");
                                                 String teamName = teamUserData.getString(0);

                                                 int deletedChal = 0;
                                                 int deletedTeam = 0;
                                                 int deletedLogUser = 0;
                                                 int deletedParticipates = 0;
                                                 int deletedLeaderBoard = 0;
                                                 int deletedNotifications = 0;
                                                 int deletedUser = 0;

                                                 while (userData.moveToNext()){

                                                 }
                                                 while (challangeUserData.moveToNext()) {
                                                     deletedChal = db.deleteChallenge(challengeIDSt);
                                                 }
                                                 while (teamUserData.moveToNext()) {
                                                     deletedTeam = db.deleteTeam(teamName, challegeID, username);
                                                 }
                                                 while (logUserData.moveToNext()) {
                                                     deletedLogUser = db.deleteLog(username, challegeID);
                                                 }
                                                 while (participateUserData.moveToNext()) {
                                                     deletedParticipates = db.deleteParticipates(username, challegeID);
                                                 }
                                                 while (leaderBoard.moveToNext()) {
                                                     //PLACE HOLDER
                                                     deletedLeaderBoard = db.deleteLeaderBoard(12, username);
                                                 }


                                                 if (deletedChal > 0 && deletedTeam >0 && deletedLogUser >0 && deletedParticipates >0 && deletedLeaderBoard >0) {
                                                     Toast toast=Toast.makeText(getApplicationContext(),"User was reset",Toast.LENGTH_LONG);
                                                 }
                                                 else {
                                                     Toast toast=Toast.makeText(getApplicationContext(),"User not reset",Toast.LENGTH_LONG);
                                                 }

                                             }
                                         }
        );
    }

    public void resetAcct(final User user) {
        resetProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = user.getUsername();
                Cursor challangeUserData = db.getChallengeData("username", username);
                int challegeID = (challangeUserData.getInt(0));
                String challengeIDSt = String.valueOf(challangeUserData.getInt(0));
                Cursor teamUserData = db.getTeamData("username", username);
                Cursor logUserData = db.getLogData(username, challegeID);
                Cursor participateUserData = db.getParticipatesData("username", username);
                Cursor leaderBoard = db.getUniversalLeaderBoardData("username");
                String teamName = teamUserData.getString(0);

                int deletedChal = 0;
                int deletedTeam = 0;
                int deletedLogUser = 0;
                int deletedParticipates = 0;
                int deletedLeaderBoard = 0;
                int deletedNotifications = 0;



                while (challangeUserData.moveToNext()) {
                    deletedChal = db.deleteChallenge(challengeIDSt);
                }
                while (teamUserData.moveToNext()) {
                    deletedTeam = db.deleteTeam(teamName, challegeID, username);
                }
                while (logUserData.moveToNext()) {
                    deletedLogUser = db.deleteLog(username, challegeID);
                }
                while (participateUserData.moveToNext()) {
                    deletedParticipates = db.deleteParticipates(username, challegeID);
                }
                while (leaderBoard.moveToNext()) {
                    //PLACE HOLDER
                    deletedLeaderBoard = db.deleteLeaderBoard(12, username);
                }


                if (deletedChal > 0 && deletedTeam >0 && deletedLogUser >0 && deletedParticipates >0 && deletedLeaderBoard >0) {
                    Toast toast=Toast.makeText(getApplicationContext(),"User was reset",Toast.LENGTH_LONG);
                }
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"User not reset",Toast.LENGTH_LONG);
                }
            }
        }
        );
    }
}

