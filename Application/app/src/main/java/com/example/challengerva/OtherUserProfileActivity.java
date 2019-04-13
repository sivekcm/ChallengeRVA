package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherUserProfileActivity extends AppCompatActivity {

    CircleImageView profilePicture;
    TextView otherUsername;
    TextView otherChallComp;
    TextView otherJoinDate;
    Button viewChallengeBtn;

    User user;
    User otherUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_profile);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");
        otherUser = intent.getParcelableExtra("other user");

        profilePicture = findViewById(R.id.otherProfileImageView);
        otherUsername = findViewById(R.id.otherProfileUsernameTextView);
        otherChallComp = findViewById(R.id.otherProfileChallCompTextView);
        otherJoinDate = findViewById(R.id.otherProfileJoinDateTextView);
        viewChallengeBtn = findViewById(R.id.otherProfileViewChallBtn);

        otherUsername.setText(otherUser.getUsername());
        otherChallComp.setText(String.valueOf(otherUser.getChallengesCompleted()));
        otherJoinDate.setText(otherUser.getJoinDate());


        viewChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherUserProfileActivity.this, AthleteViewChallengeActivity.class);
                intent.putExtra("User Object", user);
                intent.putExtra("other user", otherUser);
                intent.putExtra("activity", "OtherUserProfileActivity");

                startActivity(intent);
            }
        });
    }
}
