package com.example.challengerva;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayImage {

    public static void display(Context context, CircleImageView imageView, byte[] image)
    {
            if (image != null)
            {
                imageView.setImageBitmap(Utils.getImage(image));
                styleImage(context,imageView);
            }
            else
            {
                imageView.setImageResource(R.drawable.ic_default_profile_picture);
            }

    }

    public static void styleImage(Context context, CircleImageView imageView)
    {
        imageView.setBorderColor(context.getResources().getColor(R.color.Golden));
        imageView.setBorderWidth(10);
    }
}
