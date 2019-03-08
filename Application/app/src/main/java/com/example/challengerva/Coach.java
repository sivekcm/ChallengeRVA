package com.example.challengerva;

public class Coach extends User{

<<<<<<< HEAD
    //constructor
    public Coach (){
        this.username = "username";
        this.password = "password";
=======
    /*
    Default constructor for Coach object
     */
    public Coach(){
        super.username = username;
        super.password = password;
>>>>>>> 8e57006fe29a8472dd4e6425c176fb7ba661ef37
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
