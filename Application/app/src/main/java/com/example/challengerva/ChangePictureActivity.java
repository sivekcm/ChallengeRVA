package com.example.challengerva;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChangePictureActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 7777;
    ImageView imageView;

    Button selectPhotoBtn;
    Button saveChangesBtn;

    User user;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_picture);

        final Intent intent = getIntent();

        user = intent.getParcelableExtra("User Object");

        selectPhotoBtn = findViewById(R.id.selectPictureBtn);
        saveChangesBtn = findViewById(R.id.savePictureChangesBtn);
        imageView = findViewById(R.id.changePictureImageView);

        imageView.setImageBitmap(Utils.getImage(user.getImage()));

        selectPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectPhotoIntent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(selectPhotoIntent,SELECT_PHOTO);
            }
        });

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                Cursor pass = db.getUserPassword(user.getUsername());
                pass.moveToNext();
                String password = pass.getString(0);
                String isPrivate;
                if (user.isPrivate())
                {
                    isPrivate = "Y";
                }
                else
                {
                    isPrivate = "N";
                }
                db.updateUser(user.getUsername(),user.getUsername(),password,
                        user.getFirstName(),user.getLastName(),user.getBirthDate(),
                        user.getJoinDate(),user.getEmail(),user.getChallengesCompleted(),
                        isPrivate,user.getAccountType().name(),Utils.getBytes(bitmap));

                Intent toHomeIntent = null;
                if (user.getAccountType().equals(User.UserType.ATHLETE))
                {
                    toHomeIntent = new Intent(ChangePictureActivity.this,AthleteHomeActivity.class);
                }
                else
                {
                    toHomeIntent = new Intent(ChangePictureActivity.this,CoachHomeActivity.class);
                }
                user.setImage(Utils.getBytes(bitmap));
                toHomeIntent.putExtra("User Object", user);
                toHomeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toHomeIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && intent != null)
        {
            Uri image = intent.getData();
            imageView.setImageURI(image);
        }
    }
}