package com.example.challengerva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "challenge.db";

    public static final String TABLE_USER = "User";
    public static final String USER_COL1 = "username";
    public static final String USER_COL2 = "password";
    public static final String USER_COL3 = "first_name";
    public static final String USER_COL4 = "last_name";
    public static final String USER_COL5 = "birth_date";
    public static final String USER_COL6 = "join_date";
    public static final String USER_COL7 = "email";
    public static final String USER_COL8 = "challenges_comp";
    public static final String USER_COL9 = "private";
    public static final String USER_COL10 = "type";

    public static final String TABLE_CHALLENGE = "Challenge";
    public static final String CHAL_COL1 = "challenge_id";
    public static final String CHAL_COL2 = "name";
    public static final String CHAL_COL3 = "coach";
    public static final String CHAL_COL4 = "start_date";
    public static final String CHAL_COL5 = "duration";
    public static final String CHAL_COL6 = "type";
    public static final String CHAL_COL7 = "difficulty";
    public static final String CHAL_COL8 = "category";

    public static final String TABLE_TEAM = "Team";
    public static final String TEAM_COL1 = "team_id";
    public static final String TEAM_COL2 = "challenge_id";
    public static final String TEAM_COL3 = "user1";
    public static final String TEAM_COL4 = "user2";
    public static final String TEAM_COL5 = "user3";
    public static final String TEAM_COL6 = "user4";

    public static final String TABLE_LEADERBOARD = "LeaderBoard";
    public static final String LB_COL1 = "rank";
    public static final String LB_COL2 = "username";
    public static final String LB_COL3 = "challenges_comp";

    public static final String TABLE_PARTICIPATES = "Participates";
    public static final String PART_COL1 = "username";
    public static final String PART_COL2 = "challenge_id";
    public static final String PART_COL3 = "join_date";

    public DBHelper(Context context)
    {
        super(context,"challenge.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
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
                ") ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHALLENGE + "(" +
                "challenge_ID INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "coach TEXT NOT NULL, " +
                "start_date DATE NOT NULL, " +
                "duration INTEGER NOT NULL, " +
                "type TEXT NOT NULL, " +
                "difficulty TEXT NOT NULL, " +
                "category TEXT NOT NULL," +
                "FOREIGN KEY(coach) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE" +
                ") ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TEAM + "(" +
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
                ") ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LEADERBOARD + "(" +
                "rank INTEGER NOT NULL, " +
                "username TEXT NOT NULL, " +
                "challenges_comp INTEGER NOT NULL, " +
                "PRIMARY KEY(rank, username), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenges_comp) REFERENCES " + TABLE_USER + "(challenges_comp) ON DELETE CASCADE" +
                ") ");

        db.execSQL("CREATE TABLE " + TABLE_PARTICIPATES + "(" +
                "username TEXT, " +
                "challenge_id INTEGER, " +
                "join_date DATE NOT NULL, " +
                "PRIMARY KEY(username, challenge_id), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE" +
                ") ");
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
        cv.put(USER_COL1, username);
        cv.put(USER_COL2, password);
        cv.put(USER_COL3, fName);
        cv.put(USER_COL4, lName);
        cv.put(USER_COL5, birthDate);
        cv.put(USER_COL6, joinDate);
        cv.put(USER_COL7, email);
        cv.put(USER_COL8, complChall);
        cv.put(USER_COL9, priv);
        cv.put(USER_COL10, type);

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

    public boolean insertChallenge(int challengeID, String name, String coach,
                                   String startDate, int duration, String type,
                                   String diff, String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHAL_COL1, challengeID);
        cv.put(CHAL_COL2, name);
        cv.put(CHAL_COL3, coach);
        cv.put(CHAL_COL4, startDate);
        cv.put(CHAL_COL5, duration);
        cv.put(CHAL_COL6, type);
        cv.put(CHAL_COL7, diff);
        cv.put(CHAL_COL8, category);

        long num = db.insert(TABLE_CHALLENGE,null,cv);
        if (num == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insertTeam(int team_id, int challenge_id, String user1,
                              String user2, String user3, String user4)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_COL1, team_id);
        cv.put(TEAM_COL2, challenge_id);
        cv.put(TEAM_COL3, user1);
        cv.put(TEAM_COL4, user2);
        cv.put(TEAM_COL5, user3);
        cv.put(TEAM_COL6, user4);

        long num = db.insert(TABLE_TEAM,null,cv);
        if (num == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insertLeaderBoard(int rank, String username, int challengesComp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, rank);
        cv.put(LB_COL2, username);
        cv.put(LB_COL3, challengesComp);

        long num = db.insert(TABLE_LEADERBOARD,null,cv);
        if (num == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insertParticipates(String username, int challengeID, String joinDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PART_COL1, username);
        cv.put(PART_COL2, challengeID);
        cv.put(PART_COL3, joinDate);

        long num = db.insert(TABLE_PARTICIPATES,null,cv);
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

