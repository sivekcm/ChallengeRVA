package com.example.challengerva;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderboardChallenge extends AppCompatActivity {

    //Declaration of variables
    ListView leaderboard_chall_rank,
             leaderboard_chall_user,
             leaderboard_chall_ratio;

    ArrayList listRankItem,
              listUserItem,
              listRatioItem;

    ArrayAdapter adapter;

    DBHelper db = new DBHelper(this);
    Challenge challenge;

    /**********************
     * onCreate method
     * @descript: will run the activity upon launch
     * @param: (bundle) savedInstanceState
     *
     **********************/

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_leaderboard);

        //instantiate DBHelper for database methods
        leaderboard_chall_rank = findViewById(R.id.leaderboard_chall_rank);
        leaderboard_chall_user = findViewById(R.id.leaderboard_chall_user);
        leaderboard_chall_ratio = findViewById(R.id.leaderboard_chall_ratio);

        listRankItem = new ArrayList<>();
        listUserItem = new ArrayList<>();
        listRatioItem = new ArrayList<>();

        viewData();

    }

    public void viewData(){
        Cursor cursor = db.getChallengeLeaderBoardData(challenge.getChallengeName());

        if(cursor.getCount() ==0){
            Toast.makeText(this, "No users found for challenge", Toast.LENGTH_LONG);
        }
        else{
            while(cursor.moveToNext()){

            }
        }

    }


}
