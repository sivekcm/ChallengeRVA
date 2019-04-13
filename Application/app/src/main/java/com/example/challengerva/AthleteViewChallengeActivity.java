package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class AthleteViewChallengeActivity extends AppCompatActivity {

    RecyclerView challengeRV;
    AthleteChallengeAdapter adapter;
    DBHelper db = new DBHelper(this);
    Cursor challengeData;
    Button currentBtn;
    Button previousBtn;
    User user;
    User originUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athelte_challenges_list);

        challengeRV = findViewById(R.id.athleteChallengeRV);
        currentBtn = findViewById(R.id.atheleteChallengeCurrentBtn);
        previousBtn = findViewById(R.id.athleteChallengesPreviousBtn);

        Intent intent = getIntent();
        String fromActivity = intent.getStringExtra("activity");
        if (fromActivity.equals("AthleteHomeActivity") || fromActivity.equals("LogChallengeActivity")) {
            user = intent.getParcelableExtra("User Object");
        }
        else if (fromActivity.equals("PublicProfileActivity"))
        {
            user = intent.getParcelableExtra("other user");
            originUser = intent.getParcelableExtra("User Object");
        }

        final boolean isLoggedTemp = user.isLoggedUser();

        currentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeData = db.getParticipatesDataInnerJoin(user.getUsername(),"N");
                adapter = new AthleteChallengeAdapter(AthleteViewChallengeActivity.this,challengeData,user);
                showResults(adapter);
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setLoggedUser(false);
                challengeData = db.getParticipatesDataInnerJoin(user.getUsername(),"Y");
                adapter = new AthleteChallengeAdapter(AthleteViewChallengeActivity.this,challengeData,user);
                showResults(adapter);
                user.setLoggedUser(isLoggedTemp);
            }
        });




    }

    /*************************************************
     * showResults method
     *
     * Displays the challenges contained in cursor on the recylcerview
     */
    public void showResults(AthleteChallengeAdapter adapter)
    {
        challengeRV.setLayoutManager(new LinearLayoutManager(AthleteViewChallengeActivity.this));
        challengeRV.swapAdapter(adapter,false);
        adapter.setOnItemClickListener(new AthleteChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                challengeData.moveToPosition(position);
                Cursor challengeCursor = db.getChallengeData("name",challengeData.getString(1));
                challengeCursor.moveToNext();
                Challenge challenge = new Challenge(challengeCursor);
                Intent toChallengeViewIntent = new Intent(AthleteViewChallengeActivity.this,ViewChallengeActivity.class);
                toChallengeViewIntent.putExtra("User Object",user);
                toChallengeViewIntent.putExtra("challenge",challenge);
                startActivity(toChallengeViewIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                finish();
            }
        }
    }
}
