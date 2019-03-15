package com.example.challengerva;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText inputEmail;
    Button emailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Context thisContext = this;

        inputEmail = findViewById(R.id.forgotPasswordEditText);
        emailButton = findViewById(R.id.forgotPasswordUsernameButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                DBHelper db = new DBHelper(thisContext);
                Cursor checkCursor = db.userFromEmail(email);
                checkCursor.moveToFirst();


                //Checks for text in email field
                if(email.length() == 0)
                    AlertMessage.AlertMessage("No Text", "Please put your email in the text field.", thisContext);
                    //Checks for email in database
                else if(checkCursor.getCount() == 0)
                    AlertMessage.AlertMessage("User not found", "There is no account associated with this email.", thisContext);
                else {

                    Intent changePasswordIntent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                    changePasswordIntent.putExtra("userEmail", email);
                    startActivity(changePasswordIntent);

                }
            }
        });
    }
}
