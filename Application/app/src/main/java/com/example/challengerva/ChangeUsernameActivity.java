package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeUsernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        Intent lastIntent = getIntent();
        final String userEmail = lastIntent.getStringExtra("userEmail");

        final EditText changeUsernameEditText = findViewById(R.id.changeUsernameEditText);

        Button changeUsernameButton = findViewById(R.id.changeUsernameButton);
        changeUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = changeUsernameEditText.getText().toString();
                DBHelper db = new DBHelper(ChangeUsernameActivity.this);

                if(!RegisterActivity.userIsValid(newUsername))
                    AlertMessage.alertMessage("Invalid Username", "Invalid Username, " +
                            "please make sure it meets minimum requirements", ChangeUsernameActivity.this);

                else if(!db.userIsAvail(newUsername))
                    AlertMessage.alertMessage("Username Taken", "This Username" +
                            "is already taken. Please choose another.", ChangeUsernameActivity.this);
                else{
                    Cursor userCursor = db.getUserData("email",userEmail);
                    User updatedUser = new User(userCursor);
                    updatedUser.setUsername(newUsername);
                    db.updateUser(updatedUser.getParameters());
                    updatedUser.updateUsername();

                    Intent intent = new Intent(ChangeUsernameActivity.this, CoachActivity.class);
                    intent.putExtra("User Object", updatedUser);
                    startActivity(intent);


                }


            }
        });


    }
}
