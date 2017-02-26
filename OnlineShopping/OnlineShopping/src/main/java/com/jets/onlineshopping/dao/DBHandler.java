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
import java.sql.ResultSet;
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
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO Product (name, price, quantity_in_stock, category, description, URL)"
                    + " VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getStockQuantity());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getUrl());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE Product SET name = ?, price = ?, quantity_in_stock = ?, category = ?, description = ?, URL = ?"
                    + " WHERE id = ?");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getStockQuantity());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getUrl());
            preparedStatement.setInt(7, product.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM Product WHERE id = ?");
            preparedStatement.setInt(1, productId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean decreaseProductQuantity(int productId, int amount) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE Product SET quantity_in_stock = quantity_in_stock - ? WHERE id = ?");

            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, productId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Product getProduct(int productId) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT *FROM Product WHERE id = ?");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Product> getProducts() {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT *FROM Product");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> productsList = new ArrayList<>();
            while (resultSet.next()) {
                productsList.add(new Product(resultSet.getInt("id"), resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url")));
            }
            return productsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Product> getProducts(String category) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT *FROM Product WHERE category = ?");
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> productsList = new ArrayList<>();
            while (resultSet.next()) {
                productsList.add(new Product(resultSet.getInt("id"), resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url")));
            }
            return productsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Product> getProducts(float minPrice, float maxPrice) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT *FROM Product WHERE price BETWEEN ? and ?");
            preparedStatement.setFloat(1, minPrice);
            preparedStatement.setFloat(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Product> productsList = new ArrayList<>();
            while (resultSet.next()) {
                productsList.add(new Product(resultSet.getInt("id"), resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url")));
            }
            return productsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
//    end of Aya
//    ==============================   
//    start of Eslam
    
//    end of Eslam
}
