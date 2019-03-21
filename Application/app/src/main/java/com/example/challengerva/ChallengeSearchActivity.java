package com.example.challengerva;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class ChallengeSearchActivity extends AppCompatActivity{

    RecyclerView challengeRV;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_search);

        challengeRV = findViewById(R.id.challengeRV);
        challengeRV.setLayoutManager(new LinearLayoutManager(this));
        Cursor challengeData = db.getChallengeData();
        ChallengeAdapter adapter = new ChallengeAdapter(this, challengeData);
        challengeRV.setAdapter(adapter);

    }


}
