package com.example.challengerva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    //Database
    public static final String DB_NAME = "challenge.db";

    //User Table
    public static final String TABLE_USER = "User";
    public static final String USER_COL1 = "username";
    public static final String USER_COL2 = "password";
    public static final String USER_COL3 = "first_name";
    public static final String USER_COL4 = "last_name";
    public static final String USER_COL5 = "birth_date";
    public static final String USER_COL6 = "join_date";
    public static final String USER_COL7 = "email";
    public static final String USER_COL8 = "challenges_count";
    public static final String USER_COL9 = "private";
    public static final String USER_COL10 = "type";
    public static final String USER_COL11 = "image_data";
    public static final String USER_COL12 = "bio";

    //Challenge Table
    public static final String TABLE_CHALLENGE = "Challenge";
    public static final String CHAL_COL1 = "challenge_id";
    public static final String CHAL_COL2 = "name";
    public static final String CHAL_COL3 = "coach";
    public static final String CHAL_COL4 = "start_date";
    public static final String CHAL_COL5 = "end_date";
    public static final String CHAL_COL6 = "type";
    public static final String CHAL_COL7 = "difficulty";
    public static final String CHAL_COL8 = "team_or_single";
    public static final String CHAL_COL9 = "availability";
    public static final String CHAL_COL10 = "health_hazards";
    public static final String CHAL_COL11 = "description";
    public static final String CHAL_COL12 = "min_team";
    public static final String CHAL_COL13 = "max_team";
    public static final String CHAL_COL14 = "log_range";
    public static final String CHAL_COL15 = "log_unit";

    public static final String CHAL_COL16 = "competitionType";
    public static final String CHAL_COL17 = "number_ratings";
    public static final String CHAL_COL18 = "total_rating";


    //Team Table
    public static final String TABLE_TEAM = "Team";
    public static final String TEAM_COL1 = "team_name";
    public static final String TEAM_COL2 = "challenge_id";
    public static final String TEAM_COL3 = "username";

    //Universal LeaderBoard Table
    public static final String TABLE_LEADERBOARD = "Universal_LeaderBoard";
    public static final String LB_COL1 = "rank";
    public static final String LB_COL2 = "username";
    public static final String LB_COL3 = "challenges_count";

    //Challenge specific LeaderBoard Table
    public static final String TABLE_LEADERBOARD_CHALLENGE = "Challenge_LeaderBoard";
    public static final String LBCHALL_COL1 = "rank";
    public static final String LBCHALL_COL2 = "username";
    public static final String LBCHALL_COL3 = "challenges_weight";

    //Participates Table
    public static final String TABLE_PARTICIPATES = "Participates";
    public static final String PART_COL1 = "username";
    public static final String PART_COL2 = "challenge_id";
    public static final String PART_COL3 = "join_date";
    public static final String PART_COL4 = "completed";
    public static final String PART_COL5 = "has_rated";

    //Log Table
    public static final String TABLE_LOG = "Log";
    public static final String LOG_COL1 = "username";
    public static final String LOG_COL2 = "challenge_id";
    public static final String LOG_COL3 = "log_date";
    public static final String LOG_COL4 = "log_value";

    //Notification Table
    public static final String TABLE_NOTIFICATION = "Notification";
    public static final String NOTIFICATION_COL1 = "username";
    public static final String NOTIFICATION_COL2 = "type";

    /***************************
     * DBHeler Constructor
     * @param context: current context, usually "this"
     *
     *
     * Create DBHelper object to use the Database
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    /****************************
     * OnCreate method
     * @param db: SQLiteDatabase object
     *
     * Creates the tables of the
     * Database using SQLite.
     * Called automatically when
     * activity is launched
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //User table
        //Stores user information
        //Unique usernames
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" +
                "username TEXT COLLATE NOCASE PRIMARY KEY, " +
                "password TEXT NOT NULL, " +
                "first_name TEXT NOT NULL, " +
                "last_name TEXT NOT NULL, " +
                "birth_date DATE NOT NULL, " +
                "join_date DATE NOT NULL, " +
                "email TEXT NOT NULL, " +
                "challenges_count INTEGER NOT NULL, " +
                "private TEXT NOT NULL, " +
                "type TEXT NOT NULL, " +
                "image_data BLOB, " +
                "bio TEXT" +
                ") ");

        //Challenge table
        //Stores challenge data
        //Unique challenge ID
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHALLENGE + "(" +
                "challenge_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "coach TEXT NOT NULL, " +
                "start_date DATE NOT NULL, " +
                "end_date DATE NOT NULL, " +
                "type TEXT NOT NULL, " +
                "difficulty TEXT NOT NULL, " +
                "team_or_single TEXT NOT NULL, " +
                "availability TEXT NOT NULL, " +
                "health_hazards TEXT, " +
                "description TEXT, " +
                "min_team INTEGER NOT NULL, " +
                "max_team INTEGER NOT NULL, " +
                "log_range INTEGER NOT NULL, " +
                "log_unit TEXT NOT NULL, " +
                "competition_type TEXT NOT NULL, " +
                "number_ratings INTEGER," +
                "total_rating INTEGER," +
                "FOREIGN KEY(coach) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE" +
                ") ");

        //Team table
        //Stores team data
        //unique team id and challenge id
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TEAM + "(" +
                "team_name TEXT NOT NULL, " +
                "challenge_id INTEGER NOT NULL, " +
                "username TEXT, " +
                "PRIMARY KEY(team_name, challenge_id, username), " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE, " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL" +
                ") ");

        //Universal LeaderBoard table
        //Stores leaderBoard data
        //unique rank and username pairing
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LEADERBOARD + "(" +
                "rank INTEGER NOT NULL, " +
                "username TEXT NOT NULL, " +
                "challenges_count INTEGER NOT NULL, " +
                "PRIMARY KEY(rank, username), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenges_count) REFERENCES " + TABLE_USER + "(challenges_count) ON DELETE CASCADE" +
                ") ");

        //Challenge-specific LeaderBoard table
        //Stores leaderBoard data for specified challenge
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LEADERBOARD_CHALLENGE + "(" +
                    "rank INTEGER NOT NULL, " +
                    "username TEXT NOT NULL, " +
                    "challenges_weight DOUBLE NOT NULL, " +
                    "PRIMARY KEY(rank, username), " +
                    "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                    "FOREIGN KEY(challenges_weight) REFERENCES " + TABLE_USER + "(challenges_weight) ON DELETE CASCADE" +
                    ") ");

        //Participates table
        //Stores data of what challenges a user is participating in
        //unique username and challenge id pairing
        db.execSQL("CREATE TABLE " + TABLE_PARTICIPATES + "(" +
                "username TEXT, " +
                "challenge_id INTEGER, " +
                "join_date DATE NOT NULL, " +
                "completed TEXT NOT NULL," +
                "has_rated TEXT NOT NULL," +
                "PRIMARY KEY(username, challenge_id), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE" +
                ") ");

        //Log table
        //stores data of all the logs of a user for a challenge
        //unique username, challenge id, and date pairing
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LOG + "(" +
                "username TEXT NOT NULL, " +
                "challenge_id INTEGER NOT NULL, " +
                "log_date DATE NOT NULL, " +
                "log_value REAL NOT NULL, " +
                "PRIMARY KEY(username, challenge_id, log_date), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE" +
                ") ");

        //Notification table
        //stores data of what notifications the user has
        //unique username and type pairing
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NOTIFICATION + "(" +
                "username TEXT, " +
                "type TEXT, " +
                "PRIMARY KEY(username,type), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE" +
                ") ");
    }

    /*********************************
     * onUpgrade method
     * @param db: instance of SQLiteDatabase
     * @param oldVersion
     * @param newVersion
     *
     * Creates updated database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHALLENGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTICIPATES);
        onCreate(db);

    }

    /***********************************************************************
     * insertUser method
     * @param username
     * @param password
     * @param fName: first name
     * @param lName: last name
     * @param birthDate: MUST BE IN FORMAT "YYYY-MM-DD"
     * @param joinDate: date user signed up. MUST BE IN FORMAT "YYYY-MM-DD"
     * @param email: email of user
     * @param complChall: the integer number of challenges completed by user
     * @param priv: is Profile Private, "Y" or "N"
     * @param type: "athlete" or "coach"
     * @param bio: biography
     * @return false if insert failed, true if data was inserted successfully
     *
     * This method inserts data (a full row) into the User table. All dates
     * must be formatted "YYYY-MM-DD" otherwise insert will fail. Username
     * cannot match the username of another user in the user table, otherwise
     * insert will fail. The only parameter that can be null is complChall.
     * This method just inserts a new data entry, It can not change the values
     * of an existing data entry.
     */
    public boolean insertUser(String username, String password, String fName,
                              String lName, String birthDate, String joinDate,
                              String email, int complChall, String priv,
                              String type, byte[] image, String bio) {
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
        cv.put(USER_COL11, image);
        cv.put(USER_COL12, bio);

        long num = db.insert(TABLE_USER, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    /************************************************************************
     * insertChallenge method
     * @param name: name of challenge
     * @param coach: the username (not name) of the user that is the coach of the challenge
     * @param startDate: start date of challenge "YYYY-MM-DD"
     * @param endDate: end date of challenge "YYYY-MM-DD"
     * @param type: type, e.g Strength, Cardio, etc
     * @param diff: the difficulty
     * @param teamOrSingle: if challenge is team or single
     * @param availability: if challenge is open or closed
     * @param hazards: possible health hazards
     * @param description: description of challenge
     * @param numberRatings: The total number of ratings
     * @param totalRating: The running total rating
     * @return false if insert fails, true if data is inserted successfully
     *
     * This method inserts a new data entry (a full row) into the challenge
     * table. ChallengeID cannot match another ChallengeID in the challenge
     * table, otherwise insert will fail. All dates must be specified in the
     * format "YYYY-MM-DD", otherwise insert will fail. No parameters are
     * allowed to be null in this method.
     */
    public boolean insertChallenge(String name, String coach,
                                   String startDate, String endDate, String type,
                                   int diff, String teamOrSingle, String availability,
                                   String hazards, String description, int minTeam,
                                   int maxTeam, int logRange, String logUnit, String competitionType,
                                   int numberRatings, int totalRating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHAL_COL2, name);
        cv.put(CHAL_COL3, coach);
        cv.put(CHAL_COL4, startDate);
        cv.put(CHAL_COL5, endDate);
        cv.put(CHAL_COL6, type);
        cv.put(CHAL_COL7, diff);
        cv.put(CHAL_COL8, teamOrSingle);
        cv.put(CHAL_COL9, availability);
        cv.put(CHAL_COL10, hazards);
        cv.put(CHAL_COL11, description);
        cv.put(CHAL_COL12, minTeam);
        cv.put(CHAL_COL13, maxTeam);
        cv.put(CHAL_COL14, logRange);
        cv.put(CHAL_COL15, logUnit);

        cv.put(CHAL_COL16, competitionType);
        cv.put(CHAL_COL17, numberRatings);
        cv.put(CHAL_COL18, totalRating);


        long num = db.insert(TABLE_CHALLENGE, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**********************************************************************
     * insertTeam method
     * @param team_name: name for team
     * @param challenge_id id for challenge referring to challenge table (unique pairing with team id)
     * @param user
     * @return false if insert fails, true if data is inserted successfully
     *
     * This method inserts a new data entry in the the team table. No two data
     * entries may have the same team_id and challenge_id pairing. team id and
     * challenge id cannot be null.
     */
    public boolean insertTeam(String team_name, int challenge_id, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_COL1, team_name);
        cv.put(TEAM_COL2, challenge_id);
        cv.put(TEAM_COL3, user);


        long num = db.insert(TABLE_TEAM, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    /*************************************************************************
     * insertLeaderBoard method
     * @param rank: the rank of the user
     * @param username: the user (unique pairing with rank
     * @param challengesCount: the amount of challenges completed by user
     * @return false if insert fails, true if data is inserted successfully
     *
     * This methods inserts a new data entry (a full row) into the leaderboard
     * table. No two data entries may have the same rank and username pairing.
     * Only challengesComp can be null
     */
    public boolean insertUniversalLeaderBoard(int rank, String username, int challengesCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, rank);
        cv.put(LB_COL2, username);
        cv.put(LB_COL3, challengesCount);

        long num = db.insert(TABLE_LEADERBOARD, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertChallengeLeaderBoard(int rank, String username, int challengesCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, rank);
        cv.put(LB_COL2, username);
        cv.put(LB_COL3, challengesCount);

        long num = db.insert(TABLE_LEADERBOARD, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }



    /********************************************************************************
     * insertParticipates method
     * @param username: the user
     * @param challengeID: the id of the challenge (unique pairing with username)
     * @param joinDate: date that user joined challenge "YYYY-MM-DD"
     * @param completed: if the user has completed the challenge (keeps track of the user's past challenges)
     * @return false if insert fails, true if data is inserted successfully
     *
     * this method inserts a new data entry (a full row) into the participates table.
     * The participates table contains data of the users that a participating in a
     * certain challenge (i.e when a user completes a challenge, the data entry
     * in this table associated with the user should be removed). No two data entries
     * may have the same username and challengeID pairing. All dates must be formatted
     * "YYYY-MM-DD" otherwise insert will fail. No parameters are allowed to be null.
     */
    public boolean insertParticipates(String username, int challengeID, String joinDate, String completed, String hasRated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PART_COL1, username);
        cv.put(PART_COL2, challengeID);
        cv.put(PART_COL3, joinDate);
        cv.put(PART_COL4, completed);
        cv.put(PART_COL5, hasRated);


        long num = db.insert(TABLE_PARTICIPATES, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertLog(String username, int challengeID, String date, double value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LOG_COL1, username);
        cv.put(LOG_COL2, challengeID);
        cv.put(LOG_COL3, date);
        cv.put(LOG_COL4, value);


        long num = db.insert(TABLE_LOG, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertNotification(String username, NotificationType type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTIFICATION_COL1,username);
        cv.put(NOTIFICATION_COL2,type.name());

        long num = db.insert(TABLE_NOTIFICATION, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }


    /***********************************************************************
     * updateUser method
     * @param oldUsername: the row you want to update
     * @param newUsername: the username you want to replace the old one with
     * @param password: the password
     * @param fName: first name
     * @param lName: last name
     * @param birthDate: MUST BE IN FORMAT "YYYY-MM-DD"
     * @param joinDate: date user signed up. MUST BE IN FORMAT "YYYY-MM-DD"
     * @param email: email of user
     * @param complChall: the integer number of challenges completed by user
     * @param priv: is Profile Private, "Y" or "N"
     * @param type: "athlete" or "coach"
     * @param bio: biography
     * @return false if update failed, true if data was updated successfully
     *
     * This method updates a row in the User table at the specified username. All dates
     * must be formatted "YYYY-MM-DD" otherwise insert will fail. Username must already
     * exist in table or else update will fail. The only parameter that can be null is complChall.
     */
    public boolean updateUser(String oldUsername, String newUsername, String password, String fName,
                              String lName, String birthDate, String joinDate,
                              String email, int complChall, String priv,
                              String type, byte[] image, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COL1, newUsername);
        cv.put(USER_COL2, password);
        cv.put(USER_COL3, fName);
        cv.put(USER_COL4, lName);
        cv.put(USER_COL5, birthDate);
        cv.put(USER_COL6, joinDate);
        cv.put(USER_COL7, email);
        cv.put(USER_COL8, complChall);
        cv.put(USER_COL9, priv);
        cv.put(USER_COL10, type);
        cv.put(USER_COL11, image);
        cv.put(USER_COL12, bio);

        long result = db.update(TABLE_USER, cv, "username = ?", new String[]{oldUsername});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * updateUser method
     *
     * @param parameterArray: an Object array of all necessary parameters
     */
    public boolean updateUser(Object[] parameterArray) {

        //No Password Change
        if (parameterArray[2] == null) {
            Cursor userCursor = getUserData("username",parameterArray[0].toString());
            userCursor.moveToFirst();
            parameterArray[2] = userCursor.getString(1);
        }

        return updateUser((String) parameterArray[0], (String) parameterArray[1], (String) parameterArray[2], (String) parameterArray[3],
                (String) parameterArray[4], (String) parameterArray[5], (String) parameterArray[6], (String) parameterArray[7],
                (int) parameterArray[8], (String) parameterArray[9], (String) parameterArray[10], (byte[]) parameterArray[11], (String)parameterArray[12]);
    }

    /************************************************************************
     * updateChallenge method
     * @param oldChallengeID: The row you want to update
     * @param newChallengeID: the challengeID you want to replace the old one with
     * @param name: name of challenge
     * @param coach: the username (not name) of the user that is the coach of the challenge
     * @param startDate: start date of challenge "YYYY-MM-DD"
     * @param endDate: end date of challenge "YYYY-MM-DD"
     * @param type: type, e.g Strength,Cardio, etc
     * @param diff: the difficulty
     * @param teamOrSingle: if challenge is team or single
     * @param availability: if challenge is open or closed
     * @param hazards: possible health hazards
     * @param description: description of challenge
     * @param numberRatings: the number of ratings
     * @param totalRating: The running total of ratings
     * @return false if update fails, true if data is updated successfully
     *
     * This method updates a row in the challenge table at the specified
     * challengeID. All dates must be specified in the format "YYYY-MM-DD",
     * otherwise update will fail. No parameters are allowed to be null in this method.
     */
    public boolean updateChallenge(int oldChallengeID, int newChallengeID, String name, String coach,
                                   String startDate, String endDate, String type,
                                   String diff, String teamOrSingle, String availability,
                                   String hazards, String description, int numberRatings, int totalRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHAL_COL1, newChallengeID);
        cv.put(CHAL_COL2, name);
        cv.put(CHAL_COL3, coach);
        cv.put(CHAL_COL4, startDate);
        cv.put(CHAL_COL5, endDate);
        cv.put(CHAL_COL6, type);
        cv.put(CHAL_COL7, diff);
        cv.put(CHAL_COL8, teamOrSingle);
        cv.put(CHAL_COL9, availability);
        cv.put(CHAL_COL10, hazards);
        cv.put(CHAL_COL11, description);
        cv.put(CHAL_COL17, numberRatings);
        cv.put(CHAL_COL18, totalRating);
        long result = db.update(TABLE_CHALLENGE, cv, "challenge_id = ?", new String[]{Integer.valueOf(oldChallengeID).toString()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**********************************************************************
     * updateTeam method
     * @param oldTeamName: team id of the row you want to change
     * @param oldChallengeID: challenge id of the row you want to change (paired with team id)
     * @param newTeamName: the Team id you want to replace the old one with
     * @param newChallengeID: the challenge id you want to replace the old one with
     * @return false if update fails, true if data is updated successfully
     *
     * This method updates the data of a row at the specified Team_id and
     * challenge_id pairing. Team_id and challenge_id must already exist
     * or else update will fail.
     */
    public boolean updateTeam(String oldTeamName, int oldChallengeID, String newTeamName, int newChallengeID, String oldUser, String newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_COL1, newTeamName);
        cv.put(TEAM_COL2, newChallengeID);
        cv.put(TEAM_COL3, newUser);

        long result = db.update(TABLE_TEAM, cv, "team_name = ? AND challenge_id = ? AND username = ?", new String[]{oldTeamName, Integer.valueOf(oldChallengeID).toString(), oldUser});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*************************************************************************
     * updateLeaderBoard method
     * @param oldRank: the rank of the row you want to update
     * @param oldUsername: the username of the row you want to update (paired with oldRank)
     * @param newRank: The rank you want to replace the old one with.
     * @param newUsername: the username you want to replace the old one with.
     * @param challengesCount: the amount of challenges completed by user
     * @return false if update fails, true if data is updated successfully
     *
     * This methods updates a row in the leaderboard
     * table at the specififed rank and username pairing.
     * the rank and username must already exist in the table,
     * otherwise update will fail. Only challengesComp can
     * be null.
     */
    public boolean updateLeaderBoard(int oldRank, String oldUsername, int newRank, String newUsername, int challengesCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, newRank);
        cv.put(LB_COL2, newUsername);
        cv.put(LB_COL3, challengesCount);

        long result = db.update(TABLE_LEADERBOARD, cv, "rank = ? AND username = ?", new String[]{Integer.valueOf(oldRank).toString(), oldUsername});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /********************************************************************************
     * updateParticipates method
     * @param oldUsername: the username of the row you want to update
     * @param oldChallengeID: the challenge id of the row you want to update (paired with username)
     * @param newUsername: the username you want to replace the old one with
     * @param newChallengeID: the challenge id you want to replace the old one with
     * @param joinDate: date that user joined challenge "YYYY-MM-DD"
     * @param completed: whether the user has completed the challenge or not
     * @param hasRated: Whether the user has rated the challenge or not
     * @return false if update fails, true if data is updated successfully
     *
     * this method updates a row in the participates table at the specified username
     * and challengeID pairing. The participates table contains data of the users that a participating in a
     * certain challenge (i.e when a user completes a challenge, the data entry
     * in this table associated with the user should be removed). The username and
     * challengeID must already exist or the update will fail. All dates must be formatted
     * "YYYY-MM-DD" otherwise insert will fail. No parameters are allowed to be null.
     */
    public boolean updateParticipates(String oldUsername, int oldChallengeID, String newUsername, int newChallengeID, String joinDate, String completed, String hasRated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PART_COL1, newUsername);
        cv.put(PART_COL2, newChallengeID);
        cv.put(PART_COL3, joinDate);
        cv.put(PART_COL4, completed);
        cv.put(PART_COL5, hasRated);

        long result = db.update(TABLE_PARTICIPATES, cv, "username = ? AND challenge_id = ?", new String[]{oldUsername, Integer.valueOf(oldChallengeID).toString()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateNotification(String currentUsername, String newUsername, NotificationType currentType, NotificationType newType)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOTIFICATION_COL1,newUsername);
        cv.put(NOTIFICATION_COL2,newType.name());

        long num = db.update(TABLE_NOTIFICATION, cv, "username = ? AND type = ?", new String[] {currentUsername, currentType.name()});
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**********************************************************************************************
     * getStringData method
     * @param table: the table you want to get data from
     * @param column: the column you want to get data from
     * @param primaryKey1: For tables that have 1 unique identifier, enter the value of the primary key that
     *                     is associated with the row you want to get data from.
     * @param primaryKey2: Use this in conjunction with primaryKey1 if table has 2 unique identifiers.
     *                     If table only has 1 unique identifier, then pass null as this parameter
     * @return The String representation of an individual data cell from a column that is of type String.
     *
     *
     *  Do not use this method for retrieving integer data. This method is work-in-progress and may
     *  cause unexpected results or errors in its current state.
     */
    public String getStringData(String table, String column, String primaryKey1, String primaryKey2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = null;
        if (table.equals(TABLE_USER)) {
            data = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE username = " + primaryKey1, null);
        }
        if (table.equals(TABLE_CHALLENGE)) {
            data = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE challenge_id = " + primaryKey1, null);
        }
        if (table.equals(TABLE_TEAM)) {
            data = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE team_id = " + primaryKey1 + ", Challenge_id = ", null);
        }
        if (table.equals(TABLE_LEADERBOARD)) {
            data = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE rank = " + primaryKey1 + ", username = " + primaryKey2, null);
        }
        if (table.equals(TABLE_PARTICIPATES)) {
            data = db.rawQuery("SELECT " + column + " FROM " + table + " WHERE username = " + primaryKey1 + ", challenge_id = " + primaryKey2, null);
        }
        if (data == null) {
            return "Invalid Table, column, primaryKey1, or PrimaryKey2 name. Please try again with valid inputs and make sure that the desired data is stored in the database";
        }
        return data.getString(0);
    }

    /***************************************************
     * deleteUser method
     * @param username: username at the row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified username
     * from the user table
     */
    public int deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER, "username = ?", new String[]{username});
    }

    /*****************************************************
     * deleteChallenge method
     * @param challenge_id: challenge id at the row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified challenge_id
     * from the challenge table
     */
    public int deleteChallenge(String challenge_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CHALLENGE, "challenge_id = ?", new String[]{challenge_id});
    }

    /*****************************************************
     * deleteTeam
     * @param teamName: team id at the row to be deleted
     * @param challengeID: challenge_id at the row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified team id
     * and challenge_id from the team table
     */
    public int deleteTeam(String teamName, int challengeID, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TEAM, "team_name = ? AND challenge_id = ? AND username = ?", new String[]{teamName, Integer.valueOf(challengeID).toString(), username});
    }

    /*******************************************************
     * deleteLeaderoard method
     * @param rank: rank at the row to be deleted
     * @param username: username at the row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified team id and username
     * from the leaderboard table
     */
    public int deleteLeaderBoard(int rank, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_LEADERBOARD, "rank = ? AND username = ?", new String[]{Integer.valueOf(rank).toString(), username});
    }

    /****************************************************
     * deleteParticipates method
     * @param username: username at row to be deleted
     * @param challengeID: challenge_id at row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified username
     * and challenge_id from the participates table
     */
    public int deleteParticipates(String username, int challengeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PARTICIPATES, "username = ? AND challenge_id = ?", new String[]{username, Integer.valueOf(challengeID).toString()});
    }

    public int deleteLog(String username, int challengeID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_LOG, "username = ? AND challenge_id = ?", new String[] {username,String.valueOf(challengeID)});
    }

    public int deleteNotification(String username, NotificationType type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTIFICATION,"username = ? AND type = ?", new String[] {username,type.name()});
    }



    /*****************************************************************
     * userFromEmail method
     * @param searchEmail: the email to be checked
     * @return the cursor object created by a query
     *
     * This method returns a cursor object after querying a
     * database with an email
     */
    public Cursor userFromEmail(String searchEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM User WHERE email = ?", new String[]{searchEmail});
    }



    /****************************************************
     * getChallengeData()
     * @return A cursor object containing every challenge in the database
     */
    public Cursor getChallengeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge", new String[]{});
        return cursor;
    }

    /******************************************************
     * getChallengeData(String column, String value)
     * @param column: the column you wish to compare value to
     * @param value: the value at which the column must equal.
     * @return A cursor object containing the challenges where the specified column equals the value
     */
    public Cursor getChallengeData(String column, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge WHERE " + column + " = ?", new String[]{value});
        return cursor;
    }

    /********************************************************
     * getChallengeWildCard
     * @param column
     * @param wildcard
     * @return a cursor object containing the challenges matching the indicated wildcard pattern
     */
    public Cursor getChallengeWildCard(String column, String wildcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge WHERE " + column + " LIKE '" + wildcard + "'", new String[]{});
        return cursor;
    }

    /***********************************************************************
     * getChallengeDataAsc
     * @param column
     * @return a cursor object containing the challenges ordered by column in ascending order
     */
    public Cursor getChallengeDataAsc(String column)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge ORDER BY " + column + " ASC", new String[] {});
        return cursor;
    }

    /***********************************************************************
     * getChallengeDataDesc
     * @param column
     * @return a cursor object containing the challenges ordered by column in descending order
     */
    public Cursor getChallengeDataDesc(String column)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge ORDER BY " + column + " DESC", new String[] {});
        return cursor;
    }

    /************************************************************************
     * getChallengeDataDateAsc
     * @return a cursor object containing the challenges ordered by the most recent date to oldest
     */
    public Cursor getChallengeDataDateAsc()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHALLENGE ORDER BY date(start_date) ASC", new String[]{});
        return cursor;
    }

    /************************************************************************
     * getChallengeDataDateDesc
     * @return a cursor object containing the challenges ordered by the oldest date to the most recent
     */
    public Cursor getChallengeDataDateDesc()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CHALLENGE ORDER BY date(start_date) DESC", new String[]{});
        return cursor;
    }

    /********************************************************
     Search database for query relating to user input of
     challenge name
     @param: challenge name
     @return cursor object containing challenge with specified
     challenge name
     */
    public Cursor getChallengeName() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Challenge WHERE name", new String[]{});
        return cursor;
    }

    /******************************************************
     * getChallengeData(int challengeID)
     * @param challengeID
     * @return A cursor object containing the challenge with the specified challengeID
     */
    public Cursor getChallengeID(int challengeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM Challenge WHERE challengeID", new String[]{Integer.valueOf(challengeID).toString()});
        return cursor;
    }

    /****************************************************
     * getChallengeData(String coach)
     * @return A cursor object containing every challenge hosted by the specified coach
     *
     */
    public Cursor getChallengeCoach(String coach) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge WHERE coach = ?", new String[]{coach});
        return cursor;
    }

    /***************************************************
     * getUserData() method
     * @return A cursor object containing every user in the user table
     */
    public Cursor getUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User", new String[]{});
        return cursor;
    }

    /***************************************************
     * getUserData() method
     * @return A cursor object containing username and profile picture columns
     * from every user in the user table
     */
    public Cursor getUserDataUsernameAndImage() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT username,image_data FROM User", new String[]{});
        return cursor;
    }

    /******************************************************
     * getUserData(String column)
     * @param column: the column you wish to compare value to
     * @param value: the value at which the column must equal.
     * @return A cursor object containing the users where the specified column equals value
     */
    public Cursor getUserData(String column, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE " + column + " = ?", new String[]{value});
        return cursor;
    }

    /*****************************************************
     * getUserData(String username, String password)
     * @param column1: the first column you wish to compare value1 to
     * @param value1: the value at which the first column must equal.
     * @param column2: the second column you wish to compare value2 to
     * @param value2: the value at which the column2 must equal.
     * @return A cursor object containing the users where column1 equals value1 and column2 equals value2
     */
    public Cursor getUserData(String column1, String value1, String column2, String value2) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE " + column1 + " = ? AND " + column2 + " = ?",
                new String[]{value1, value2});
        return cursor;
    }

    public Cursor getUserPassword(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM User WHERE username = ?", new String[] {username});
        return  cursor;
    }

    public Cursor getBitmapByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT image_data FROM User WHERE username = ?", new String[] {username});
        return cursor;
    }

    /**********************************************************************
     * getTeamData(String column, String value)
     * @param column:the column you wish to compare value to
     * @param value: the value at which column must equal
     * @return A cursor object containing the Team where column equals value
     */
    public Cursor getTeamData(String column, String value)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Team WHERE " + column + " = ?",new String[] {value});
        return cursor;
    }

    public Cursor getTeamData(String column1, String value1, String column2, String value2)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Team WHERE " + column1 + " = ? AND " + column2 + " = ?",new String[] {value1,value2});
        return cursor;
    }

    public Cursor getTeamData(String column, String value, String groupBy)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Team WHERE " + column + " = ? GROUP BY " + groupBy,new String[] {value});
        return cursor;
    }

    /**********************************************************************
     * getParticipatesData(String column, String value)
     * @param column:the column you wish to compare value to
     * @param value: the value at which column must equal
     * @return A cursor object containing the Team where column equals value
     */
    public Cursor getParticipatesData(String column, String value)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Participates WHERE " + column + " = ?",new String[] {value});
        return cursor;
    }

    public Cursor getParticipatesData(String column1, String value1, String column2, String value2)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Participates WHERE " + column1 + " = ? AND " + column2 + " = ?",new String[] {value1,value2});
        return cursor;
    }

    public Cursor getParticipatesDataInnerJoin(String username, String completed)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username,challenge.name,challenge.start_date,challenge.end_date FROM participates INNER JOIN challenge ON Participates.challenge_id = challenge.challenge_ID WHERE username = ? AND completed = ?",
                new String[] {username,completed});
        return cursor;
    }
    /**********************************************************************
     * getUniversalLeaderBoardsData(String column)
     * @param col:the column you wish to compare value to
     * @return A cursor object containing the Team of the entire column specified
     */
    public Cursor getUniversalLeaderBoardData(String col){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Universal LeaderBoard WHERE col", new String[]{});
        return cursor;
    }

    public Cursor getLogData(String username, int challengeID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Log WHERE username = ? AND challenge_id = ?",new String[] {username,String.valueOf(challengeID)});
        return cursor;
    }

    public Cursor getLogData(String username, int challengeID, String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Log WHERE username = ? AND challenge_id = ? AND log_date = ?",new String[] {username,String.valueOf(challengeID),date});
        return cursor;
    }


    /**********************************************************************
     * ggetNotificationData (String Column, String value)
     *@param column:the column you wish to compare value to
     *@param value: the value at which column must equal
     *@return A cursor object containing the Team where column equals value
     */
    public Cursor getNotificationData(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Participates WHERE " + column + " = ?", new String[]{value});
        return cursor;
    }

    public Cursor getLogDataInnerJoin(String teamName, int challengeID, String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT log.log_date, log.log_value, challenge.log_unit FROM log, team INNER JOIN challenge ON log.challenge_id = challenge.challenge_id WHERE team.team_name = ? AND team.challenge_id = ? AND log.username = ? GROUP BY log_date ORDER BY log_date",new String[] {teamName, String.valueOf(challengeID), username});

        return cursor;
    }

    public boolean hasLoggedToday(String username, int challengeID)
    {
        //Gets the current date, for determining the date the user signed up
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = sdf.format(date);

        Cursor cursor = getLogData(username,challengeID,currentDate);
        if (cursor.getCount() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /******************************************************************
     * userIsAvail method
     * @param username: the username to be checked
     * @return true if username is available, false if it is taken
     *
     * This method checks if the username entered by the user
     * is already taken (i.e already exists in the database).
     */
    public boolean userIsAvail(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        //queries the database to find if username is in the User table.
        Cursor cursor = db.rawQuery("SELECT username FROM User WHERE username = ? COLLATE NOCASE", new String[]{username});
        //If query returns no data
        if (cursor.getCount() == 0) {
            return true;
        }
        //if the query returns data, then the username was found.
        return false;
    }

    public boolean emailIsAvail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        //queries the database to find if username is in the User table.
        Cursor cursor = db.rawQuery("SELECT email FROM User WHERE email = ? COLLATE NOCASE", new String[]{email});
        //If query returns no data
        if (cursor.getCount() == 0) {
            return true;
        }
        //if the query returns data, then the username was found.
        return false;
    }
}

