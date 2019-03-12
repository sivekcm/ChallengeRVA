package com.example.challengerva;

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

public class ChallengeActivity extends AppCompatActivity{
    TextView createChallenge;
    TextView challengeType;
    TextView registrationType;

    Spinner challengeTypeList;
    Spinner registrationTypeList;


    EditText challengeName;
    EditText coachAssigned;
    EditText challengeDescription;
    EditText difficulty;
    EditText startDate;
    EditText endDate;

    Button submitChallenge;

    DatePickerDialog.OnDateSetListener startListener;
    DatePickerDialog.OnDateSetListener endListener;

    int startYear, startMonth, startDay;
    int endYear, endMonth, endDay;

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
        coachAssigned = findViewById(R.id.CoachAssigned);
        challengeDescription = findViewById(R.id.challengeDescription);
        difficulty = findViewById(R.id.difficulty);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);

        submitChallenge = findViewById(R.id.ButtonSubmitChallenge);
        submitChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(ChallengeActivity.this);

                String name = challengeName.getText().toString();
                String coach = coachAssigned.getText().toString();
                String start = startDate.getText().toString();
                String end = endDate.getText().toString();
                String type = registrationType.getText().toString();
                String diff = difficulty.getText().toString();
                String category = challengeType.getText().toString();

                //Check to see if all information has been filled
            }
        });

        startDate = findViewById(R.id.endDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //default to today's date for start
                Calendar date = Calendar.getInstance();
                startYear = date.get(Calendar.YEAR);
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

                //create a dialog box when choosing end date
                DatePickerDialog endDialog = new DatePickerDialog(ChallengeActivity.this,
                        endListener,
                        endYear,
                        endMonth,
                        endDay);
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

    /********************************************************************************
     * formatDate method - based on Chris's RegisterActivity
     * @param date: the date desired to be formatted
     * @PreCondition: the date is in the format "MM/DD/YYYY"
     * @return the input date in the format "YYYY-MM-DD"
     *
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

    public static boolean nameIsValid(){

    }

    public static boolean coachIsValid(){

    }

    public static boolean difficultyIsValid(int difficulty){
        if(difficulty >=1 && difficulty <= 3){
            return true;
        }
        return false;

    }

}
