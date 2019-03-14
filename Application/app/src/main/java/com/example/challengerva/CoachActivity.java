package com.example.challengerva;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
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

public class CoachActivity extends AppCompatActivity {
    TextView coachNameTxtView;
    TextView statusTxtView;
    TextView coachUserNameTxtView;

    Button changeUserNameBtn;
    Button changePasswordBtn;
    Button changeDisplayNameBtn;
    Button createChallengeBtn;
    Button viewChallengesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_profile);
        coachNameTxtView = (TextView) findViewById(R.id.coachNameTxtView);
        statusTxtView = findViewById(R.id.statusTxtView);
        coachUserNameTxtView = findViewById(R.id.coachUserNameTxtView);

        changeUserNameBtn = findViewById(R.id.changeUserNameBtn);
        changePasswordBtn = findViewById(R.id.changeUserNameBtn);
        changeDisplayNameBtn = findViewById(R.id.changeDisplayNameBtn);
        createChallengeBtn = findViewById(R.id.createChallengeBtn);

        ViewAll();

    }
        public void ViewAll(){
        viewChallengesBtn = findViewById(R.id.viewAll);
            viewChallengesBtn.setOnClickListener(new View.OnClickListener() {
                    DBHelper db = new DBHelper(CoachActivity.this);
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getChallengeData();
                         if (res.getCount() == 0) {
                          showMessage("Nothing Found", "Nothing Found");
                               return;
                          }
                          StringBuffer buffer = new StringBuffer();

                         User test = new User();
                         while (res.moveToNext()) {
                             if (res.getString(1).equals(test)) {
                                 buffer.append("Challenge Name:" + res.getString(1) + "\n");
                                 buffer.append("Challenge Description:" + res.getString(10) + "\n");
                                 buffer.append("Start Date:" + res.getString(3) + "End Date" + res.getString(4) + "\n");
                             }
                             showMessage("Challenges", buffer.toString());
                         }
                    }
                }

            );
        }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
