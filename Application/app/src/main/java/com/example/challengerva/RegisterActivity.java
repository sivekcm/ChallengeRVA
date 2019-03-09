package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class RegisterActivity extends AppCompatActivity {

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

    EditText userEditText;
    EditText passEditText;
    EditText rePassEditText;
    EditText emailEditText;
    EditText fNameEditText;
    EditText lNameEditText;

    RadioButton athleteRB;
    RadioButton coachRB;

    Button registerBtn;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        userEditText = findViewById(R.id.registerUserEditText);
        passEditText = findViewById(R.id.registerPassEditText);
        rePassEditText = findViewById(R.id.registerRePassEditText);
        emailEditText = findViewById(R.id.registerEmailEditText);
        fNameEditText = findViewById(R.id.registerFirstEditText);
        lNameEditText = findViewById(R.id.registerLastEditText);

        athleteRB = findViewById(R.id.registerAthleteRB);
        coachRB = findViewById(R.id.registerCoachRB);

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DBHelper db = new DBHelper(RegisterActivity.this);
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();
                String rePassword = rePassEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String firstName = fNameEditText.getText().toString();
                String lastName = lNameEditText.getText().toString();
                String birthDate = dateTextView.getText().toString();

                if (!birthDate.equals("Pick a Date"))
                {
                    birthDate = formatDate(birthDate);
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String currentDate = sdf.format(date);

                Log.d("RegisterActivity","Current Date:" + currentDate);

                if (!hasAllFields(username,password,rePassword,email,firstName,lastName,birthDate))
                {
                    //empty fields message
                    alertMessage("Empty Fields", "One or more of the required fields" +
                            "were left blank. Please fill in all required fields");
                }
                else if (!passMatch(password,rePassword))
                {
                    //passwords do not match message
                    alertMessage("Passwords do not match", "The passwords you entered do not match,");
                }
                else if (!userIsValid(username) || !passIsValid(password) || !ageIsValid(currentDate,birthDate))
                {
                    //multiple fields do not meet requirements message
                    alertMessage("Invalid fields", "One or more fields do not meet the minimum requirements." +
                            "Please read the minimum requirements for a field by clicking on the *");
                }
                else if (!userIsAvail(username,db))
                {
                    //username is not available message
                    alertMessage("Username Taken", "The username entered is already " +
                            "taken, please enter a new one");
                }
                else if (!emailIsValid(email))
                {
                    //email is not valid message
                    alertMessage("Invalid Email", "The provided email address does not exist." +
                            "Please enter a valid email address");
                }
                else
                {
                    boolean success = false;
                    if (athleteRB.isChecked())
                    {
                        success = db.insertUser(username, password, firstName, lastName, birthDate,
                                currentDate, email, 0, "Y", "Athlete");
                    }
                    else if (coachRB.isChecked())
                    {
                        success = db.insertUser(username, password, firstName, lastName, birthDate,
                                currentDate, email, 0, "Y", "Coach");
                    }
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

        dateTextView = findViewById(R.id.registerDateTextView);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this,
                        android.R.style.Theme_Dialog,onDateSetListener,year,month,day);
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String stringDate = month + "/" + dayOfMonth + "/" + year;
                dateTextView.setText(stringDate);
            }
        };


    }

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

    //Username  is between 3-14 characters
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

    //User does not already exist
    public static boolean userIsAvail(String username, DBHelper myDB)
    {
        SQLiteDatabase db = myDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM User WHERE username = ?",new String[] {username});
        if (cursor.getCount() == 0)
        {
            return true;
        }
        while (cursor.moveToNext())
        {
            if (username.equals(cursor.getString(0)))
            {
                return false;
            }
        }
        return true;
    }

    //password has at least one letter and number and is at least 8 characters long
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

    //age is at least 16
    public static boolean ageIsValid(String currentDate, String birthDate)
    {
        String[] currentDateArr = currentDate.split("-");
        String[] birthDateArr = birthDate.split("-");

        Log.d("RegisterActivity","CurrentDate [0] = " + currentDateArr[0] + ", [1] = " + currentDateArr[1] + ", [2] = " + currentDateArr[2]);
        Log.d("RegisterActivity","BirthDate [0] = " + birthDateArr[0] + ", [1] = " + birthDateArr[1] + ", [2] = " + birthDateArr[2]);

        if (Integer.parseInt(currentDateArr[0]) - Integer.parseInt(birthDateArr[0]) > 16)
        {
            return true;
        }
        if (Integer.parseInt(currentDateArr[0]) - Integer.parseInt(birthDateArr[0]) == 16)
        {
            if (Integer.parseInt(currentDateArr[1]) == Integer.parseInt(birthDateArr[1]) &&
                    Integer.parseInt(currentDateArr[2]) >= Integer.parseInt(birthDateArr[2]))
            {
                return true;
            }

            if (Integer.parseInt(currentDateArr[1]) > Integer.parseInt(birthDateArr[1]))
            {
                return true;
            }
            return false;
        }
        return false;

    }

    //email exists
    public static boolean emailIsValid(String email)
    {
        return true;
    }

    //all required fields have been filled out
    public static boolean hasAllFields(String user, String pass, String rePass, String email, String fName, String lName, String birthDate)
    {
        if (user.isEmpty() || pass.isEmpty() || rePass.isEmpty() || email.isEmpty() || fName.isEmpty() || lName.isEmpty() || birthDate.equals("Pick a Date"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void alertMessage(String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }



}



