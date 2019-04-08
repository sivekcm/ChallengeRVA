package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
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
    TextView difficultyTextView;

    Spinner challengeTypeListSpinner;
    Spinner registrationTypeListSpinner;
    Spinner logUnitSpinner;


    EditText challengeNameEditText;
    EditText challengeDescriptionEditText;
    EditText startDateEditText;
    EditText endDateEditText;
    EditText minTeamEditText;
    EditText maxTeamEditText;
    EditText logRangeEditText;

    RatingBar difficultyRatingBar;

    Button submitChallengeBtn;

    RadioGroup competionTypeGroup;

    RadioButton competitionTypeButton;

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

        Intent intent = getIntent();
        final User user = intent.getParcelableExtra("User Object");

        createChallengeTextView = findViewById(R.id.createChallengeTextView);
        challengeTypeTextView = findViewById(R.id.challengeTypeTextView);
        registrationTypeTextView = findViewById(R.id.registrationTypeTextView);
        difficultyTextView = findViewById(R.id.difficultyTextView);

        challengeTypeListSpinner = findViewById(R.id.ChallengeTypeSpinner);
        registrationTypeListSpinner = findViewById(R.id.registrationTypeSpinner);
        logUnitSpinner = findViewById(R.id.measurementTypeSpinner);

        challengeNameEditText = findViewById(R.id.challengeNameEditText);
        challengeDescriptionEditText = findViewById(R.id.challengeDescriptionEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        endDateEditText = findViewById(R.id.endDateEditText);
        minTeamEditText = findViewById(R.id.minTeam);
        maxTeamEditText = findViewById(R.id.maxTeam);
        logRangeEditText = findViewById(R.id.logRange);

        difficultyRatingBar = findViewById(R.id.difficultyRatingBar);

        competionTypeGroup = findViewById(R.id.competitionType);

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
                String coach = user.getUsername();
                String description = challengeDescriptionEditText.getText().toString();
                String start = startDateEditText.getText().toString();
                String end = endDateEditText.getText().toString();
                String type = registrationTypeListSpinner.toString();
                String category = challengeTypeListSpinner.toString();
                int diff = Integer.parseInt(String.valueOf((int)difficultyRatingBar.getRating()));
                int minTeam = Integer.parseInt(minTeamEditText.getText().toString());
                int maxTeam = Integer.parseInt(maxTeamEditText.getText().toString());
                int logRange = Integer.parseInt(logRangeEditText.getText().toString());

                String logUnit = logUnitSpinner.getSelectedItem().toString();

                //check to see what is selected from radio group
                //return selected item
                int selectedID = competionTypeGroup.getCheckedRadioButtonId();
                competitionTypeButton = findViewById(selectedID);
                String competitionType = competitionTypeButton.toString();

                //Execute onRatingBar
                addListenerOnRatingBar();

                //convert dates to SQL format for db
                if(!start.equals("")){
                    start = formatDate(start);
                }
                if(!end.equals("")) {
                    end = formatDate(end);
                }

                //Validate that all fields are present
                if (!hasAllFields(name,description,String.valueOf(diff),start,end))
                {
                    AlertMessage.alertMessage("Empty Fields","Please make sure all required " +
                            "fields have been filled out", ChallengeActivity.this);
                }

                //Validate the challenge name for a challenge
                else if(!nameIsValid(name)){
                    AlertMessage.alertMessage("Challenge Name Not Found",
                            "Please enter a name for the created challenge.", ChallengeActivity.this);

                }

                //Add newly created challenge object to database based on type selection
                else
                {
                    boolean success = false;

                    success = db.insertChallenge(
                                    name,
                                    coach,
                                    start,
                                    end,
                                    challengeTypeListSpinner.getSelectedItem().toString(),
                                    diff,
                                    registrationTypeListSpinner.getSelectedItem().toString(),
                                    "open",
                                    "1",
                                    description,
                                    minTeam,
                                    maxTeam,
                                    logRange,
                                    logUnit,
                                    competitionType);

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

        /*******************************************************************
         * Rating bar will identify the level of difficulty that a challenge
         * will be
         *
         * ******************************************************************/
        //will display the value from the difficulty variable automatically
        difficultyRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                difficultyTextView.setText(String.valueOf(rating));
            }
        });


        /*******************************************************************
         * onClickListener set for start date and end dates, shown below. When
         * a user clicks on the edit text box, a calendar will pop up for them
         * to choose a date in the format DD/MM/YYYY
         * ******************************************************************/
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
     * AddListenerOnRatingBar
     * @descript when changing rating via scale, difficulty value will
     * change in accordance to rating bar
     * @return TextView of rating
     */
    public void addListenerOnRatingBar(){

        //if rating bar is changed from default
        //display current difficulty rating
        difficultyRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                difficultyTextView.setText(String.valueOf(rating));
            }
        });
    }

    public static boolean hasAllFields(String name, String desc,String diff, String startDate, String endDate)
    {
        if (name.isEmpty() || desc.isEmpty() || startDate.isEmpty() || endDate.isEmpty())
        {
            return false;
        }
        return true;
    }

}
