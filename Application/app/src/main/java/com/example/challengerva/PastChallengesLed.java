package com.example.challengerva;

public class PastChallengesLed extends Challenge{
    private String challengeName;
    private String[] listofChallenges;

    public void setChallengeName(String challName){
        this.challengeName = challName;
    }

    public String getChallengeName(){
        return challengeName;
    }

    public void setListofChallenges(String[] arr){
        this.listofChallenges = arr;
    }

    public String[] getListofChallenges(){
        return listofChallenges;
    }

    public void printChallenges(String[] listofChallenges){
        for(String item : listofChallenges){
            System.out.println(item);
        }
    }
}
