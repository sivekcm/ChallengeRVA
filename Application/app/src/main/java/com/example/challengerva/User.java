package com.example.challengerva;
import android.database.Cursor;



public class User {
    protected String oldUsername;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String birthDate;
    protected String joinDate;
    protected String email;
    protected boolean isPrivate;
    protected UserType accountType;
    protected int challengesCompleted;


    //Enumeration used to determine type of account
    enum UserType
    {
        ATHLETE, COACH;
    }

    //Default Constructor
    public User(){

    }

    //Constructor
    public User(Cursor userCursor)
    {
        setUsername(userCursor.getString(0));
        oldUsername = this.username;
        setPassword(userCursor.getString(1));

        this.firstName = userCursor.getString(2);
        this.lastName = userCursor.getString(3);
        this.birthDate = userCursor.getString(4);
        this.joinDate = userCursor.getString(5);
        this.email = userCursor.getString(6);
        this.challengesCompleted = userCursor.getInt(7);

        if(userCursor.getString(8) == "true")
            this.isPrivate = true;
        else this.isPrivate = false;

        if(userCursor.getString(9) == "coach")
            this.accountType = UserType.COACH;
        else this.accountType = UserType.ATHLETE;



    }
    /*
    Passes an array of the parameters of this User object
    @return an array with all parameters of this User object
     */
    public Object[] getParameters()
    {
        Object[] parameters = new Object[11];
        parameters[0] = oldUsername;
        parameters[1] = username;
        parameters[2] = password;
        parameters[3] = firstName;
        parameters[4] = lastName;
        parameters[5] = birthDate;
        parameters[6] = joinDate;
        parameters[7] = email;
        parameters[8] = challengesCompleted;
        if(isPrivate)
            parameters[9] = "true";
        else parameters[9] = "false";
        if (accountType == UserType.COACH)
            parameters[10] = "coach";
        else parameters[10] = "athlete";
        return parameters;
    }

    /*
    Determines whether a username meets minimum requirements.
    @Param newUsername the username to be tested
    @return a 0 is the username is valid and available, a 1 if the username
        is valid but taken, or -1 if the username is invalid
     */
    public int isUsernameValid(String newUsername)
    {
        //Requirements: 6-20 characters, not taken
        if(newUsername.length() < 6 || newUsername.length() > 20)
            return -1;

        return isUsernameAvailable(newUsername);
    }

    /*
    Checks whether a username is available
    @Param newUsername the username to be checked
    @return 0 if it is available or 1 if it is taken
     */
    public int isUsernameAvailable(String newUsername)
    {
        // !!!!! PLACEHOLDER
        return 0;
    }

    /*
    Determines whether a password is valid.
    @Param newPassword the password to be tested
    @return 0 is it is valid or -1 if it is invalid
     */
    public int isPasswordValid(String newPassword)
    {
        //Requirements: Between 6-20 characters
        if(newPassword.length() < 6 || newPassword.length() > 20)
            return -1;
        else return 0;
    }

    /*
    Checks whether a parameter is equal to the username of a User.
    @Param inputUsername the parameter to be compared with the Username
     @return true if the parameter is the same, false if not
     */
    public boolean equalsUsername(String inputUsername)
    {
        return inputUsername.equals(username);
    }

    /*
    Checks whether a parameter is equal to the password of a User.
    @Param inputPassword the parameter to be compared with the Username
     @return true if the parameter is the same, false if not
     */
    public boolean equalsPassword(String inputPassword)
    {
        return inputPassword.equals(password);
    }
    /*
    Adds a Challenge to the User, depending on User Type
    @Param newChallenge the Challenge object to be added
    @return true if successful, false if not
     */
    public boolean addChallenge(String newChallenge)//!!!!! PLACEHOLDER
    {
        //User is Coach case
        if(accountType == UserType.COACH)
        {
            return true;
        }
        else return false;
    }

    /*
    Adds a team to an Athlete
    @Param newTeam the Team object to be added
    @return true if successful, false if not
     */
    public boolean addTeam(String newTeam)//!!!!! PLACEHOLDER
    {
        return false;
    }

    /*
    Sets the Username to something new
    @Param newUsername the new Username
    @return true if successful, false if not
     */
    public boolean setUsername(String newUsername)
    {
        int validUsername = isUsernameValid(newUsername);
        //Taken username case
        if(validUsername == 1)
            throw new IllegalArgumentException("The username " + newUsername + "is taken.");
        //Invalid username case
        if(validUsername == -1)
            throw new IllegalArgumentException("The username " + newUsername + "does not meet the requirements.");
        username = newUsername;
        return true;
    }
    public String getUsername(){
        return username;
    }
    /*
    Sets the Password to something new
    @Param newPassword the new Password
    @return true if successful,false if not
     */
    public boolean setPassword(String newPassword)
    {
        //Invalid password case
        if(isPasswordValid(newPassword) == -1)
            throw new IllegalArgumentException("The password you entered does not meet the requirements.");


        password = newPassword;
        return true;
    }

}
