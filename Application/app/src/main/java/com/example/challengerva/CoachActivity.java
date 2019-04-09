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
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*****************************************************************************
 * Class Coach Activity
 *
 * @version 3/15/2019
 *
 * Allows coach to manage profile
 */

public class CoachActivity extends AppCompatActivity {
    //Declares Text View
    TextView coachNameTxtView;
    TextView statusTxtView;
    TextView coachUserNameTxtView;
    TextView changePicBtn;

    //Declares Buttons
    Button createChallengeBtn;
    Button viewChallengesBtn;

    User user;
    /*******************************
     * OnCreate
     * @param savedInstanceState
     *
     * Runs the Activity upon launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_profile);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");

        //Initializes textViews to their respective UI elements
        coachNameTxtView = (TextView)findViewById(R.id.coachNameTxtView);
        statusTxtView = (TextView)findViewById(R.id.statusTxtView);
        coachUserNameTxtView = (TextView)findViewById(R.id.coachUserNameTxtView);
        changePicBtn = findViewById(R.id.coachChangePicTextView);
        
        //Initializes Buttons to their respective UI elements
        createChallengeBtn = (Button)findViewById(R.id.createChallengeBtn);
        viewChallengesBtn = (Button)findViewById(R.id.viewAll);


        //Setting the name and user name text views to the coach name and user name
        coachNameTxtView.setText(user.getFirstName());
        coachUserNameTxtView.setText(user.getUsername());
        //Creating new instance of DBHelper to use database methods
        final DBHelper challenge = new DBHelper(CoachActivity.this);


        //Calling openChallengeActivity method
        openChallengeActivity(user);

        //calling view all method
        viewAll(user);

        toChangePictureActivity();

    }
    /**
     * view all method
     * @param : none
     * @return : none
     * Displays all challenges lead by the coach using alert message
     */
        public void viewAll(final User user){
        viewChallengesBtn = findViewById(R.id.viewAll);
          viewChallengesBtn.setOnClickListener(new View.OnClickListener() {
                    DBHelper db = new DBHelper(CoachActivity.this);
                /**
                 * OnClick method - based on Chris's RegisterActivity
                 * @param : View v
                 * @return the input date in the format "YYYY-MM-DD"
                 * If the coach has challenges with his/her user name, then display all of their challenges
                 */
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getChallengeCoach(user.getUsername());
                         if (res.getCount() == 0) {
                          showMessage("Nothing Found", "You have no challenges");
                               return;
                          }
                          StringBuffer buffer = new StringBuffer();

                         User test = new User();
                         test.username = "test123";
                         test.firstName = "test";

                        while (res.moveToNext()) {
                         if (res.getString(2).equals(user.getUsername())) {
                                buffer.append("Challenge Name: " + res.getString(1) + "\n");
                                buffer.append("Challenge Description: " + res.getString(10) + "\n");
                                buffer.append("Start Date: " + res.getString(3) + "\n");
                                buffer.append("End Date" + res.getString(4) + "\n");
                                buffer.append("type: " + res.getString(5)+"\n");
                                buffer.append("difficulty: " + res.getString(6)+"\n");

                                buffer.append("Team or single: "+ res.getString(7)+"\n");
                                buffer.append("Availability: " + res.getString(8)+"\n");
                                buffer.append("Hazards: " + res.getString(9)+ "\n\n\n");

                            }

                    }
                        showMessage("Challenges", buffer.toString());
                }
            });
            }

    /**
     * Open challenge activity Intent method
     * @param : none
     * @return : none
     * Takes the user to the challenge activity when they click on the "make a challenge" button
     */
    public void openChallengeActivityIntent(User user){
        Intent intent = new Intent(this, ChallengeActivity.class);
        intent.putExtra("User Object",user);
        startActivity(intent);
    }
    public void openChallengeActivity(final User user){
        createChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChallengeActivityIntent(user);
            }
        });
    }

    public void toChangePictureActivity()
    {
        changePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachActivity.this,ChangePictureActivity.class);
                intent.putExtra("User Object",user);
                startActivity(intent);
            }
        });
    }

    /**
     * Show message method
     * @param : String title and String metssage
     * @return : none
     * Holds the alert dialog builder to show the coach thier challenges
     */
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}