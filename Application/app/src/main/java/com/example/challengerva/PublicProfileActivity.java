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

    Intent intent = getIntent();

    User user;
    User otherUser;
    final DBHelper db = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);


        Intent intent = getIntent();

        String username = intent.getStringExtra("User Object");
        Cursor userData = db.getUserData("username",username);
        userData.moveToNext();
        user = new User(userData);
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
                    Cursor res = db.getChallengeCoach(user.getUsername());
                    if (res.getCount() == 0) {
                        CoachActivity.showMessage("Nothing Found", "You have no challenges", PublicProfileActivity.this);
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();

                    while (res.moveToNext()) {
                        if (res.getString(2).equals(user.getUsername())) {
                            buffer.append("Challenge Name: " + res.getString(1) + "\n");
                            buffer.append("Challenge Description: " + res.getString(10) + "\n");
                            buffer.append("Start Date: " + res.getString(3) + "\n");
                            buffer.append("End Date" + res.getString(4) + "\n");
                            buffer.append("type: " + res.getString(5)+"\n");
                            buffer.append("difficulty: " + res.getString(6)+"\n");

                            buffer.append("Team or single: "+ res.getString(7)+"\n");
                            buffer.append("Availability: " + res.getString(8)+"\n");
                            buffer.append("Hazards: " + res.getString(9)+ "\n\n\n");

                        }

                    }

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
