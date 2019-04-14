package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CoachViewChallenge extends AppCompatActivity {

    RecyclerView rv;

    User user;
    User originUser;
    DBHelper db = new DBHelper(this);
    AthleteChallengeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_view_challenge);

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        if (activity.equals("PublicProfileActivity"))
        {
            user = intent.getParcelableExtra("coach user");
            String username = intent.getParcelableExtra("User Object");
            Cursor cursor = db.getUserData("username",username);
            cursor.moveToNext();
            originUser = new User(cursor);
        }
        else if (activity.equals("CoachActivity") || activity.equals("CoachHomeActivity")) {
            user = intent.getParcelableExtra("User Object");
        }

        Cursor cursor = db.getChallengeData("coach",user.getUsername());
        rv = findViewById(R.id.coachChallengeRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        showResults(cursor);
    }


    public void showResults(Cursor cursor)
    {
        adapter = new AthleteChallengeAdapter(CoachViewChallenge.this,cursor,user);
        rv.swapAdapter(adapter,false);
        toChallengeViewActivity(cursor);
    }

    public void toChallengeViewActivity(final Cursor cursor)
    {
        adapter.setOnItemClickListener(new AthleteChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                cursor.moveToPosition(position);
                Challenge challenge = new Challenge(cursor);
                Intent intent = new Intent(CoachViewChallenge.this,ViewChallengeActivity.class);
                if (originUser == null) {
                    intent.putExtra("User Object", user);
                }
                else
                {
                    intent.putExtra("User Object", originUser);
                }
                intent.putExtra("challenge",challenge);

                startActivity(intent);
            }
        });
    }
}
