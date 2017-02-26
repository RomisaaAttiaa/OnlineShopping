/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dao;

import com.jets.onlineshopping.dto.User;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eslam
 */
public class DBHandler {

    Connection connection;
    PreparedStatement preparedStatement;

    public DBHandler() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlineshoppingdb", "root", "");
            System.out.println("connected");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            connection.close();
        } finally {
            super.finalize();
        }
    }

//    start of Aya
    
//    end of Aya
//    ==============================   
//    start of Eslam
    public boolean insertUser(User user) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getEmail().toLowerCase());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
            preparedStatement.setString(5, user.getJob());
            preparedStatement.setInt(6, user.getCreditLimit());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getRole());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE user set name = ?, password = ?, DoB = ?, job = ?, credit_limit = ?, address = ? where email = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, new java.sql.Date(user.getBirthdate().getTime()));
            preparedStatement.setString(4, user.getJob());
            preparedStatement.setInt(5, user.getCreditLimit());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getEmail().toLowerCase());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User getUser(String email) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            preparedStatement.setString(1, email.toLowerCase());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("email"), rs.getString("name"), rs.getString("password"), rs.getDate("DoB"), rs.getString("job"), rs.getInt("credit_limit"), rs.getString("address"), rs.getString("role"));
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("email"), rs.getString("name"), rs.getString("password"), rs.getDate("DoB"), rs.getString("job"), rs.getInt("credit_limit"), rs.getString("address"), rs.getString("role")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return users;
        }
    }

    public boolean decreaseCreditLimit(String email, int amount) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE user set credit_limit = credit_limit-? where email = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, email.toLowerCase());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean increaseCreditLimit(String email, int amount) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE user set credit_limit = credit_limit+? where email = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, email.toLowerCase());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User checkLogin(String email, String password) {
        if (getUser(email).getPassword().equals(password)) {
            return getUser(email);
        } else {
            return null;
        }

    }
//    end of Eslam
}
