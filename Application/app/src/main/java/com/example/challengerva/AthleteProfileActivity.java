package com.example.challengerva;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AthleteProfileActivity extends AppCompatActivity {
    //Declares Text View
    TextView athleteNameTxtView;
    TextView athleteProfileTxtView;
    TextView athleteUsernameTxtView;
    TextView changeProfilePicTextView;

    //Declares Buttons
    Button athleteViewChallengesBtn;
    Button athleteViewFriendsBtn;
    Button resetProfileBtn;
    Button deleteAcctBtn;
    Button privateButton;
    Button changeBioButton;

    //Declaring Recycler View
    RecyclerView athleteChallengesRView;

    CircleImageView imageView;

    final DBHelper db = new DBHelper(AthleteProfileActivity.this);
    final User athlete = null;
    final Challenge chal = null;


    User user;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_profile);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        //Initializes textViews to their respective UI elements
        athleteNameTxtView = findViewById(R.id.athleteNameTxtView);
        athleteProfileTxtView = (TextView) findViewById(R.id.statusTxtView);
        athleteUsernameTxtView = (TextView) findViewById(R.id.athleteUsernameTxtView);
        changeProfilePicTextView = findViewById(R.id.athleteChangePhotoTextView);

        //Initializes Buttons to their respective UI elements
        athleteViewChallengesBtn = (Button) findViewById(R.id.createChallengeBtn);
        athleteViewFriendsBtn = (Button) findViewById(R.id.athleteEditBioBtn);
        deleteAcctBtn = (Button) findViewById(R.id.athleteDeleteAcctBtn);
        resetProfileBtn = (Button) findViewById(R.id.athleteResetProfileBtn);
        privateButton = (Button) findViewById(R.id.athleteProfilePrivateButton);
        changeBioButton = (Button) findViewById(R.id.athleteEditBioBtn);

        imageView = findViewById(R.id.athleteProfilePictureImageView);

        //Initializing Recycler View
        athleteChallengesRView = (RecyclerView) findViewById(R.id.athleteChallengeRV);

        //Setting the name and user name text views to the athlete name and user name
        athleteNameTxtView.setText(user.getFirstName());
        athleteUsernameTxtView.setText(user.getUsername());

        byte[] image = user.getImage();
        DisplayImage.display(this,imageView,image);

        toChangePhotoActivity();

        //The following code handles the Private button
        if(user.isPrivate())
            privateButton.setText("Make Profile Public");
        else
            privateButton.setText("Make Profile Private");

        privateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isPrivate())
                    privateButton.setText("Make Profile Private");
                else privateButton.setText("Make Profile Public");
                user.togglePrivate();
                db.updateUser(user.getParameters());

            }
        });

        //The following code handles the Bio button
        changeBioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeBioIntent = new Intent(AthleteProfileActivity.this, EditBioActivity.class);
                changeBioIntent.putExtra("user object", user);
                startActivityForResult(changeBioIntent,0);
            }
        });


        deleteAcct();
        resetAcct();


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

    public void deleteAcct() {
        deleteAcctBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = user.getUsername();
                Cursor userData = db.getUserData("username", username);
                Cursor challangeUserData = db.getChallengeData("coach", username);
                int challegeID = (challangeUserData.getInt(0));
                String challengeIDSt = String.valueOf(challangeUserData.getInt(0));
                Cursor teamUserData = db.getTeamData("username", username);
                Cursor logUserData = db.getLogData(username, challegeID);
                Cursor participateUserData = db.getParticipatesData("username", username);
                //Cursor leaderBoard = db.getUniversalLeaderBoardData("username");
                String teamName = teamUserData.getString(0);

                int deletedChal = 0;
                int deletedTeam = 0;
                int deletedLogUser = 0;
                int deletedParticipates = 0;
                int deletedLeaderBoard = 0;
                int deletedUser = 0;



                deletedChal = db.deleteChallenge(challengeIDSt);
                deletedTeam = db.deleteTeam(teamName, challegeID, username);
                deletedLogUser = db.deleteLog(username, challegeID);
                deletedParticipates = db.deleteParticipates(username, challegeID);

                //PLACE HOLDER
                deletedLeaderBoard = db.deleteLeaderBoard(username);



                if (deletedUser > 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "User was deleted", Toast.LENGTH_LONG);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "User not deleted", Toast.LENGTH_LONG);
                }

            }
        });
    }


    public void resetAcct() {
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
                //Cursor leaderBoard = db.getUniversalLeaderBoardData("username");
                String teamName = teamUserData.getString(0);

                int deletedChal = 0;
                int deletedTeam = 0;
                int deletedLogUser = 0;
                int deletedParticipates = 0;
                int deletedLeaderBoard = 0;



                 deletedChal = db.deleteChallenge(challengeIDSt);


                 deletedTeam = db.deleteTeam(teamName, challegeID, username);


                 deletedLogUser = db.deleteLog(username, challegeID);


                 deletedParticipates = db.deleteParticipates(username, challegeID);


                 //PLACE HOLDER
                 deletedLeaderBoard = db.deleteLeaderBoard(username);


                if (deletedChal > 0 ) {
                    Toast toast=Toast.makeText(getApplicationContext(),"User was reset",Toast.LENGTH_LONG);
                }
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"User not reset",Toast.LENGTH_LONG);
                }
            }
        }
        );
    }

    public void toChangePhotoActivity()
    {
        changeProfilePicTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AthleteProfileActivity.this,ChangePictureActivity.class);
                intent.putExtra("User Object",user);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                finish();
            }
        }
    }
}

