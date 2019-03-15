package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        String oldUsername = lastIntent.getStringExtra("oldUsername");

        final EditText changeUsernameEditText = findViewById(R.id.changeUsernameEditText);

        Button changeUsernameButton = findViewById(R.id.changeUsernameButton);
        changeUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = changeUsernameEditText.getText().toString();
                DBHelper db = new DBHelper(ChangeUsernameActivity.this)

                if(!RegisterActivity.userIsValid(newUsername))
                    AlertMessage.AlertMessage("Invalid Username", "Invalid Username, " +
                            "please make sure it meets minimum requirements", ChangeUsernameActivity.this);

                else if(!db.userIsAvail(newUsername))
                    AlertMessage.AlertMessage("Username Taken", "This Username" +
                            "is already taken. Please choose another.", ChangeUsernameActivity.this);
                else{

                }


            }
        });


    }
}
