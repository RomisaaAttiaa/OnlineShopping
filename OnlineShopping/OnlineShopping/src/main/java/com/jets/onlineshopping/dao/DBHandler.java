/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dao;

import com.jets.onlineshopping.dto.Product;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public boolean insertProduct(Product product) {
        return false;
    }

    public boolean updateProduct(Product product) {
        return false;
    }

    public boolean deleteProduct(int productId) {
        return false;
    }

    public void decreaseProductQuantity(int productId, int amount) {

    }

    public Product getProduct(int productId) {
        return null;
    }

    public ArrayList<Product> getProducts(String category) {
        return null;
    }

    public ArrayList<Product> getProducts(float minPrice, float maxPrice) {
        return null;
    }
//    end of Aya
//    ==============================   
//    start of Eslam
    
//    end of Eslam
}
