package com.example.challengerva;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText inputEmail;
    Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Context thisContext = this;

        inputEmail = findViewById(R.id.forgotPasswordEditText);
        emailButton = findViewById(R.id.forgotPasswordUsernameButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                DBHelper db = new DBHelper(thisContext);
                Cursor checkCursor = db.getUserData("email",email);
                checkCursor.moveToFirst();


                //Checks for text in email field
                if(email.length() == 0)
                    AlertMessage.alertMessage("No Text", "Please put your email in the text field.", thisContext);
                    //Checks for email in database
                else if(checkCursor.getCount() == 0)
                    AlertMessage.alertMessage("User not found", "There is no account associated with this email.", thisContext);
                else {

                    //Intent changePasswordIntent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                    //changePasswordIntent.putExtra("userEmail", email);
                    //startActivity(changePasswordIntent);
                    sendEmail();

                }
            }
        });
    }

    private void sendEmail(){
        String email = inputEmail.getText().toString().trim();
        String subject = "ChallengeRVA: Reset Password";
        String text = "Time to reset password";

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("Valid email required");
            inputEmail.requestFocus();
        }

        RetrofitClient.getInstance()
                .getApi()
                .sendEmail("khanfr2@vcu.edu", email,  subject, text)
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    JSONObject obj = new JSONObject(response.body().string());
                    Toast.makeText(ForgotPasswordActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                } catch(JSONException | IOException e ){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
