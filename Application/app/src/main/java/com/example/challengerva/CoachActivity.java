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
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    Button privateButton;
    Button changeBioButton;
    Button coachResetProfileBtn;
    Button coachDeleteProfileBtn;

    CircleImageView profileImage;

    DBHelper db = new DBHelper(this);

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
        privateButton = findViewById(R.id.coachProfilePrivateButton);
        changeBioButton = findViewById(R.id.coachEditBioBtn);
        coachResetProfileBtn = findViewById(R.id.coachResetProfileBtn);
        coachDeleteProfileBtn = findViewById(R.id.coachProfileDeleteButton);


        //Setting the name and user name text views to the coach name and user name
        coachNameTxtView.setText(user.getFirstName());
        coachUserNameTxtView.setText(user.getUsername());
        //Creating new instance of DBHelper to use database methods
        final DBHelper challenge = new DBHelper(CoachActivity.this);

        profileImage = findViewById(R.id.coachProfileImg);

        //Calling openChallengeActivity method
        openChallengeActivity(user);

        //calling view all method
        viewAll();

        toChangePictureActivity();

        //Setting profile picture
        byte[] image = user.getImage();
        DisplayImage.display(this,profileImage,image);

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
                Intent changeBioIntent = new Intent(CoachActivity.this, EditBioActivity.class);
                changeBioIntent.putExtra("user object", user);
                startActivity(changeBioIntent);
            }
        });
        deleteAcct(user);
        resetAcct(user);

    }
    /**
     * view all method
     * @param : none
     * @return : none
     * Displays all challenges lead by the coach using alert message
     */
        public void viewAll(){
        viewChallengesBtn = findViewById(R.id.viewAll);
        viewChallengesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CoachActivity.this,CoachViewChallenge.class);
                intent.putExtra("User Object",user);
                startActivity(intent);

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
     * Holds the alert dialog builder to show the coach their challenges
     */
    public static void showMessage(String title, String message, Context thisContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void resetAcct(final User user) {
        coachResetProfileBtn.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {

                                               }
                                           }
        );
    }

    public void deleteAcct(final User user) {
        coachDeleteProfileBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = user.getUsername();
                int deletedUser;
                deletedUser = db.deleteUser(username);
                if (deletedUser > 0) {
                    Intent intent = new Intent(CoachActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast toast = Toast.makeText(getApplicationContext(), "User was deleted", Toast.LENGTH_LONG);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "User not deleted", Toast.LENGTH_LONG);
                }

            }
        });
    }
}