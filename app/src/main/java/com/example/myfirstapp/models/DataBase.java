package com.example.myfirstapp.models;

import java.sql.*;
public class DataBase {
    private String url = "jdbc:postgresql://db.fauokmrzqpowzdiqqxxg.supabase.co:5432/postgres";
    private String user = "postgres";
    private String password = "palakapapoy";


    public Connection createConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user, password);
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method fetches the user from the database using the parameter username
     * this returns an object of User
     * @param username
     * @return User
     */
    public User fetchUser(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user_details WHERE username = '"+username+"'");
            if(rs.next()){
                //Gets data from ResultSet and segregates them into respective variables
                String fetchedUsername = rs.getString(1);
                String fetchedPassword = rs.getString(2);
                int fetchedUserID = rs.getInt(3);
                String fetchedFName = rs.getString(4);
//                String fetchedMName = rs.getString(5);
                String fetchedLName = rs.getString(6);
                // constructs and returns a new object of user based on the fetched data
                return new User(fetchedUsername, fetchedPassword, true,fetchedFName, fetchedLName);
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ContactDetails fetchContactDetails(String username){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contact_details WHERE username = '"+username+"'");
            int fetchedID = rs.getInt(1);
            String fetchedUsername = rs.getString(2);
            String fetchedEmail = rs.getString(3);
//            String fetchedAddress = rs.getString(4);
            String fetchedNumber = rs.getString(5);
            ContactDetails cd = new ContactDetails(fetchedUsername, fetchedEmail,fetchedNumber);
            cd.setId(fetchedID);
            return cd;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public TravelPlan fetchTravelPlan(String columnName, String searchKey){
        try{
            Connection conn = createConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM travel_plan WHERE "+columnName+" = '"+searchKey+"'");
            String fetchedTitle = rs.getString(3);
            String fetchedReviews = rs.getString(4);
            String fetchedAuthor = rs.getString(2);
            String fetchedDuration = rs.getString(5);
            String fetchedEstimatedCost = rs.getString(6);
            String fetchedDescription = rs.getString(7);
            String fetchedDestinations = rs.getString(8);
            TravelPlan fetchedPlan = new TravelPlan(fetchedTitle, null, fetchedAuthor,fetchedDuration, fetchedEstimatedCost,fetchedDescription,fetchedDestinations);
            fetchedPlan.setReviews(fetchedReviews);
            fetchedPlan.setPost_id(rs.getInt(1));
            return fetchedPlan;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
