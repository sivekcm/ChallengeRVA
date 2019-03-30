package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AthleteViewChallengeActivity extends AppCompatActivity {

    RecyclerView challengeRV;
    AthleteChallengeAdapter adapter;
    DBHelper db = new DBHelper(this);
    Cursor challengeData;
    Button currentBtn;
    Button previousBtn;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athelte_challenges_list);

        challengeRV = findViewById(R.id.athleteChallengeRV);
        currentBtn = findViewById(R.id.atheleteChallengeCurrentBtn);
        previousBtn = findViewById(R.id.athleteChallengesPreviousBtn);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");

        currentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeData = db.getParticipatesDataInnerJoin(user.getUsername(),"N");
                showResults(challengeData);
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeData = db.getParticipatesDataInnerJoin(user.getUsername(),"Y");
                showResults(challengeData);
            }
        });


    }

    /*************************************************
     * showResults method
     * @param cursor the cursor containing the challenges to be displayed
     *
     * Displays the challenges contained in cursor on the recylcerview
     */
    public void showResults(Cursor cursor)
    {
        challengeRV.setLayoutManager(new LinearLayoutManager(AthleteViewChallengeActivity.this));
        adapter = new AthleteChallengeAdapter(AthleteViewChallengeActivity.this, cursor);
        challengeRV.swapAdapter(adapter,false);
    }
}
