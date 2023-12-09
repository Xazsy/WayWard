package com.example.myfirstapp.models;

import com.example.myfirstapp.controllers.SigninController;
import com.example.myfirstapp.controllers.SignupController;

import java.sql.*;

/**
 * User model class
 */

public class User {
    private String username;
    private String password;
    private int userId = -1;
    private String firstName;
    private String middleName;
    private String lastName;
    private final DataBase db = new DataBase();
    private SignupController signupController;
    private SigninController signinController;
    public User (SignupController signupController) {
        this.signupController = signupController;
    }
    public User(SigninController signinController) {
        this.signinController = signinController;
    }
    /**
     * This constructor takes in three parameters and leaves the details of names as null
     * @param username
     * @param password
     * @param hashed
     */
    public User(String username, String password, boolean hashed){
        this.username = username;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;
    }

    /**
     * Is the complete constructor of the class
     * @param username
     * @param password
     * @param hashed
     * @param firstName
//     * @param middleName
     * @param lastName
     */
    public User(String username, String password, boolean hashed, String firstName, String lastName){
        this.username = username;
        this.firstName = firstName;
//        this.middleName = middleName;
        this.lastName = lastName;
        if(hashed){
            this.password = password;
        }else{
            this.password = hashPassword(password);
        }
    }




    /**
     * Inserts the current object of user to the database
     */
    public void insertCurrentUser(){
        boolean valid = isUsernameValid();
        if(valid) {
            try {
                Connection conn = db.createConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("insert into user_details(user_id,username,password,first_name,middle_name,last_name) values (default,'" + this.username + "','"
                        + this.password + "','" + this.firstName + "','" + this.middleName + "','" + this.lastName + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("username already exists");
        }
    }

    /**
     * Checks if the username already exists in the database
     * @return boolean
     */
    public boolean isUsernameValid(){
        try {
            Connection conn = db.createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT exists(SELECT 1 FROM user_details WHERE username = '"+this.username+"'");
            return rs.getBoolean(1);
        }catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Method used to authenticate given password with the stored password in the database
     * @return boolean
     */
    public boolean authenticate(){
        User dbUser = db.fetchUser(this.username);

        //insert logic to compare two objects of user
        return this.password.equals(dbUser.getPassword());
    }

    /**
     * This method hashes the password to provide a level of security in storing the password
     * @param rawPassword Takes in a string that is a raw unhashed version of the password
     * @return String
     */
    private String hashPassword(String rawPassword){
        String hashed="";

        return hashed;
    }

    /**
     *
     * @return
     */
    public String getPassword(){
        return this.password;
    }

    /**
     *
     * @return
     */
    public void setPassword(String password, boolean hashed){
        if(!hashed){
            this.password = hashPassword(password);
        }else{
            this.password = password;
        }
    }

    /**
     *
     * @return
     */
    public String getUsername(){
        return this.username;

    }

    /**
     *
     * @return
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     *
     * @return
     */
    public void setFirstname(String firstName){
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getMiddleName(){
        return this.middleName;
    }

    /**
     *
     * @return
     */
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    /**
     *
     * @return
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     *
     * @return
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public boolean createAccount(String email, String username, String password) {

//        db.storeUserAccount()
        return false;
    }
}
