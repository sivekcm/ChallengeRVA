package com.example.challengerva

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.util.Log
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterTesting {

    private var username_toBe: String? = null
    private var password_toBe: String? = null
    private var rePassword_toBe: String? = null
    private var wrongRePassword_toBe: String? = null
    private var email_toBe: String? = null
    private var firstName_toBe: String? = null
    private var lastName_toBe: String? = null
    private var birthDate_toBe: String? = null


    @JvmField
    @Rule
    public val activityTestRule = ActivityTestRule<RegisterActivity>(RegisterActivity::class.java)

    @Before
    fun setRegister() {
        username_toBe = "athlete1"
        password_toBe = "Password1"
        rePassword_toBe = "Password1"
        wrongRePassword_toBe = "wrongPassword"
        email_toBe = "newemail@yahoo.com"
        firstName_toBe = "Chris"
        lastName_toBe = "Sivek"
        birthDate_toBe = "4/12/1999"

    }

    @Test
    fun RegisterSuccess(){
        Log.e("@Test", "Performing successful register")
        Espresso.onView(withId(R.id.registerUserEditText)).perform(ViewActions.typeText(username_toBe!!))
        Espresso.onView(withId(R.id.registerUserEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerPassEditText)).perform(ViewActions.typeText(password_toBe!!))
        Espresso.onView(withId(R.id.registerPassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerRePassEditText)).perform(ViewActions.typeText(rePassword_toBe!!))
        Espresso.onView(withId(R.id.registerRePassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerEmailEditText)).perform(ViewActions.typeText(email_toBe!!))
        Espresso.onView(withId(R.id.registerEmailEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerFirstEditText)).perform(ViewActions.typeText(firstName_toBe!!))
        Espresso.onView(withId(R.id.registerFirstEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerLastEditText)).perform(ViewActions.typeText(lastName_toBe!!))
        Espresso.onView(withId(R.id.registerLastEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerDateTextView)).perform(ViewActions.replaceText(birthDate_toBe!!))
        Espresso.onView(withId(R.id.registerBtn)).perform(ViewActions.click())
    }

    @Test
    fun RegisterFailure() {
        Log.e("@Test", "Performing register failure")
        Espresso.onView(withId(R.id.registerUserEditText)).perform(ViewActions.typeText(username_toBe!!))
        Espresso.onView(withId(R.id.registerUserEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerPassEditText)).perform(ViewActions.typeText(password_toBe!!))
        Espresso.onView(withId(R.id.registerPassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerRePassEditText)).perform(ViewActions.typeText(wrongRePassword_toBe!!))
        Espresso.onView(withId(R.id.registerRePassEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerEmailEditText)).perform(ViewActions.typeText(email_toBe!!))
        Espresso.onView(withId(R.id.registerEmailEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerFirstEditText)).perform(ViewActions.typeText(firstName_toBe!!))
        Espresso.onView(withId(R.id.registerFirstEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerLastEditText)).perform(ViewActions.typeText(lastName_toBe!!))
        Espresso.onView(withId(R.id.registerLastEditText)).perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerDateTextView)).perform(ViewActions.replaceText(birthDate_toBe!!))
        Espresso.onView(withId(R.id.registerBtn)).perform(ViewActions.click())

    }


}


