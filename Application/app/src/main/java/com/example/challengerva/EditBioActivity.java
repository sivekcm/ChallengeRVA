package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditBioActivity extends AppCompatActivity {
    TextView bioTextBox;
    Button bioButton;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        final User user = getIntent().getParcelableExtra("user object");

        bioTextBox = findViewById(R.id.editBioMainTextView);
        bioButton = findViewById(R.id.editBioButton);

        bioTextBox.setText(user.getBio());

        bioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bio = bioTextBox.getText().toString();
                if (bio.isEmpty())
                {
                    AlertMessage.alertMessage("Invalid input","Please fill out the field before clicking submit",EditBioActivity.this);
                }
                else {
                    user.setBio(bio);
                }
                dbHelper.updateUser(user.getParameters());
                Intent profileIntent;
                setResult(RESULT_OK);

                if(user.getAccountType() == User.UserType.COACH) {
                    profileIntent = new Intent(EditBioActivity.this, CoachActivity.class);
                }
                else profileIntent = new Intent(EditBioActivity.this, AthleteProfileActivity.class);

                profileIntent.putExtra("User Object", user);
                startActivity(profileIntent);
                finish();
            }
        });


    }
}
