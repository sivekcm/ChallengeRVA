package com.example.challengerva

import android.support.test.espresso.matcher.ViewMatchers
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChallengeCreationTesting{

    private var challenge_nameToBe: String? = null
    private var challenge_coachToBe: String? = null
    private var challenge_startToBe: String? = null
    private var challenge_endToBe: String? = null
    private var challenge_typeToBe: String? = null
    private var challenge_diffToBe: String? = null
    private var challenge_teamToBe: String? = null
    private var challenge_descriptToBe: String? = null
    private var challenge_min_teamToBe: String? = null
    private var challenge_max_teamToBe: String? = null
    private var challenge_log_rangeToBe: String? = null

    @JvmField
    @Rule
    public val activityTestRule = ActivityTestRule<ChallengeActivity>(ChallengeActivity::class.java)

    @Before
    fun setChallenge(){
        challenge_nameToBe = "Challenge Name"
        challenge_coachToBe = "Farhana Khan"
        challenge_startToBe = "15/04/2019"
        challenge_endToBe = "15/10/2019"
        challenge_typeToBe = "Cardio"
        challenge_diffToBe = "3"
        challenge_teamToBe = "Individual"
        challenge_descriptToBe = "Running challenge; at your leisure " +
                                 "find a path around your neighborhood that " +
                                 "is 2 miles long and complete it daily"
        challenge_min_teamToBe = "1"
        challenge_max_teamToBe = "1"
        challenge_log_rangeToBe = "2"

    }

    @Test
    fun challengeSuccess(){
        Log.e("@Test", "Performing successful challenge creation")
        Espresso.onView(ViewMatchers.withId(R.id.challengeNameEditText)).perform(ViewActions.typeText(challenge_nameToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.coachUserNameTxtView)).perform(ViewActions.typeText(challenge_coachToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.startDateEditText)).perform(ViewActions.typeText(challenge_startToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.endDateEditText)).perform(ViewActions.typeText(challenge_endToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.challengeTypeTextView)).perform(ViewActions.typeText(challenge_typeToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.difficultyTextView)).perform(ViewActions.typeText(challenge_diffToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.registrationTypeTextView)).perform(ViewActions.typeText(challenge_teamToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.challengeDescriptionEditText)).perform(ViewActions.typeText(challenge_descriptToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.minTeam)).perform(ViewActions.typeText(challenge_min_teamToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.maxTeam)).perform(ViewActions.typeText(challenge_max_teamToBe!!))
        Espresso.onView(ViewMatchers.withId(R.id.logRange)).perform(ViewActions.typeText(challenge_log_rangeToBe!!))
    }

}