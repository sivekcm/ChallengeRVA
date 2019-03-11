package com.example.challengerva;

public class Challenge {
    private String challengeName;
    private String challengeDescription;
    private String type;
    private String coachAssigned;
    private int difficulty;
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

    /*
    Default constructor when creating a challenge
    */
    public Challenge(String challengeName, String challengeDescription, String type, String coachAssigned, int difficulty,
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
    Set the challenge description for a given challenge
    @param: A "String" of the challenge's description and specifics
    @return true if the challenge string exists and false if no
            challenge description is given
    */
    public boolean setChallengeDescription(String description) {
        if (stringIsValid(challengeDescription)) {
            this.challengeDescription = description;
            return true;
        }
        else {
            throw new IllegalArgumentException("No challenge description was provided.");
        }
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
    Get the value for challenge name
    @param: none
    @return: challenge name
    */
    public String getChallengeName(){
        return challengeName;
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
    Return the type for the challenge
    @param: none
    @return: type for the challenge
    */
    public String getType(){
        return type;
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
    Get the coach name for challenge
    @param: none
    @return: coach assigned for challenge
    */
    public String getCoachAssigned(){
        return coachAssigned;
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

    /*
    Return the value of the difficulty of challenge
    @param: none
    @return: difficulty
    */
    public int getDifficulty(){
        return difficulty;
    }

    /*
    Sets the start date of the challenge
    @param: year, month, and day for the start date
    @return: true if all values are correct values for dates and
             false if not
    */
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

    /*
    Get the start date, month, and year
    @param: none
    @return: date, month and year
    */
    public int getStartDateYear(){
        return startDateYear;
    }
    public int getStartDateMonth(){
        return startDateMonth;
    }
    public int getStartDateDay(){
        return startDateDay;
    }

    /*
    Set the end date for the challenge
    @param: year, monnth, and day for the end date of challenge
    @return: true if completed date is a valid date and false
             if not
    */
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

    /*
    Get the end date's year, month, and day
    @param: none
    @return: end date's year, month, and day
    */
    public int getEndDateYear(){
        return endDateYear;
    }
    public int getEndDateMonth(){
        return endDateMonth;
    }
    public int getEndDateDay(){
        return endDateDay;
    }

    public void setInterval(int interval) {
            this.interval = interval;
    }

    public int getInterval() {
            return interval;
    }

    public void setStartAge(int startAge) {
            this.startAge = startAge;
    }

    public int getStartAge() {
            return startAge;
    }

    public void setEndAge(int endAge) {
            this.endAge = endAge;
    }

    public int getEndAge() {
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

    public boolean isTeam() {
            return team;
    }

}


