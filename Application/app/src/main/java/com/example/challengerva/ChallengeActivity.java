package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.YEAR;

public class ChallengeActivity extends AppCompatActivity{
    TextView createChallengeTextView;
    TextView challengeTypeTextView;
    TextView registrationTypeTextView;

    Spinner challengeTypeListSpinner;
    Spinner registrationTypeListSpinner;


    EditText challengeNameEditText;
    EditText challengeDescriptionEditText;
    EditText difficultyEditText;
    EditText startDateEditText;
    EditText endDateEditText;

    Button submitChallengeBtn;

    DatePickerDialog.OnDateSetListener startDateListener;
    DatePickerDialog.OnDateSetListener endDateListener;

    int startYear, startMonth, startDay;
    int endYear, endMonth, endDay;

    /*********************************************************
     * OnCreate will find the values given via android input
     * from the app
     ********************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createchallenge_form);

        createChallengeTextView = findViewById(R.id.createChallengeTextView);
        challengeTypeTextView = findViewById(R.id.challengeTypeTextView);
        registrationTypeTextView = findViewById(R.id.registrationTypeTextView);

        challengeTypeListSpinner = findViewById(R.id.ChallengeTypeSpinner);
        registrationTypeListSpinner = findViewById(R.id.registrationTypeSpinner);

        challengeNameEditText = findViewById(R.id.challengeNameEditText);
        challengeDescriptionEditText = findViewById(R.id.challengeDescriptionEditText);
        difficultyEditText = findViewById(R.id.difficultyEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        endDateEditText = findViewById(R.id.endDateEditText);



        submitChallengeBtn = findViewById(R.id.submitChallengeBtn);
        /*******************************************************************
         * SubmitChallenge button is initialized and when clicked,
         * all the provided information will be checked and validated for
         * accuracy.
         * ******************************************************************/
        submitChallengeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ChallengeActivity.this);

                String name = challengeNameEditText.getText().toString();
                String description = challengeDescriptionEditText.getText().toString();
                String start = startDateEditText.getText().toString();
                String end = endDateEditText.getText().toString();
                String type = registrationTypeListSpinner.toString();
                int diff = Integer.parseInt(difficultyEditText.getText().toString());
                String category = challengeTypeListSpinner.toString();
                int count_ID = 0; //default value



                //convert dates to SQL format for db
                if(!start.equals("Start Date")){
                    start = formatDate(start);
                }
                if(!end.equals("End Date")){
                    end = formatDate(end);
                }

                //Validate the challenge name for a challenge
                if(!nameIsValid(name)){
                    AlertMessage.alertMessage("Challenge Name Not Found",
                            "Please enter a name for the created challenge.", ChallengeActivity.this);

                }

                //Validate the difficulty of the challenge
                else if(!difficultyIsValid(diff))
                {
                    AlertMessage.alertMessage("Difficulty Out Of Range",
                            "Please enter a difficulty level 1 to 3.", ChallengeActivity.this);

                }

                //Add newly created challenge object to database based on type selection
                else
                {
                    boolean success = false;

                    success = db.insertChallenge(
                                    name,
                                    "1",
                                    start,
                                    end,
                                    challengeTypeListSpinner.getSelectedItem().toString(),
                                    diff,
                                    registrationTypeListSpinner.getSelectedItem().toString(),
                                    "open",
                                    "1",
                                    description);

                    if(success){
                            Toast.makeText(ChallengeActivity.this,
                                    "Challenge Successfully Created!",
                                    Toast.LENGTH_LONG).show();
                    }
                    else {
                            Toast.makeText(ChallengeActivity.this,
                                    "Challenge Creation Failed. Please Try Again.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //default to today's date for start
                Calendar date = Calendar.getInstance();
                startYear = date.get(YEAR);
                startMonth = date.get(Calendar.MONTH);
                startDay = date.get(Calendar.DAY_OF_MONTH);

                //create a dialog box when choosing start date
                DatePickerDialog startDialog = new DatePickerDialog(ChallengeActivity.this,
                        startDateListener,
                        startYear,
                        startMonth,
                        startDay);

                //illustrate dialog
                startDialog.show();

            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //default to today's date for start
                Calendar date2 = Calendar.getInstance();
                endYear = date2.get(YEAR);
                endMonth = date2.get(Calendar.MONTH);
                endDay = date2.get(Calendar.DAY_OF_MONTH);

                //create a dialog box when choosing end date
                DatePickerDialog endDialog = new DatePickerDialog(ChallengeActivity.this,
                        endDateListener,
                        endYear,
                        endMonth,
                        endDay);

                //illustrate dialog
                endDialog.show();
            }
        });

        //define default onDateSetListener obj
        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String stringStartDate = month + "/" + day + "/" + year;
                startDateEditText.setText(stringStartDate);
            }
        };

        //define default onDateSetListener obj
        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String stringStartDate = month + "/" + day + "/" + year;
                endDateEditText.setText(stringStartDate);
            }
        };

    }

    /**
     * formatDate method - based on Chris's RegisterActivity
     * @param date: the date desired to be formatted
     * @PreCondition: the date is in the format "MM/DD/YYYY"
     * @return the input date in the format "YYYY-MM-DD"
     * Formats the date to a format recognized by SQL, "YYYY-MM-DD"
     */
    public static String formatDate(String date)
    {
        List<String> dateArr = new ArrayList<>(Arrays.asList(date.split("/")));
        if (Integer.parseInt(dateArr.get(0)) < 10)
        {
            dateArr.set(0, "0" + dateArr.get(0));
        }

        if (Integer.parseInt(dateArr.get(1)) < 10)
        {
            dateArr.set(1, "0" + dateArr.get(1));
        }

        date = dateArr.get(2) + "-" + dateArr.get(0) + "-" + dateArr.get(1);
        return date;
    }

    /*
     * Check to see if the challenge name is valid
     * @param name of the challenge
     * @return boolean value; true if name is valid false if not
     */
    public static boolean nameIsValid(String name){
        //default for name
        if(name.length()>1){
            return true;
        }
        return false;
    }

    /**
     * Check to see if a valid difficulty level is given
     * @param difficulty (int)
     * @return boolean value true if valid level given,
     * false if another level is given or if a string is attempted
     * to be passed through
     */
    public static boolean difficultyIsValid(int difficulty){
        if(difficulty >=1 && difficulty <= 3){
            return true;
        }
        return false;
    }

}
