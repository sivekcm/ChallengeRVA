package com.example.challengerva

import android.util.Log
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
    private var challenge_availToBe: String? = null
    private var challenge_descriptToBe: String? = null
    private var challenge_min_teamToBe: String? = null
    private var challenge_max_teamToBe: String? = null
    private var challenge_log_rangeToBe: String? = null
    private var challenge_log_unitToBe: String? = null

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
        challenge_availToBe = "Available"
        challenge_descriptToBe = "Running challenge; at your leisure " +
                                 "find a path around your neighborhood that " +
                                 "is 2 miles long and complete it daily"
        challenge_min_teamToBe = "1"
        challenge_max_teamToBe = "1"
        challenge_log_rangeToBe = "2"
        challenge_log_unitToBe = "miles"

    }

    @Test
    fun challengeSuccess(){
        Log.e("@Test", "Performing successful challenge creation")
    }

}