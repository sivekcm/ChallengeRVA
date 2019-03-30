package com.example.challengerva;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.util.Log;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class LoginUnitTest {

    String username_toBe, password_toBe, wrong_password_toBe;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setLogin(){
        username_toBe = "chris1";
        password_toBe = "Password1";
        wrong_password_toBe = "wrongPassword1";

    }

    @Test
    public void loginSuccess(){
        Log.e("@Test", "Performing successful login");
        Espresso.onView((withId(R.id.userTextView))).perform(ViewActions.typeText(username_toBe));
        Espresso.onView(withId(R.id.passTextView)).perform(ViewActions.typeText(password_toBe));
        Espresso.onView(withId(R.id.loginBtn)).perform(ViewActions.click());
    }

    @Test
    public void loginFailure(){
        Log.e("@Test", "Performing login failure");
        Espresso.onView(withId(R.id.userTextView)).perform(ViewActions.typeText(username_toBe));
        Espresso.onView(withId(R.id.passTextView)).perform(ViewActions.typeText(wrong_password_toBe));
    }


}
