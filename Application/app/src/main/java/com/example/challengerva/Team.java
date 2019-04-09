package com.example.challengerva;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

    public String name;
    public int challengeID;
    public int numOfUsers;

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.challengeID);
        dest.writeInt(this.numOfUsers);
    }

    public Team(Parcel in)
    {
        this.name = in.readString();
        this.challengeID = in.readInt();
        this.numOfUsers = in.readInt();
    }

    public Team(Cursor curosr) {
        this.name = curosr.getString(0);
        this.challengeID = curosr.getInt(1);
        this.numOfUsers = curosr.getCount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(int challengeID) {
        this.challengeID = challengeID;
    }

    public int getNumOfUsers() {
        return numOfUsers;
    }

    public void setNumOfUsers(int numOfUsers) {
        this.numOfUsers = numOfUsers;
    }
}
