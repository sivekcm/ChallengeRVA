package com.example.challengerva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "challenge.db";

    public static final String TABLE_USER = "User";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COL3 = "first_name";
    public static final String COL4 = "last_name";
    public static final String COL5 = "birth_date";
    public static final String COL6 = "join_date";
    public static final String COL7 = "email";
    public static final String COL8 = "challenges_comp";
    public static final String COL9 = "private";
    public static final String COL10 = "type";
    public static final String TABLE_CHALLENGE = "Challenge";
    public static final String TABLE_TEAM = "Team";
    public static final String TABLE_LEADERBOARD = "LeaderBoard";
    public static final String TABLE_PARTICIPATES = "Participates";

    public DBHelper(Context context)
    {
        super(context,"challenge.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL, " +
                "first_name TEXT NOT NULL, " +
                "last_name TEXT NOT NULL, " +
                "birth_date DATE NOT NULL, " +
                "join_date DATE NOT NULL, " +
                "email TEXT NOT NULL, " +
                "challenges_comp INTEGER, " +
                "private TEXT NOT NULL, " +
                "type TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_CHALLENGE + "(" +
                "challenge_ID INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "coach TEXT NOT NULL, " +
                "start_date DATE NOT NULL, " +
                "duration INTEGER NOT NULL, " +
                "type TEXT NOT NULL, " +
                "difficulty TEXT NOT NULL, " +
                "category TEXT NOT NULL," +
                "FOREIGN KEY(coach) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_TEAM + "(" +
                "team_id INTEGER NOT NULL, " +
                "challenge_id INTEGER NOT NULL, " +
                "user1 TEXT, " +
                "user2 TEXT, " +
                "user3 TEXT, " +
                "user4 TEXT, " +
                "PRIMARY KEY(team_id, challenge_id), " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE, " +
                "FOREIGN KEY(user1) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL, " +
                "FOREIGN KEY(user2) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL, " +
                "FOREIGN KEY(user3) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL, " +
                "FOREIGN KEY(user4) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL"+
                ")");

        db.execSQL("CREATE TABLE " + TABLE_LEADERBOARD + "(" +
                "rank INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "challenges_comp INTEGER NOT NULL, " +
                "PRIMARY KEY(rank, username), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_comp) REFERENCES " + TABLE_USER + "(challenge_comp) ON DELETE CASCADE" +
                ")");

        db.execSQL("CREATE TABLE " + TABLE_PARTICIPATES + "(" +
                "username TEXT, " +
                "challenge_id INTEGER, " +
                "join_date DATE NOT NULL, " +
                "PRIMARY KEY(username, challenge_id), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHALLENGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPATES);
        onCreate(db);

    }

    public boolean insertUser(String username, String password, String fName,
                              String lName, String birthDate, String joinDate,
                              String email, int complChall, String priv,
                              String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, username);
        cv.put(COL2, password);
        cv.put(COL3, fName);
        cv.put(COL4, lName);
        cv.put(COL5, birthDate);
        cv.put(COL6, joinDate);
        cv.put(COL7, email);
        cv.put(COL8, complChall);
        cv.put(COL9, priv);
        cv.put(COL10, type);

        long num = db.insert(TABLE_USER,null,cv);
        if (num == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

