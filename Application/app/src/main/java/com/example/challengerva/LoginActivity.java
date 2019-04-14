package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DBHelper db;

    TextView userTextView;
    TextView passTextView;
    TextView forgotUserTextView;
    TextView forgotPassTextView;
    TextView registerTextView;

    EditText userEditText;
    EditText passEditText;

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        userTextView = findViewById(R.id.userTextView);
        passTextView = findViewById(R.id.passTextView);
        forgotUserTextView = findViewById(R.id.forgotUserTextView);
        forgotPassTextView = findViewById(R.id.forgotPassTextView);
        registerTextView = findViewById(R.id.createAccTextView);

        userEditText = findViewById(R.id.loginUserEditText);
        passEditText = findViewById(R.id.loginPassEditText);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();



                if (!hasAllFields(username,password))
                {
                    AlertMessage.alertMessage("Missing Fields",
                            "Please fill in every required field",LoginActivity.this);
                }
                Cursor userData = db.getUserData("username",username,"password",password);
                userData.moveToNext();

                if (hasAllFields(username, password) && userData.getCount() == 0)
                {
                    AlertMessage.alertMessage("Invalid Credentials",
                            "We could not find an account with this username or password",LoginActivity.this);

                    passEditText.setText("");
                }
                else if (userData.getCount() == 1)
                {
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    db.insertUser("athlete2","Password1","cio","dfij","1999-04-12","2019-04-08","coij@ghoi.com",0,"Y","athlete",null,"");
                    db.insertParticipates("athlete1",2,"2019-04-08","N","N");
                    db.insertParticipates("athlete2",2,"2019-04-08","N","N");
                    Log.d("LoginActivity","" + db.insertChallenge("first challenge23","jonathan","2019-03-26","2019-04-26",
                            "cardio",2,"team","Y","none","this is the desc",
                            3,4,2,"miles","n",0,0));
                    db.insertTeam("team 2", 2, "athlete1");
                    db.insertLog("athlete1",2,"2019-04-08",3);
                    db.insertLog("athlete1",2,"2019-04-09",20);
                    db.insertLog("athlete1",2,"2019-04-10",5);
                    db.insertLog("athlete1",2,"2019-04-11",6);
                    db.insertLog("athlete1",2,"2019-04-12",1);
                    db.insertLog("athlete1",2,"2019-04-13",6);
                    db.insertLog("athlete1",2,"2019-04-14",8);
                    db.insertLog("athlete1",2,"2019-04-15",12);
                    db.insertLog("athlete1",2,"2019-04-16",51);
                    db.insertLog("athlete1",2,"2019-04-17",7);
                    db.insertLog("athlete1",2,"2019-04-18",2);
                    db.insertLog("athlete1",2,"2019-04-19",3);

                    db.insertTeam("team 2", 2, "athlete2");
                    db.insertLog("athlete2",2,"2019-04-08",3);
                    db.insertLog("athlete2",2,"2019-04-09",20);
                    db.insertLog("athlete2",2,"2019-04-10",5);
                    db.insertLog("athlete2",2,"2019-04-11",6);
                    db.insertLog("athlete2",2,"2019-04-12",1);
                    db.insertLog("athlete2",2,"2019-04-13",6);
                    db.insertLog("athlete2",2,"2019-04-14",8);
                    db.insertLog("athlete2",2,"2019-04-15",12);
                    db.insertLog("athlete2",2,"2019-04-16",51);
                    db.insertLog("athlete2",2,"2019-04-17",7);
                    db.insertLog("athlete2",2,"2019-04-18",2);
                    db.insertLog("athlete2",2,"2019-04-19",3);


                    User user = new User(userData);
                    user.setLoggedUser(true);
                    Log.d("LoginActivity",user.getUsername());
                    Intent intent = null;
                    if (user.getAccountType().equals(User.UserType.ATHLETE))
                    {
                        intent = new Intent(LoginActivity.this,AthleteHomeActivity.class);
                    }
                    else
                    {
                        intent = new Intent(LoginActivity.this,CoachHomeActivity.class);
                    }
                    intent.putExtra("User Object", user);

                    startActivity(intent);
                    finish();
                }




            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(toRegister);

            }
        });

        forgotUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toForgotUser = new Intent(getApplicationContext(), ForgotUserActivity.class);
                startActivity(toForgotUser);

            }
        });

        final TextView forgotPassword = findViewById(R.id.forgotPassTextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toForgotPass = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(toForgotPass);

            }
        });
    }

    public static boolean hasAllFields(String user, String pass)
    {
        if (user.isEmpty() || pass.isEmpty())
        {
            return false;
        }
        return true;
    }
}
