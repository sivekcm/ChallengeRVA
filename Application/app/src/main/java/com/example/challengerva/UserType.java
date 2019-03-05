package com.example.challengerva;

//class to initiate user class
public class UserType {
    private String username;
    private String password;
    private String first;
    private String last;


    public UserType() {
        this.username = "UserName";
        this.password = "PassWord";
    }

    public UserType(String user, String pw) {
        this.username = user;
        this.password = pw;
    }

    public UserType(String username, String password, String first, String last){
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
    }

}
