package com.example.challengerva;

public class CreateChallenge {
    private String challengeName;
    private String challengeDescription;
    private String type;
    private String coachAssigned;
    private String difficulty;
    private int startDate;
    private int endDate;
    private int interval;
    private boolean open;
    private boolean team;
    private int startAge;
    private int endAge;

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }
    public String getChallengeDescription(){
        return challengeDescription;
    }
    public void setChallengeName(String challengeName){
        this.challengeName = challengeName;
    }
    public String getChallengeName(){
        return challengeName;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return type;
    }
    public void setCoachAssigned(String coachAssigned){
        this.coachAssigned= coachAssigned;
    }
    public String getCoachAssigned(){
        return coachAssigned;
    }
    public void setDifficulty(String type){
        this.difficulty=difficulty;
    }
    public String getDifficulty(){
        return difficulty;
    }
    public void setStartDate(int startDate){
        this.startDate = startDate;
    }
    public int getStartDate(){
        return startDate;
    }
    public void setEndDate(int endDate){
        this.endDate = endDate;
    }
    public int getEndDate(){
        return endDate;
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
