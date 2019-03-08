package com.example.challengerva;

public class Coach extends UserType{
    private String username;
    private String password;

    //constructor
    public Coach (){
        this.username = "username";
        this.password = "password";
    }

    public Coach(String user, String pw){
        this.username = user;
        this.password = pw;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return password;
    }
    public boolean verifyPassword(){
        return false;
    }
    public boolean verifyCoach(){
        return false;
    }
}
