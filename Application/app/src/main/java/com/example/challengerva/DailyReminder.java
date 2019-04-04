package com.example.challengerva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class DailyReminder {

    public static void dailyReminder(Context lastContext, AlarmManager alarmManager, Boolean isActive)
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
    private static boolean checkNeedsNotification() {
        return true;
    }
}

//AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        DailyReminder.dailyReminder(this, alarmManager);

