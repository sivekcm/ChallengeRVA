package com.example.challengerva;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.Button;
//import android.widget.TextView;
//
public class AthleteTeamSelectionActivity// extends AppCompatActivity {
{}
//    TextView challengeNameTxtView;
//    RecyclerView teamSelectionRecyclerView;
//    TeamAdapter adapter;
//    DBHelper db = new DBHelper(this);
//
//    Cursor teamData;
//    Cursor cursor;
//    Button currentBtn;
//    Button previousBtn;
//    User user;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.athlete_team_selection);
//
//        challengeNameTxtView = (TextView)findViewById(R.id.challengeNameTxtView);
//        teamSelectionRecyclerView = findViewById(R.id.teamSelectionRecyclerView);
//
//        String name = cursor.getString(1);
//        challengeNameTxtView.setText(name);
//
//        //showResults(db.getTeamData();
//    }
//
//    public void showResults(Cursor cursor)
//    {
//        teamSelectionRecyclerView.setLayoutManager(new LinearLayoutManager(AthleteTeamSelectionActivity.this));
//        adapter = new TeamAdapter(AthleteTeamSelectionActivity.this, cursor);
//        teamSelectionRecyclerView.swapAdapter(adapter,false);
//    }
//}