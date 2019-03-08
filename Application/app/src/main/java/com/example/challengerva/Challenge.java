package com.example.challengerva;

public class Challenge {

    private String challengeName;
    private String challengeDescription;
    private String type;
    private String coachAssigned;
    private String difficulty;
    private int startDateYear;
    private int startDateMonth;
    private int startDateDay;
    private int endDateYear;
    private int endDateMonth;
    private int endDateDay;
    private int interval;
    private boolean open;
    private boolean team;
    private int startAge;
    private int endAge;

    //constructor
    public Challenge(String challengeName, String challengeDescription, String type, String coachAssigned, String difficulty,
                     int startDateYear, int startDateMonth, int startDateDay, int endDateYear, int endDateMonth, int endDateDay, int interval,
                     int startAge, int endAge, boolean open, boolean team){
        setChallengeName(challengeName);
        setChallengeDescription(challengeDescription);
        setCoachAssigned(coachAssigned);
        setDifficulty(difficulty);
        setStartDate(startDateYear, startDateMonth, startDateDay);
        setEndDate(endDateYear, endDateMonth, endDateDay);
        setInterval(interval);
        setStartAge(startAge);
        setEndAge(endAge);
        setOpen(open);
        setTeam(team);

    }

    public boolean stringIsValid(String entry){
        if (entry.length() > 0){
            return true;
        }
        return false;
    }

    public boolean integerIsValid(int entry){
        if (entry > 0){
            return true;
        }
        return false;
    }

    public boolean setChallengeDescription(String challengeDescription) {
        if (stringIsValid(challengeDescription)) {
            this.challengeDescription = challengeDescription;
            return true;
        }
        else {
            throw new IllegalArgumentException("Please enter a challenge description.");
        }
    }
    public String getChallengeDescription(){
        return challengeDescription;
    }

    public void setChallengeName(String challengeName){
        if (stringIsValid(challengeName))
            this.challengeName = challengeName;
        else
            throw new IllegalArgumentException("Please enter a challenge name");
    }
    public String getChallengeName(){
        return challengeName;
    }

    public void setType(String type){
        if (stringIsValid(type))
            this.type=type;
        else
            throw new IllegalArgumentException("Please enter the type of challenge");

    }
    public String getType(){
        return type;
    }

    public void setCoachAssigned(String coachAssigned){
        if (stringIsValid(coachAssigned))
            this.coachAssigned= coachAssigned;
        else
            throw new IllegalArgumentException("Please enter your (the coach) name.");
    }
    public String getCoachAssigned(){
        return coachAssigned;
    }

    public void setDifficulty(String difficulty){
        if (stringIsValid(difficulty))
            this.difficulty=difficulty;
        else
            throw new IllegalArgumentException("Please state the difficulty of this challenge.");
    }
    public String getDifficulty(){
        return difficulty;
    }

    public boolean setStartDate(int startDateYear, int startDateMonth, int startDateDay){
        if (startDateYear >2019) {
            this.startDateYear = startDateYear;
            return true;
        }
        if ((startDateMonth > 0)&& (startDateMonth < 12)) {
            this.startDateMonth = startDateMonth;
            return true;
        }
        if ((startDateDay > 0) && (startDateDay < 31)){
            this.startDateDay = startDateDay;
            return true;
        }
        return false;
    }
    public int getStartDateYear(){
        return startDateYear;
    }
    public int getStartDateMonth(){
        return startDateMonth;
    }
    public int getStartDateDay(){
        return startDateDay;
    }
    public boolean setEndDate(int endDateYear, int endDateMonth, int endDateDay){
        if (endDateYear > 2019){
            this.endDateYear = endDateYear;
            return true;
        }
        if ((endDateMonth > 0)&& (endDateMonth < 12)){
            this.endDateMonth =endDateMonth;
            return true;
        }
        if ((endDateDay > 0) && (endDateDay < 31)){
            this.endDateDay = endDateDay;
            return true;
        }
        return false;
    }
    public int getEndDateYear(){
        return endDateYear;
    }
    public int getEndDateMonth(){
        return endDateMonth;
    }
    public int getEndDateDay(){
        return endDateDay;
    }

    public void setInterval(int interval){
        this.interval = interval;
    }
    public int getInterval(){
        return interval;
    }

    public void setStartAge(int startAge){
        this.startAge = startAge;
    }
    public int getStartAge(){
        return startAge;
    }

    public void setEndAge(int endAge){
        this.endAge = endAge;
    }
    public int getEndAge(){
        return endAge;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
    public boolean isOpen() {
        return open;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }
    public boolean isTeam(){
        return team;
    }
}
