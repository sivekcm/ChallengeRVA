package com.example.challengerva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    User user;

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
        user = intent.getParcelableExtra("User Object");

        //instantiate DBHelper for database methods
        final DBHelper challenge = new DBHelper(ViewChallengeActivity.this);

        view_challengeName = findViewById(R.id.view_challengeName);
        view_coachName = findViewById(R.id.view_coachName);
        view_description = findViewById(R.id.view_description);
        view_startDate = findViewById(R.id.view_startDate);
        view_endDate = findViewById(R.id.view_endDate);
        view_difficulty = findViewById(R.id.view_difficulty);
        view_teamType = findViewById(R.id.view_teamType);
        view_minTeam = findViewById(R.id.view_minTeam);
        view_maxTeam = findViewById(R.id.view_maxTeam);
        view_Availability = findViewById(R.id.view_Availability);


        //connect variables to database
//        view_challengeName = (TextView) challenge.getChallengeDataAsc("2");
//        view_coachName = (TextView) challenge.getChallengeDataAsc("3");
//        view_description = (TextView) challenge.getChallengeDataAsc("11");
//        view_startDate = (TextView) challenge.getChallengeDataAsc("4");
//        view_endDate = (TextView) challenge.getChallengeDataAsc("5");
//        view_difficulty = (TextView) challenge.getChallengeDataAsc("7");
//        view_teamType = (TextView) challenge.getChallengeDataAsc("8");
//        view_minTeam = (TextView) challenge.getChallengeDataAsc("12");
//        view_maxTeam = (TextView) challenge.getChallengeDataAsc("13");
//        view_Availability = (TextView) challenge.getChallengeDataAsc("14");



//        registerChallengeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /**Direct to team creation activity**/
//                //Intent intent = new Intent(TeamCreationActivity.this,TeamCreationActivity.class);
//                //startActivity(intent);
//
//            }
//        });



    }

}
