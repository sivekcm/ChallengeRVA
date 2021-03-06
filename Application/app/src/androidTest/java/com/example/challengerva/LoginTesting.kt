package com.example.challengerva

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.util.Log
import android.view.View

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginTesting {

    private var username_toBe: String? = null
    private var password_toBe: String? = null
    private var wrong_password_toBe: String? = null

    @JvmField
    @Rule
    public val activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Before
    fun setLogin() {
        username_toBe = "athlete1"
        password_toBe = "Password1"
        wrong_password_toBe = "wrongPassword1"

    }

    @Test
    fun loginSuccess(){
        Log.e("@Test", "Performing successful login")
        Espresso.onView(withId(R.id.loginUserEditText)).perform(ViewActions.typeText(username_toBe!!))
        Espresso.onView(withId(R.id.loginUserEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginPassEditText)).perform(ViewActions.typeText(password_toBe!!))
        Espresso.onView(withId(R.id.loginPassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginBtn)).perform(ViewActions.click())
    }

    @Test
    fun loginFailure() {
        Log.e("@Test", "Performing login failure")
        Espresso.onView(withId(R.id.loginUserEditText)).perform(ViewActions.typeText(username_toBe!!))
        Espresso.onView(withId(R.id.loginUserEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginPassEditText)).perform(ViewActions.typeText(wrong_password_toBe!!))
        Espresso.onView(withId(R.id.loginPassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginBtn)).perform(ViewActions.click())

    }


}


