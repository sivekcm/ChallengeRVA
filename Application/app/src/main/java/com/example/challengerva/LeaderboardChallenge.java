package com.example.challengerva;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LeaderboardChallenge extends AppCompatActivity {

    //Declaration of variables
    ListView leaderboard_chall_rank,
             leaderboard_chall_user,
             leaderboard_chall_weight,
             leaderboard_chall_total;

    ArrayList listRankItem,
              listUserItem,
              listWeightItem,
              listTotalItem;

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
        leaderboard_chall_weight = findViewById(R.id.leaderboard_chall_weight);
        leaderboard_chall_total = findViewById(R.id.leaderboard_chall_total);

        listRankItem = new ArrayList<>();
        listUserItem = new ArrayList<>();
        listWeightItem = new ArrayList<>();
        listTotalItem = new ArrayList<>();

        viewData();

    }

    /******************************************
     * viewData()
     * @descript: view the data from the given
     * columns of leaderboard
     *
     *******************************************/

    public void viewData(){
        Cursor cursor = db.getChallengeLeaderBoardData(challenge.getChallengeName());

        if(cursor.getCount() ==0){
            Toast.makeText(this, "No users found for challenge", Toast.LENGTH_LONG);
        }
        else{
            while(cursor.moveToNext()){
                listRankItem.add(cursor.getString(1)); //rank column
                listUserItem.add(cursor.getString(2)); //username column
                listWeightItem.add(cursor.getString(3)); //logs from user column
                listTotalItem.add(cursor.getString(4)); //total for challenge
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listRankItem);
            leaderboard_chall_rank.setAdapter(adapter);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listUserItem);
            leaderboard_chall_user.setAdapter(adapter);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listWeightItem);
            leaderboard_chall_weight.setAdapter(adapter);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTotalItem);
            leaderboard_chall_total.setAdapter(adapter);
        }

    }


}
