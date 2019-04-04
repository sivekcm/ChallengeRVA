package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PublicProfileActivity extends AppCompatActivity {

    TextView profileUsername;
    TextView profileName;
    TextView profileBio;
    ImageView profileIcon;

    Intent intent = getIntent();
    final User user = intent.getParcelableExtra("User Object");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        Intent intent = getIntent();
        final User user = intent.getParcelableExtra("User Object");

        profileUsername = (TextView) findViewById(R.id.publicProfileUsernameTextView);
        profileName = (TextView) findViewById(R.id.publicProfileNameTextView);
        profileBio = (TextView) findViewById(R.id.publicProfileBioTextView);
        profileIcon = (ImageView) findViewById(R.id.publicProfileImageView);

        profileUsername.setText(user.getUsername());
        profileName.setText(user.getFirstName());

    }
}
