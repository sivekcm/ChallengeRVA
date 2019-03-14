package com.example.challengerva;

import android.app.AlertDialog;
import android.content.Context;

public class AlertMessage {
    /****************************************************************************
     * alertMessage method
     * @param title the title of the message
     * @param message the content of the message*
     * Creates a popup message on the screen with information.
     */
    public void AlertMessage(String title, String message, Context thisContext) {
        AlertDialog.Builder alert = new AlertDialog.Builder(thisContext);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }
}
