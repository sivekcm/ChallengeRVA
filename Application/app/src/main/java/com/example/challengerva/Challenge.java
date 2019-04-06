package com.example.challengerva;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Challenge implements Parcelable {
    private int challengeID;
    private String challengeName;
    private String coachAssigned;
    private String startDate;
    private String endDate;
    private String type;
    private int difficulty;
    private boolean team;
    private boolean open;
    private String healthHazards;
    private String challengeDescription;
    private int minTeam;
    private int maxTeam;
    private int logRange;
    private String logUnit;

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel in) {
            return new Challenge(in);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };

    public Challenge(Parcel in)
    {
        this.challengeID = in.readInt();
        this.challengeName = in.readString();
        this.coachAssigned = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.type = in.readString();
        this.difficulty = in.readInt();
        this.team = in.readByte() != 0;
        this.open = in.readByte() != 0;
        this.healthHazards = in.readString();
        this.challengeDescription = in.readString();
        this.minTeam = in.readInt();
        this.maxTeam = in.readInt();
        this.logRange = in.readInt();
        this.logUnit = in.readString();

    }

    /*
    constructor when creating a challenge for a all instance variables
    */
    public Challenge(Cursor cursor) {
        this.challengeID = cursor.getInt(0);
        this.challengeName = cursor.getString(1);
        this.coachAssigned = cursor.getString(2);
        this.startDate = cursor.getString(3);
        this.endDate = cursor.getString(4);
        this.type = cursor.getString(5);
        this.difficulty = cursor.getInt(6);
        if (cursor.getString(7).toLowerCase().equals("team"))
        {
            this.team = true;
        }
        else
        {
            this.team = false;
        }
        if (cursor.getString(8).equals("Y"))
        {
            this.open = true;
        }
        else
        {
            this.open = false;
        }
        this.healthHazards = cursor.getString(9);
        this.challengeDescription = cursor.getString(10);
        this.minTeam = cursor.getInt(11);
        this.maxTeam = cursor.getInt(12);
        this.logRange = cursor.getInt(13);
        this.logUnit = cursor.getString(14);
    }



    /*
        Check to see if a string exists
        @param: string
        @return: boolean true if string exists and false
                 if it does not
        */
    public boolean stringIsValid(String entry){
        if (entry.length() > 0){
            return true;
        }
        return false;
    }

    /*
    Check to see if an inputted number exists
    @param: integer
    @return: boolean true if integer exists and false
             if it does not
    */
    public boolean integerIsValid(int entry){
        if (entry > 0){
            return true;
        }
        return false;
    }



    /*
    Return value for challenge description
    @param: none
    @return: challenge description
    */
    public String getChallengeDescription(){
        return challengeDescription;
    }

    /*
    Get the value for challenge name
    @param: none
    @return: challenge name
    */
    public String getChallengeName(){
        return challengeName;
    }

    /*
    Return the type for the challenge
    @param: none
    @return: type for the challenge
    */
    public String getType(){
        return type;
    }

    /*
    Get the coach name for challenge
    @param: none
    @return: coach assigned for challenge
    */
    public String getCoachAssigned(){
        return coachAssigned;
    }

    /*
    Return the value of the difficulty of challenge
    @param: none
    @return: difficulty
    */
    public int getDifficulty(){
        return difficulty;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isTeam() {
        return team;
    }

    public boolean isOpen() {
        return open;
    }

    public String getHealthHazards() {
        return healthHazards;
    }

    public int getMinTeam() {
        return minTeam;
    }

    public int getMaxTeam() {
        return maxTeam;
    }

    public int getLogRange() {
        return logRange;
    }

    public String getLogUnit()
    {
        return logUnit;
    }

    /*
        Set the challenge name for a new challenge
        @param: name for challenge
        @return: challenge name or throw argument if name not given
        */
    public void setChallengeName(String name){
        if (stringIsValid(name))
            this.challengeName = name;

        else
            throw new IllegalArgumentException("Please enter a challenge name");
    }

    /*
        Set the challenge description for a given challenge
        @param: A "String" of the challenge's description and specifics
        @return true if the challenge string exists and false if no
                challenge description is given
        */
    public void setChallengeDescription(String description) {
        if (stringIsValid(challengeDescription)) {
            this.challengeDescription = description;
        }
        else {
            throw new IllegalArgumentException("No challenge description was provided.");
        }
    }

    /*
    Identify the type of challenge
    @param: string of type
    @return: type if type given or throw an exception when type not
             provided
    */
    public void setType(String type){
        if (stringIsValid(type))
            this.type=type;
        else
            throw new IllegalArgumentException("Type of challenge not specified.");

    }

    /*
    Set the coach assigned for the challenge
    @param: name of coach assigned
    @return: coach assigned for challenge
    */
    public void setCoachAssigned(String coach){
        if (stringIsValid(coach))
            this.coachAssigned = coach;
        else
            throw new IllegalArgumentException("Coach name not provided.");
    }

    /*
    Set the difficulty of the challenge
    @param: int of difficulty: 1 (easiest), 2, 3 (hardest)
    @return: the difficulty of the challenge
    */
    public void setDifficulty(int difficulty){
        if (integerIsValid(difficulty))
            this.difficulty=difficulty;
        else
            throw new IllegalArgumentException("Please state the difficulty of this challenge.");
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setHealthHazards(String healthHazards) {
        this.healthHazards = healthHazards;
    }

    public void setMinTeam(int minTeam) {
        this.minTeam = minTeam;
    }

    public void setMaxTeam(int maxTeam) {
        this.maxTeam = maxTeam;
    }

    public void setLogRange(int logRange) {
        this.logRange = logRange;
    }

    /*
           Set the status of the challenge
           @param: boolean end age
           @return: return true if the status of the challenge is open
           */
    public void setOpen(boolean open) {
            this.open = open;
    }


    /*
     Set if the challenge accepts teams
     @param: boolean team
     @return: return true if the challenge is team based
     */
    public void setTeam(boolean team) {
        this.team = team;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getChallengeID() {
        return challengeID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.challengeID);
        dest.writeString(this.challengeName);
        dest.writeString(this.coachAssigned);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.type);
        dest.writeInt(this.difficulty);
        dest.writeByte((byte) (team ? 1 : 0));
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(this.healthHazards);
        dest.writeString(this.challengeDescription);
        dest.writeInt(this.minTeam);
        dest.writeInt(this.maxTeam);
        dest.writeInt(this.logRange);
        dest.writeString(this.logUnit);

    }
}


