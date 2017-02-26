/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    
//    end of Eslam
}
