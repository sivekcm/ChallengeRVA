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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.YEAR;

public class ChallengeActivity extends AppCompatActivity{
    TextView createChallenge;
    TextView challengeType;
    TextView registrationType;

    Spinner challengeTypeList;
    Spinner registrationTypeList;


    EditText challengeName;
    EditText challengeDescription;
    EditText difficulty;
    EditText startDate;
    EditText endDate;

    Button submitChallenge;

    DatePickerDialog.OnDateSetListener startListener;
    DatePickerDialog.OnDateSetListener endListener;

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

        createChallenge = findViewById(R.id.createChallenge);
        challengeType = findViewById(R.id.challengeTypeTextView);
        registrationType = findViewById(R.id.registrationTypeTextView);

        challengeTypeList = findViewById(R.id.ChallengeType);
        registrationTypeList = findViewById(R.id.registrationType);

        challengeName = findViewById(R.id.challengeName);
        challengeDescription = findViewById(R.id.challengeDescription);
        difficulty = findViewById(R.id.difficulty);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);



        submitChallenge = findViewById(R.id.ButtonSubmitChallenge);
        /*******************************************************************
         * SubmitChallenge button is initialized and when clicked,
         * all the provided information will be checked and validated for
         * accuracy.
         *******************************************************************/
        submitChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ChallengeActivity.this);

                String name = challengeName.getText().toString();
                String start = startDate.getText().toString();
                String end = endDate.getText().toString();
                String type = registrationType.getText().toString();
                int diff = Integer.parseInt(difficulty.getText().toString());
                String category = challengeType.getText().toString();
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
                    alertMessage("Challenge Name Not Found",
                            "Please enter a name for the created challenge.");
                }

                //Validate the difficulty of the challenge
                if(!difficultyIsValid(diff)){
                    alertMessage("Difficulty Out Of Range",
                            "Please enter a difficulty level 1 to 3.");
                }

                //Add newly created challenge object to database based on type selection
                else{
                    boolean success = false;
                    if(registrationTypeList.getSelectedItem().toString() == "Indidivudal"){
                        if(challengeTypeList.getSelectedItem().toString() == "Cardio"){
                            success = db.insertChallenge(count_ID++, name, )

                        }
                    }

                }



            }
        });

        startDate = findViewById(R.id.startDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //default to today's date for start
                Calendar date = Calendar.getInstance();
                startYear = date.get(YEAR);
                startMonth = date.get(Calendar.MONTH);
                startDay = date.get(Calendar.DAY_OF_MONTH);

                //create a dialog box when choosing start date
                DatePickerDialog startDialog = new DatePickerDialog(ChallengeActivity.this,
                        startListener,
                        startYear,
                        startMonth,
                        startDay);

                //illustrate dialog
                startDialog.show();

            }
        });

        endDate = findViewById(R.id.startDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //default to today's date for start
                Calendar date2 = Calendar.getInstance();
                endYear = date2.get(YEAR);
                endMonth = date2.get(Calendar.MONTH);
                endDay = date2.get(Calendar.DAY_OF_MONTH);

                //create a dialog box when choosing end date
                DatePickerDialog endDialog = new DatePickerDialog(ChallengeActivity.this,
                        endListener,
                        endYear,
                        endMonth,
                        endDay);

                //illustrate dialog
                endDialog.show();
            }
        });

        //define default onDateSetListener obj
        startListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String stringStartDate = month + "/" + day + "/" + year;
                startDate.setText(stringStartDate);
            }
        };
        endListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String stringEndDate = month + "/" + day + "/" + year;
                endDate.setText(stringEndDate);
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

    /****************************************************************************
     * alertMessage method implemented from RegisterActivity
     * @param title the title of the message
     * @param message the content of the message
     *
     * Creates a popup message on the screen with information.
     */
    public void alertMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
