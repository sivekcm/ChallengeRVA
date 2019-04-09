package com.example.challengerva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class DailyReminder {

    public static void dailyReminder(Context lastContext, AlarmManager alarmManager, Boolean isActive, String username)
    {
        Calendar notificationCalendar = Calendar.getInstance();
        notificationCalendar.set(Calendar.HOUR_OF_DAY,20);
        Intent notificationIntent = new Intent(lastContext, NotificationReceiver.class);


        notificationIntent.putExtra("Needs Notification", isActive);
        PendingIntent pendingNotificationIntent = PendingIntent.getBroadcast(lastContext, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notificationCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingNotificationIntent);
    }

    /**
     * checkNeedsNotification method
     * @return true if this user warrants a notification, false if not
     *
     * This method determines if a user meets requirements for receiving a reminder notification.
     */
    private static boolean checkNeedsNotification(Context lastContext, String username)
    {
        DBHelper dbHelper = new DBHelper(lastContext);
        Cursor userChallenges = dbHelper.getParticipatesData("username", username);
        if(hasActiveChallenge(userChallenges) && !hasLoggedToday())
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
        }
        return false;
    }

    private static boolean hasLoggedToday()
    {
        return false;
    }
}

//AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        DailyReminder.dailyReminder(this, alarmManager);

