package com.example.challengerva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

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
    public static final String USER_COL8 = "challenges_comp";
    public static final String USER_COL9 = "private";
    public static final String USER_COL10 = "type";

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

    //Team Table
    public static final String TABLE_TEAM = "Team";
    public static final String TEAM_COL1 = "team_id";
    public static final String TEAM_COL2 = "challenge_id";
    public static final String TEAM_COL3 = "user1";
    public static final String TEAM_COL4 = "user2";
    public static final String TEAM_COL5 = "user3";
    public static final String TEAM_COL6 = "user4";

    //Leaderboard Table
    public static final String TABLE_LEADERBOARD = "LeaderBoard";
    public static final String LB_COL1 = "rank";
    public static final String LB_COL2 = "username";
    public static final String LB_COL3 = "challenges_comp";

    //Participates Table
    public static final String TABLE_PARTICIPATES = "Participates";
    public static final String PART_COL1 = "username";
    public static final String PART_COL2 = "challenge_id";
    public static final String PART_COL3 = "join_date";

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
                "challenges_comp INTEGER, " +
                "private TEXT NOT NULL, " +
                "type TEXT NOT NULL" +
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
                "FOREIGN KEY(coach) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE" +
                ") ");

        //Team table
        //Stores team data
        //unique team id and challenge id
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
                "FOREIGN KEY(user4) REFERENCES " + TABLE_USER + "(username) ON DELETE SET NULL" +
                ") ");

        //Leaderboard table
        //Stores leaderboard data
        //unique rank and username pairing
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LEADERBOARD + "(" +
                "rank INTEGER NOT NULL, " +
                "username TEXT NOT NULL, " +
                "challenges_comp INTEGER NOT NULL, " +
                "PRIMARY KEY(rank, username), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenges_comp) REFERENCES " + TABLE_USER + "(challenges_comp) ON DELETE CASCADE" +
                ") ");

        //Participates table
        //Stores data of what challenges a user is participating in
        //unique username and challenge id pairing
        db.execSQL("CREATE TABLE " + TABLE_PARTICIPATES + "(" +
                "username TEXT, " +
                "challenge_id INTEGER, " +
                "join_date DATE NOT NULL, " +
                "PRIMARY KEY(username, challenge_id), " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_USER + "(username) ON DELETE CASCADE, " +
                "FOREIGN KEY(challenge_id) REFERENCES " + TABLE_CHALLENGE + "(challenge_id) ON DELETE CASCADE" +
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
                              String type) {
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
                                   String hazards, String description) {
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

        long num = db.insert(TABLE_CHALLENGE, null, cv);
        if (num == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**********************************************************************
     * insertTeam method
     * @param team_id: id for team
     * @param challenge_id id for challenge referring to challenge table (unique pairing with team id)
     * @param user1
     * @param user2
     * @param user3
     * @param user4
     * @return false if insert fails, true if data is inserted successfully
     *
     * This method inserts a new data entry in the the team table. No two data
     * entries may have the same team_id and challenge_id pairing. team id and
     * challenge id cannot be null.
     */
    public boolean insertTeam(int team_id, int challenge_id, String user1,
                              String user2, String user3, String user4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_COL1, team_id);
        cv.put(TEAM_COL2, challenge_id);
        cv.put(TEAM_COL3, user1);
        cv.put(TEAM_COL4, user2);
        cv.put(TEAM_COL5, user3);
        cv.put(TEAM_COL6, user4);

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
     * @param challengesComp: the amount of challenges completed by user
     * @return false if insert fails, true if data is inserted successfully
     *
     * This methods inserts a new data entry (a full row) into the leaderboard
     * table. No two data entries may have the same rank and username pairing.
     * Only challengesComp can be null
     */
    public boolean insertLeaderBoard(int rank, String username, int challengesComp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, rank);
        cv.put(LB_COL2, username);
        cv.put(LB_COL3, challengesComp);

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
     * @return false if insert fails, true if data is inserted successfully
     *
     * this method inserts a new data entry (a full row) into the participates table.
     * The participates table contains data of the users that a participating in a
     * certain challenge (i.e when a user completes a challenge, the data entry
     * in this table associated with the user should be removed). No two data entries
     * may have the same username and challengeID pairing. All dates must be formatted
     * "YYYY-MM-DD" otherwise insert will fail. No parameters are allowed to be null.
     */
    public boolean insertParticipates(String username, int challengeID, String joinDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PART_COL1, username);
        cv.put(PART_COL2, challengeID);
        cv.put(PART_COL3, joinDate);

        long num = db.insert(TABLE_PARTICIPATES, null, cv);
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
     * @return false if update failed, true if data was updated successfully
     *
     * This method updates a row in the User table at the specified username. All dates
     * must be formatted "YYYY-MM-DD" otherwise insert will fail. Username must already
     * exist in table or else update will fail. The only parameter that can be null is complChall.
     */
    public boolean updateUser(String oldUsername, String newUsername, String password, String fName,
                              String lName, String birthDate, String joinDate,
                              String email, int complChall, String priv,
                              String type) {
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
            Cursor userCursor = getUserData((String) parameterArray[0]);
            userCursor.moveToFirst();
            parameterArray[2] = userCursor.getString(1);
        }

        return updateUser((String) parameterArray[0], (String) parameterArray[1], (String) parameterArray[2], (String) parameterArray[3],
                (String) parameterArray[4], (String) parameterArray[5], (String) parameterArray[6], (String) parameterArray[7],
                (int) parameterArray[8], (String) parameterArray[9], (String) parameterArray[10]);
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
     * @return false if update fails, true if data is updated successfully
     *
     * This method updates a row in the challenge table at the specified
     * challengeID. All dates must be specified in the format "YYYY-MM-DD",
     * otherwise update will fail. No parameters are allowed to be null in this method.
     */
    public boolean updateChallenge(int oldChallengeID, int newChallengeID, String name, String coach,
                                   String startDate, String endDate, String type,
                                   String diff, String teamOrSingle, String availability,
                                   String hazards, String description) {
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
        long result = db.update(TABLE_CHALLENGE, cv, "challenge_id = ?", new String[]{Integer.valueOf(oldChallengeID).toString()});
        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**********************************************************************
     * updateTeam method
     * @param oldTeam_id: team id of the row you want to change
     * @param oldChallenge_id: challenge id of the row you want to change (paired with team id)
     * @param newTeam_id: the Team id you want to replace the old one with
     * @param newChallenge_id: the challenge id you want to replace the old one with
     * @param user1
     * @param user2
     * @param user3
     * @param user4
     * @return false if update fails, true if data is updated successfully
     *
     * This method updates the data of a row at the specified Team_id and
     * challenge_id pairing. Team_id and challenge_id must already exist
     * or else update will fail.
     */
    public boolean updateTeam(int oldTeam_id, int oldChallenge_id, int newTeam_id, int newChallenge_id, String user1,
                              String user2, String user3, String user4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TEAM_COL1, newTeam_id);
        cv.put(TEAM_COL2, newChallenge_id);
        cv.put(TEAM_COL3, user1);
        cv.put(TEAM_COL4, user2);
        cv.put(TEAM_COL5, user3);
        cv.put(TEAM_COL6, user4);

        long result = db.update(TABLE_TEAM, cv, "team_id = ? AND challenge_id = ?", new String[]{Integer.valueOf(oldTeam_id).toString(), Integer.valueOf(oldChallenge_id).toString()});
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
     * @param challengesComp: the amount of challenges completed by user
     * @return false if update fails, true if data is updated successfully
     *
     * This methods updates a row in the leaderboard
     * table at the specififed rank and username pairing.
     * the rank and username must already exist in the table,
     * otherwise update will fail. Only challengesComp can
     * be null.
     */
    public boolean updateLeaderBoard(int oldRank, String oldUsername, int newRank, String newUsername, int challengesComp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LB_COL1, newRank);
        cv.put(LB_COL2, newUsername);
        cv.put(LB_COL3, challengesComp);

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
     * @return false if update fails, true if data is updated successfully
     *
     * this method updates a row in the participates table at the specified username
     * and challengeID pairing. The participates table contains data of the users that a participating in a
     * certain challenge (i.e when a user completes a challenge, the data entry
     * in this table associated with the user should be removed). The username and
     * challengeID must already exist or the update will fail. All dates must be formatted
     * "YYYY-MM-DD" otherwise insert will fail. No parameters are allowed to be null.
     */
    public boolean updateParticipates(String oldUsername, int oldChallengeID, String newUsername, int newChallengeID, String joinDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PART_COL1, newUsername);
        cv.put(PART_COL2, newChallengeID);
        cv.put(PART_COL3, joinDate);

        long result = db.update(TABLE_PARTICIPATES, cv, "username = ? AND challenge_id = ?", new String[]{oldUsername, Integer.valueOf(oldChallengeID).toString()});
        if (result > 0) {
            return true;
        } else {
            return false;
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
     * @param team_id: team id at the row to be deleted
     * @param challenge_id: challenge_id at the row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified team id
     * and challenge_id from the team table
     */
    public int deleteTeam(int team_id, int challenge_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TEAM, "team_id = ? AND challenge_id = ?", new String[]{Integer.valueOf(team_id).toString(), Integer.valueOf(challenge_id).toString()});
    }

    /*******************************************************
     * deleteLeaderBoard method
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
     * @param challenge_id: challenge_id at row to be deleted
     * @return the number of rows that were deleted
     *
     * Deletes the row containing the specified username
     * and challenge_id from the participates table
     */
    public int deleteParticipates(String username, Integer challenge_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PARTICIPATES, "username = ? AND challenge_id = ?", new String[]{username, Integer.valueOf(challenge_id).toString()});
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

    /******************************************************
     * getChallengeData(int challengeID)
     * @param challengeID
     * @return A cursor object containing the challenge with the specified challengeID
     */
    public Cursor getChallengeData(int challengeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Challenge WHERE challenge = ?", new String[]{Integer.valueOf(challengeID).toString()});
        return cursor;
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

    /******************************************************
     * getUserData(String username)
     * @param username: the username of the user
     * @return A cursor object containing the user with the specified username from the user table
     */
    public Cursor getUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username = ?", new String[]{username});
        return cursor;
    }

    /*****************************************************
     * getUserData(String username, String password)
     * @param username: the username of the user
     * @param password: the password of the user
     * @return A cursor object containing the user with the specified username and password
     */
    public Cursor getUserData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username = ? AND password = ?",
                new String[]{username, password});
        return cursor;
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

