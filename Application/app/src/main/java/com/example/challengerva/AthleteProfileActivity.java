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

public class AthleteProfileActivity extends AppCompatActivity{
    //Declares Text View
    TextView athleteNameTxtView;
    TextView athleteProfileTxtView;
    TextView athleteUsernameTxtView;

    //Declares Buttons
    Button athleteViewChallengesBtn;
    Button athleteViewFriendsBtn;

    //Declaring Recycler View
    RecyclerView athleteChallengesRView;

    Intent intent = getIntent();
    final User user = intent.getParcelableExtra("User Object");

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athlete_profile);

        //Initializes textViews to their respective UI elements
        athleteNameTxtView = (TextView)findViewById(R.id.coachNameTxtView);
        athleteProfileTxtView = (TextView)findViewById(R.id.statusTxtView);
        athleteUsernameTxtView = (TextView)findViewById(R.id.coachUserNameTxtView);

        //Initializes Buttons to their respective UI elements
        athleteViewChallengesBtn = (Button)findViewById(R.id.createChallengeBtn);
        athleteViewFriendsBtn = (Button)findViewById(R.id.athleteViewFriendsBtn);

        //Initializing Recycler View
        athleteChallengesRView = (RecyclerView) findViewById(R.id.athleteChallengeRV);

        //Setting the name and user name text views to the athlete name and user name
        athleteNameTxtView.setText(user.getFirstName());
        athleteUsernameTxtView.setText(user.getUsername());

        final DBHelper db = new DBHelper(AthleteProfileActivity.this);
    }

    public void fillChallengesList(){
        ArrayList<String> athleteChallengeArray = new ArrayList<>();


    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
