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

public class CoachActivity extends AppCompatActivity {
    TextView coachNameTxtView;
    TextView statusTxtView;
    TextView coachUserNameTxtView;

    Button changeUserNameBtn;
    Button changePasswordBtn;
    Button changeDisplayNameBtn;
    Button createChallengeBtn;
    Button viewChallengesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_profile);
        coachNameTxtView = (TextView)findViewById(R.id.coachNameTxtView);
        statusTxtView = (TextView)findViewById(R.id.statusTxtView);
        coachUserNameTxtView = (TextView)findViewById(R.id.coachUserNameTxtView);

        changeUserNameBtn = (Button)findViewById(R.id.changeUserNameBtn);
        changePasswordBtn = (Button)findViewById(R.id.changeUserNameBtn);
        changeDisplayNameBtn = (Button)findViewById(R.id.changeDisplayNameBtn);
        createChallengeBtn = (Button)findViewById(R.id.createChallengeBtn);
        viewChallengesBtn = (Button)findViewById(R.id.viewAll);

        final User test = new User();
        test.setUsername("test");
        test.firstName = "test";

        coachNameTxtView.setText(test.firstName);
        coachUserNameTxtView.setText(test.username);

        final DBHelper challenge = new DBHelper(CoachActivity.this);
        challenge.insertChallenge("testChall", "teat", "2019", "2019", "cardio", 4, "team", "availible", "none", "basic test challenge");

    }
        public void viewAll(){
        viewChallengesBtn = findViewById(R.id.viewAll);
            viewChallengesBtn.setOnClickListener(new View.OnClickListener() {
                    DBHelper db = new DBHelper(CoachActivity.this);
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getChallengeData();
                         if (res.getCount() == 0) {
                          showMessage("Nothing Found", "Nothing Found");
                               return;
                          }
                          StringBuffer buffer = new StringBuffer();

                         User test = new User();
                         test.username = "test123";
                         test.firstName = "test";

                         while (res.moveToNext()) {
                             if (res.getString(1).equals(test.username)) {
                                 buffer.append("Challenge Name:" + res.getString(1) + "\n");
                                 buffer.append("Challenge Description:" + res.getString(10) + "\n");
                                 buffer.append("Start Date:" + res.getString(3) + "End Date" + res.getString(4) + "\n");
                             }
                             showMessage("Challenges", buffer.toString());
                         }
                while (res.moveToNext()) {
                    if (res.getString(2).equals(test.getUsername())) {
                        buffer.append("Challenge Name: " + res.getString(1) + "\n");
                        buffer.append("Challenge Description: " + res.getString(10) + "\n");
                        buffer.append("Start Date: " + res.getString(3) + "End Date" + res.getString(4) + "\n");
                        buffer.append("type: " + res.getString(5)+"\n");
                        buffer.append("difficulty: " + res.getString(6)+"\n");
                        buffer.append("Team or sinlge: "+ res.getString(7)+"\n");
                        buffer.append("Availibility: " + res.getString(8)+"\n");
                        buffer.append("Hazards: " + res.getString(9)+ "\n");
                    }
                    showMessage("Challenges", buffer.toString());
                }
            }
        });
        }

    public void openChallengeActivity(){
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}