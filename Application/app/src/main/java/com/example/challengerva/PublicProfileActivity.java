package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PublicProfileActivity extends AppCompatActivity {

    TextView profileUsername;
    TextView profileName;
    TextView profileBio;
    ImageView profileIcon;
    Button profileButton;

    Intent intent = getIntent();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User Object");

        profileUsername = (TextView) findViewById(R.id.publicProfileUsernameTextView);
        profileName = (TextView) findViewById(R.id.publicProfileNameTextView);
        profileBio = (TextView) findViewById(R.id.publicProfileBioTextView);
        profileIcon = (ImageView) findViewById(R.id.publicProfileImageView);
        profileButton = (Button) findViewById(R.id.publicProfileButton);

        //The following section handles public and private settings.

        if (user.isPrivate()) {
            profileName.setText(user.getUsername());
            profileUsername.setText("This user's information is private.");
            profileBio.setText("");
        } else {
            String fullName = user.getFirstName() + " " + user.getLastName();
            profileName.setText(fullName);
            profileUsername.setText(user.getUsername());
            profileBio.setText(user.getBio());
        }

        //The following section handles athlete and coach accounts
        if (user.getAccountType() == User.UserType.COACH) {
            profileButton.setText("View Challenges by this Coach");
            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } else
        {
            profileButton.setText("View Challenges this Athlete has taken");
            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}
