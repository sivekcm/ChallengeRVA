package com.example.challengerva;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderboardUniversal extends AppCompatActivity {

    //Declaration of variables
    ListView leaderboard_rank,
             leaderboard_user,
             leaderboard_complete_count;

    ArrayList listRankItem,
              listUserItem,
              listCompCountItem;

    ArrayAdapter adapter;

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

        listRankItem = new ArrayList<>();
        listUserItem = new ArrayList<>();
        listCompCountItem = new ArrayList<>();

        viewData();

    }

    public void viewData(){
        Cursor cursor = db.getUniversalLeaderBoardData(); //get entire data sorted by challenges completed

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No users found on leaderboard", Toast.LENGTH_LONG).show();
        }
        else{
            while(cursor.moveToNext()){
                listRankItem.add(cursor.getString(0)); //index 0 is rank
                listUserItem.add(cursor.getString(1)); //index 1 is username
                listCompCountItem.add(cursor.getString(2)); //index 2 is challenges completed
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listRankItem);
            leaderboard_rank.setAdapter(adapter);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listUserItem);
            leaderboard_user.setAdapter(adapter);

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCompCountItem);
            leaderboard_complete_count.setAdapter(adapter);

        }
    }
}
