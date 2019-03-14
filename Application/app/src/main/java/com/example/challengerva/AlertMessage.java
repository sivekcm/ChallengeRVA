package com.example.challengerva;

import android.app.AlertDialog;
import android.content.Context;

public class AlertMessage {
    /****************************************************************************
     * alertMessage method
     * @param title the title of the message
     * @param message the content of the message
     *
     * Creates a popup message on the screen with information.
     */
    public static void alertMessage(Context context, String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }
}
