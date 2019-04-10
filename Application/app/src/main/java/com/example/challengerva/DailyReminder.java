package com.example.challengerva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class DailyReminder {

    public static void dailyReminder(Context lastContext, AlarmManager alarmManager, String username)
    {
        Calendar notificationCalendar = Calendar.getInstance();
        notificationCalendar.set(Calendar.HOUR_OF_DAY,20);
        Intent notificationIntent = new Intent(lastContext, NotificationReceiver.class);


        notificationIntent.putExtra("username", username);
        PendingIntent pendingNotificationIntent = PendingIntent.getBroadcast(lastContext, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notificationCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingNotificationIntent);
    }

    /**
     * checkNeedsNotification method
     * @return true if this user warrants a notification, false if not
     *
     * This method determines if a user meets requirements for receiving a reminder notification.
     */
    public static boolean checkNeedsNotification(Context lastContext, String username)
    {
        DBHelper dbHelper = new DBHelper(lastContext);
        Cursor userChallenges = dbHelper.getParticipatesData("username", username);
        if(hasActiveChallenge(userChallenges) && !loggedSomethingToday(username, userChallenges, lastContext))
            return true;
        else return false;



    }

    private static boolean hasActiveChallenge(Cursor userChallenges)
    {
        userChallenges.moveToFirst();
        while(!userChallenges.isAfterLast())
        {
            Challenge challenge = new Challenge(userChallenges);
            if(challenge.isActiveToday())
                return true;

            userChallenges.moveToNext();
        }
        return false;
    }

    private static boolean loggedSomethingToday(String username, Cursor userChallenges, Context thisContext)
    {
        userChallenges.moveToFirst();
        int challengeID;
        DBHelper dbHelper = new DBHelper(thisContext);
        while(!userChallenges.isAfterLast())
        {
            challengeID = userChallenges.getInt(0);
            if(dbHelper.hasLoggedToday(username, challengeID))
                return true;

            userChallenges.moveToNext();
        }
        return false;
    }
}
