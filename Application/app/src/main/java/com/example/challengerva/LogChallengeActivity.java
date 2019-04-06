package com.example.challengerva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogChallengeActivity extends AppCompatActivity {

    TextView challengeNameTextView;
    TextView logDescTextView;
    TextView enterValueTextView;

    EditText enterValueEditText;

    Button submitBtn;

    User user;
    Challenge challenge;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_challenge);

        Intent intent = getIntent();
        String fromActivity = intent.getStringExtra("activity");
        if(fromActivity.equals("ViewChallengeActivity"))
        {
            user = intent.getParcelableExtra("User Object");
            challenge = intent.getParcelableExtra("challenge");
        }
        else if (fromActivity.equals("AthleteViewChallengeActivity"))
        {
            user = intent.getParcelableExtra("User Object");
            challenge = intent.getParcelableExtra("challenge");
        }

        challengeNameTextView = findViewById(R.id.logChallengeNameTextView);
        logDescTextView = findViewById(R.id.logDescTextView);
        enterValueTextView = findViewById(R.id.logEnterValueTextView);
        enterValueEditText = findViewById(R.id.logValueEditText);
        submitBtn = findViewById(R.id.logSubmitBtn);

        challengeNameTextView.setText(challenge.getChallengeName());

        String logDesc = "This challenge requires " + challenge.getLogRange() + " " + challenge.getLogUnit() + " per day";
        logDescTextView.setText(logDesc);

        String enterValue = "Enter the number of " + challenge.getLogUnit() + " you completed today:";
        enterValueTextView.setText(enterValue);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = enterValueEditText.getText().toString();
                double logValue = 0;
                try
                {
                    logValue = Double.parseDouble(value);
                    //Gets the current date, for determining the date the user signed up
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String currentDate = sdf.format(date);
                    boolean success = db.insertLog(user.getUsername(),challenge.getChallengeID(),currentDate,logValue);
                    Intent toAthleteChallengeIntent = new Intent(LogChallengeActivity.this,AthleteViewChallengeActivity.class);
                    if (success)
                    {
                        Toast.makeText(LogChallengeActivity.this,"You Successfully logged todays challenge", Toast.LENGTH_LONG).show();
                        toAthleteChallengeIntent.putExtra("User Object",user);
                        toAthleteChallengeIntent.putExtra("activity", "LogChallengeActivity");

                        startActivity(toAthleteChallengeIntent);
                    }
                    else
                    {
                        Toast.makeText(LogChallengeActivity.this,"somthing went wrong", Toast.LENGTH_LONG).show();
                    }
                }
                catch (NumberFormatException ex)
                {
                    AlertMessage.alertMessage("Wrong Format", "Please enter a number in the text box", LogChallengeActivity.this);
                }


            }
        });

    }
}
