package com.example.challengerva;

//class to initiate user information
public class Profile {
    private String username;
    private String password;
    private String first;
    private String last;

    public Profile() {
    }

    public Profile(username, password) {
        this.username = username;
        this.password = password;
    }

    public Profile(username, password, first, last){
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
    }

}
