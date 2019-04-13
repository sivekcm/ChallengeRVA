package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PublicProfileActivity extends AppCompatActivity {

    TextView profileUsername;
    TextView profileName;
    TextView profileBio;
    CircleImageView profileIcon;
    Button profileButton;

    User user;
    User otherUser;
    DBHelper db = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);


        Intent intent = getIntent();

        String username = intent.getStringExtra("User Object");
        Cursor userData = db.getUserData("username",username);
        userData.moveToNext();
        user = new User(userData);
        userData.close();
        otherUser = intent.getParcelableExtra("other user");


        profileUsername = (TextView) findViewById(R.id.publicProfileUsernameTextView);
        profileName = (TextView) findViewById(R.id.publicProfileNameTextView);
        profileBio = (TextView) findViewById(R.id.publicProfileBioTextView);
        profileIcon = findViewById(R.id.publicProfileImageView);
        profileButton = (Button) findViewById(R.id.publicProfileButton);

        byte[] image = otherUser.getImage();
        DisplayImage.display(this,profileIcon,image);

        //The following section handles public and private settings.

        if (otherUser.isPrivate()) {
            profileName.setText(otherUser.getUsername());
            profileUsername.setText("This user's information is private.");
            profileBio.setText("");
        } else {
            String fullName = otherUser.getFirstName() + " " + otherUser.getLastName();
            profileName.setText(fullName);
            profileUsername.setText(otherUser.getUsername());
            profileBio.setText(otherUser.getBio());
        }

        //The following section handles athlete and coach accounts
        if (user.getAccountType() == User.UserType.COACH) {
            profileButton.setText("View Challenges by this Coach");
            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toCoachChallengeIntent = new Intent(PublicProfileActivity.this,CoachViewChallenge.class);
                    toCoachChallengeIntent.putExtra("activity","PublicProfileActivity");
                    toCoachChallengeIntent.putExtra("User Object", user.getUsername());
                    toCoachChallengeIntent.putExtra("coach user", otherUser);

                    startActivity(toCoachChallengeIntent);

                }
            });

        } else
        {
            profileButton.setText("View Challenges this Athlete has taken");
            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent athleteIntent = new Intent(PublicProfileActivity.this, AthleteViewChallengeActivity.class);
                    athleteIntent.putExtra("User Object", user);
                    athleteIntent.putExtra("other user", otherUser);
                    athleteIntent.putExtra("activity", "PublicProfileActivity");

                    startActivity(athleteIntent);
                }
            });

        }

    }
}
