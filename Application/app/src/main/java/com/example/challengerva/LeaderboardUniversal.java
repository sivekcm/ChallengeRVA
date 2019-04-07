package com.example.challengerva;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class LeaderboardUniversal extends AppCompatActivity {

    //Declaration of variables
    ListView leaderboard_rank,
             leaderboard_user,
             leaderboard_complete_count;

    String username;

    int rank,
        user_count;

    User user;
    DBHelper db = new DBHelper(this);

    /**********************
     * onCreate method
     * @descript: will run the activity upon launch
     * @param: (bundle) savedInstanceState
     *
     **********************/

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //instantiate DBhelper for database methods
        leaderboard_rank = findViewById(R.id.leaderboard_rank);
        leaderboard_user = findViewById(R.id.leaderboard_user);
        leaderboard_complete_count = findViewById(R.id.leaderboard_complete_count);

        rank = Integer.parseInt(db.getUniversalLeaderboardData("1").toString());
        user_count = Integer.parseInt(db.getUniversalLeaderboardData("3").toString());
        username = db.getUniversalLeaderboardData("2").toString();


    }
}
