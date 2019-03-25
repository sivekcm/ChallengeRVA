package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class ViewChallengeActivity extends AppCompatActivity {

    //Declaration of variables implemented in UI
    TextView view_challengeName;
    TextView view_coachName;
    TextView view_description;
    TextView view_startDate;
    TextView view_endDate;
    TextView view_difficulty;
    TextView view_teamType;
    TextView view_minTeam;
    TextView view_maxTeam;
    TextView view_Availability;

    Button registerChallengeButton;

    /******************
     * onCreate method
     * @descript: will run the activity upon launch
     * @param: (bundle) savedInstanceState
     *
     */

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_view);

        Intent intent = getIntent();

    }

}
