package com.example.challengerva;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotUserActivity extends AppCompatActivity {

    EditText inputEmail;
    Button emailButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_user);
        final Context thisContext = this;

        inputEmail = findViewById(R.id.forgotUsernameEmailEditText);
        emailButton = findViewById(R.id.forgotUsernameEmailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                DBHelper db = new DBHelper(thisContext);
                Cursor checkCursor = db.userFromEmail(email);
                checkCursor.moveToFirst();


                //Checks for text in email field
                if(email.length() == 0)
                    AlertMessage.alertMessage("No Text", "Please put your email in the text field.", thisContext);
                    //Checks for email in database
                else if(checkCursor.getCount() == 0)
                    AlertMessage.alertMessage("User not found", "There is no account associated with this email.", thisContext);
                else {

                    Intent changeUsernameIntent = new Intent(getApplicationContext(), ChangeUsernameActivity.class);
                    changeUsernameIntent.putExtra("userEmail", email);
                    startActivity(changeUsernameIntent);

                }
            }
        });
    }
}
