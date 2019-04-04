package com.example.challengerva;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);

        Intent repeatingIntent = new Intent(context, LoginActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, repeatingIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Boolean needsNotification = intent.getBooleanExtra("Needs Notification", true);

        if(needsNotification) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentIntent(pendingIntent);
            builder.setContentTitle("No exercises logged today?");
            builder.setContentText("It looks like you haven't logged any exercises today. Don't forget!");
            builder.setSmallIcon(android.R.drawable.btn_star);
            builder.setAutoCancel(true);

            notificationManager.notify(3, builder.build());


        }





    }
}
