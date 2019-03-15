package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*****************************************************************************
 * Class RegisterActivity
 *
 * @version 3/10/2019
 *
 * Registers user accounts for the app.
 */
public class RegisterActivity extends AppCompatActivity {

    //Declares Text Views
    TextView titleTextView;
    TextView descTextView;
    TextView userTextView;
    TextView passTextView;
    TextView rePassTextView;
    TextView emailTextView;
    TextView fNameTextView;
    TextView lNameTextView;
    TextView birthTextView;
    TextView dateTextView;

    //Declares Edit Texts
    EditText userEditText;
    EditText passEditText;
    EditText rePassEditText;
    EditText emailEditText;
    EditText fNameEditText;
    EditText lNameEditText;

    //Declares radio buttons
    RadioButton athleteRB;
    RadioButton coachRB;

    //declares register button
    Button registerBtn;

    //declares onDateSetListener
    DatePickerDialog.OnDateSetListener onDateSetListener;

    /*******************************
     * OnCreate
     * @param savedInstanceState
     *
     * Runs the Activity upon launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializes textViews to their respective UI elements
        titleTextView = findViewById(R.id.registerTitleTextView);
        descTextView = findViewById(R.id.registerDescTextView);
        userTextView = findViewById(R.id.registerUserTextView);
        passTextView = findViewById(R.id.registerPassTextView);
        rePassTextView = findViewById(R.id.registerRePassTextView);
        emailTextView = findViewById(R.id.registerEmailTextView);
        fNameTextView = findViewById(R.id.registerFirstTextView);
        lNameTextView = findViewById(R.id.registerLastTextView);
        birthTextView = findViewById(R.id.registerBirthTextView);
        dateTextView = findViewById(R.id.registerDateTextView);

        //Initializes editTexts to their respective UI elements
        userEditText = findViewById(R.id.registerUserEditText);
        passEditText = findViewById(R.id.registerPassEditText);
        rePassEditText = findViewById(R.id.registerRePassEditText);
        emailEditText = findViewById(R.id.registerEmailEditText);
        fNameEditText = findViewById(R.id.registerFirstEditText);
        lNameEditText = findViewById(R.id.registerLastEditText);

        //Initializes radio buttons to their respective UI elements
        athleteRB = findViewById(R.id.registerAthleteRB);
        coachRB = findViewById(R.id.registerCoachRB);

        //Initializes register button
        registerBtn = findViewById(R.id.registerBtn);

        //Sets the on click listener for the register button
        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            /**************************************************************
             * registerBRN.onClick
             * @param v
             *
             * Defines what the register button does when It is clicked.
             * The app will validate that all information is present and
             * meets the minimum requirements. Upon successful validation
             * of user-entered data, the user will be inserted into
             * the User table in the database
             *
             */
            @Override
            public void onClick(View v)
            {
                //Instance of DBHelper to call database methods
                DBHelper db = new DBHelper(RegisterActivity.this);

                //Creates variables for the information entered into each editText box
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();
                String rePassword = rePassEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String firstName = fNameEditText.getText().toString();
                String lastName = lNameEditText.getText().toString();
                String birthDate = dateTextView.getText().toString();

                //Will convert date to correct SQL format ("YYYY-MM-DD") only if Date was entered
                if (!birthDate.equals("Pick a Date"))
                {
                    birthDate = formatDate(birthDate);
                }

                //Gets the current date, for determining the date the user signed up
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String currentDate = sdf.format(date);

                /***********************************************
                 * User Validation Section
                 * Precedence goes from top to bottom (error for missing information will have precedence over error for taken username
                 ************************************************/

                //Checks that the user has entered a value into every required field (none of the fields are empty)
                if (!hasAllFields(username,password,rePassword,email,firstName,lastName,birthDate))
                {
                    //Popup message warning user of missing information

                    AlertMessage.alertMessage("Empty Fields", "One or more of the required fields" +
                            "were left blank. Please fill in all required fields", RegisterActivity.this);

                }

                //Checks that the password and re-enter password match
                else if (!passMatch(password,rePassword))
                {
                    //Popup message warning user passwords do not match
                    AlertMessage.alertMessage("Passwords do not match", "The passwords you entered do not match,", RegisterActivity.this);

                }

                //Checks that the username, password, and age match the minimum requirements to register as a user.
                else if (!userIsValid(username) || !passIsValid(password) || !ageIsValid(currentDate,birthDate))
                {
                    //Popup warning user that one or more fields do not meet minimum requirements

                    AlertMessage.alertMessage("Invalid fields", "One or more fields do not meet the minimum requirements." +
                            "Please read the minimum requirements for a field by clicking on the *",RegisterActivity.this);

                }

                //Checks that the username is not taken
                else if (!db.userIsAvail(username))
                {
                    //Popup message warning user that the username is already taken

                    AlertMessage.alertMessage("Username Taken", "The username entered is already " +
                            "taken, please enter a new one",RegisterActivity.this);

                }

                //Checks that the email is valid
                else if (!emailIsValid(email))
                {
                    //popup message warning user that the email is not valid

                    AlertMessage.alertMessage("Invalid Email", "The provided email address does not exist." +
                           "Please enter a valid email address",RegisterActivity.this);
                }

                //Will get to this else statements only if all information is present and valid
                else
                {
                    boolean success = false;
                    //If athlete radio button is checked, User information will be inserted as an Athlete
                    if (athleteRB.isChecked())
                    {
                        success = db.insertUser(username, password, firstName, lastName, birthDate,
                                currentDate, email, 0, "Y", "Athlete");
                    }
                    //If coach radio button is checked, User information wil lbe inserted as a Coach
                    else if (coachRB.isChecked())
                    {
                        success = db.insertUser(username, password, firstName, lastName, birthDate,
                                currentDate, email, 0, "Y", "Coach");
                    }

                    //the insert method returns true or false.
                    //If registration succeeded, quick fading message will show saying registration was successful
                    //if Registration was not successful, Something went wrong not on the user's end
                    if (success)
                    {
                        Toast.makeText(RegisterActivity.this,"Registered Successfully!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Oops! Something went wrong",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            /********************************************
             * date onClick
             * @param v
             *
             * Defines what happens when a user clicks on the
             * "pick a date" TextView.
             */
            @Override
            public void onClick(View v) {

                //Defaults to today's date
                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);

                //Creates the dialog box that pops up on click
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this,
                        android.R.style.Theme_Light_Panel,onDateSetListener,year,month,day);

                //displays the dialog box
                dialog.show();
            }
        });

        //defines the default format for the date when seleted
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String stringDate = month + "/" + dayOfMonth + "/" + year;
                dateTextView.setText(stringDate);
            }
        };


    }


    /********************************************************************************
     * formatDate method
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

    /*********************************************************
     * userIsValid method
     * @param username the username to be checked
     * @return ture if username is valid, false if not valid
     *
     * Checks if username meets minimum requirements: Username
     * must be between 2 and 14 characters.
     */
    public static boolean userIsValid(String username)
    {
        if (username.length() > 2 && username.length() <= 14)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /****************************************************************
     * passIsValid method
     * @param password the password to be checked
     * @return true if password meets minimum requirements, false otherwise
     *
     * Checks if passwords meets minimum requirements: is Alphanumeric and
     * is at least 8 characters
     */
    public static boolean passIsValid(String password)
    {
        if (password.matches("^[a-zA-Z0-9]*$") && password.length() >= 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /****************************************************************
     * passMatch method
     * @param password the password to be checked
     * @param rePassword the password from the "re enter password" field
     * @return true if passwords match, false if they don't
     *
     * Checks if passwords match
     */
    public static boolean passMatch(String password, String rePassword)
    {
        if (password.equals(rePassword))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /********************************************************************
     * ageIsValid method
     * @param currentDate the current date
     * @param birthDate the birthdate entered by the user
     * @return true if the user is 16 or older, false otherwise
     *
     * Compares today's date with the birthdate of the user to determine
     * if they are old enough to user the app
     */
    public static boolean ageIsValid(String currentDate, String birthDate)
    {
        //gets arrays for the current date and birth date
        String[] currentDateArr = currentDate.split("-");
        String[] birthDateArr = birthDate.split("-");

        //If the user is older than 16 return true
        if (Integer.parseInt(currentDateArr[0]) - Integer.parseInt(birthDateArr[0]) > 16)
        {
            return true;
        }

        //If the user is supposed to/already turned 16 this year
        if (Integer.parseInt(currentDateArr[0]) - Integer.parseInt(birthDateArr[0]) == 16)
        {
            //If the user turned 16  this month, return true
            if (Integer.parseInt(currentDateArr[1]) == Integer.parseInt(birthDateArr[1]) &&
                    Integer.parseInt(currentDateArr[2]) >= Integer.parseInt(birthDateArr[2]))
            {
                return true;
            }

            //If the user turned 16 this year, return true
            if (Integer.parseInt(currentDateArr[1]) > Integer.parseInt(birthDateArr[1]))
            {
                return true;
            }
            //return false if the user is supposed to turn 16 this year, but has not yet
            return false;
        }
        //return false if the user is under 16 and is not supposed to turn 16 this year
        return false;

    }

    /***************************************************************
     * emailIsVail method
     * @param email the email to be checked
     * @return true if email is valid, false otherwise
     *
     * checks if email is valid.
     */
    public static boolean emailIsValid(String email)
    {
        return true;
    }

    /**********************************************************************
     * hasAllFields method
     * @param user useraneme
     * @param pass password
     * @param rePass re-enter password field
     * @param email email
     * @param fName first name
     * @param lName last name
     * @param birthDate birthDate
     * @return true if all the information has been entered, false if missing info
     */
    public static boolean hasAllFields(String user, String pass, String rePass, String email, String fName, String lName, String birthDate)
    {
        //Only one field needs to be empty in order to fail.
        //For birthdate, the default text is "Pick a Date", so whether or not that text equals that will determine if the user entered data for that field
        if (user.isEmpty() || pass.isEmpty() || rePass.isEmpty() || email.isEmpty() || fName.isEmpty() || lName.isEmpty() || birthDate.equals("Pick a Date"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }



}



