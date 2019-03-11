package com.example.challengerva;

public class Coach extends User{



    /*
    Default constructor for Coach object
     */
    public Coach(){
        super.username = username;
        super.password = password;

    }

    public Coach(String user, String pw) {
        this.username = user;
        this.password = pw;
    }
    public boolean verifyPassword(){
        return false;
    }
    public boolean verifyCoach(){
        return false;
    }
}
