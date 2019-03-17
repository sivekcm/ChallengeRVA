package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent lastIntent = getIntent();
        final String userEmail = lastIntent.getStringExtra("userEmail");

        final EditText changePasswordEditText = findViewById(R.id.changePasswordEditText);
        final EditText changePasswordReenterEditText =findViewById(R.id.changePasswordReenterEditText);

        Button changePasswordButton = findViewById(R.id.changePasswordResetButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = changePasswordEditText.getText().toString();
                String reenterPassword = changePasswordReenterEditText.getText().toString();

                DBHelper db = new DBHelper(ChangePasswordActivity.this);
                //Invalid Password
                if(!RegisterActivity.passIsValid(newPassword))
                    AlertMessage.alertMessage("Invalid Password", "Invalid Password, " +
                            "please make sure it meets minimum requirements", ChangePasswordActivity.this);

                //Passwords do not match
                else if(!RegisterActivity.passMatch(newPassword, reenterPassword))
                    AlertMessage.alertMessage("Do not match", "Passwords do not match."
                            , ChangePasswordActivity.this);

                //Successfully changes password
                else{
                    Cursor userCursor = db.userFromEmail(userEmail);
                    userCursor.moveToFirst();
                    User updatedUser = new User(userCursor);
                    updatedUser.setPassword(newPassword);
                    db.updateUser(updatedUser.getParameters());


                }


            }
        });
    }
}
