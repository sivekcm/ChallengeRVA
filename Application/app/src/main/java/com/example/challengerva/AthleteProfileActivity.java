package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
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
    TextView changeProfilePicTextView;

    //Declares Buttons
    Button athleteViewChallengesBtn;
    Button athleteViewFriendsBtn;
    Button resetProfileBtn;
    Button deleteAcctBtn;

    //Declaring Recycler View
    RecyclerView athleteChallengesRView;

    ImageView imageView;

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
        athleteViewFriendsBtn = (Button) findViewById(R.id.athleteViewFriendsBtn);
        deleteAcctBtn = (Button) findViewById(R.id.deleteAcctBtn);
        resetProfileBtn = (Button) findViewById(R.id.resetProfileBtn);

        imageView = findViewById(R.id.athleteProfilePictureImageView);

        //Initializing Recycler View
        athleteChallengesRView = (RecyclerView) findViewById(R.id.athleteChallengeRV);

        //Setting the name and user name text views to the athlete name and user name
        athleteNameTxtView.setText(user.getFirstName());
        athleteUsernameTxtView.setText(user.getUsername());

        Bitmap bitmap = Utils.getImage(user.getImage());
        imageView.setImageBitmap(bitmap);

        toChangePhotoActivity();
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
                Cursor teamUserData = db.getTeamData("username", username);
                String teamName = teamUserData.getString(0);

                while (userData.moveToNext()) {
                    int challegeID = (challangeUserData.getInt(0));
                    String challengeIDSt = String.valueOf(challangeUserData.getInt(0));
                    int deletedUser = db.deleteUser(username);

                    int deletedChal = db.deleteChallenge(challengeIDSt);
                    int deletedLog = db.deleteLog(username, challegeID);
                    int deletedTeam = db.deleteTeam(teamName, challegeID);
                    // need cursors participates, notification, and leaderboard

                    //int deleted participate
                    //int deleted notification table
                    
                    //PLACE HOLDER
                    int deletedLeaderBoard = db.deleteLeaderBoard(12, username);

                    if (deletedChal > 0 || deletedLog > 0 || deletedTeam > 0 || deletedUser > 0 ||deletedLeaderBoard >0)
                        Toast.makeText(AthleteProfileActivity.this, "Your account was deleted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AthleteProfileActivity.this, "Account not deleted", Toast.LENGTH_LONG).show();
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
                Cursor teamUserData = db.getTeamData("username", username);
                String teamName = teamUserData.getString(0);

                while (challangeUserData.moveToNext()){
                    int challegeID = (challangeUserData.getInt(0));
                    String challengeIDSt = String.valueOf(challangeUserData.getInt(0));

                    int deletedChal= db.deleteChallenge(challengeIDSt);
                    int deletedLog = db.deleteLog(username, challegeID);
                    int deletedTeam = db.deleteTeam(teamName, challegeID);
                    // need cursors participates, notification, and leaderboard

                    //int deleted participate
                    //int deleted notification table

                    //PLACE HOLDER
                    int deletedLeaderBoard = db.deleteLeaderBoard(12, username);

                    if (deletedChal > 0 || deletedLog >0 || deletedTeam >0 || deletedLeaderBoard >0)
                        Toast.makeText(AthleteProfileActivity.this, "Your Challenges, Logs, Leaderboard score, and Teams were deleted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AthleteProfileActivity.this, "Account not Reset", Toast.LENGTH_LONG).show();

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
                startActivity(intent);
            }
        });
    }
}


