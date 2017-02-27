/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dao;

import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.Product;
import com.jets.onlineshopping.dto.User;
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
            return new Product(resultSet.getInt("id"), resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url"));
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
    
    public boolean insertCartItem(CartItem item, String userEmail) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO Cart (user_email, product_id, quantity) VALUES (?, ?, ?)");

            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, item.getProduct().getId());
            preparedStatement.setInt(3, item.getProduct().getStockQuantity());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<CartItem> getCartItems(String userEmail) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT *FROM Cart WHERE user_email = ?");
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CartItem> cartItemsList = new ArrayList<>();
            while (resultSet.next()) {
                cartItemsList.add(new CartItem(resultSet.getInt("id"), getProduct(resultSet.getInt("product_id")), resultSet.getInt("quantity")));
            }
            return cartItemsList;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean updateCartItem(CartItem item, String userEmail) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE Cart SET user_email = ?, product_id = ?, quantity = ? WHERE id = ?");

            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, item.getProduct().getId());
            preparedStatement.setInt(3, item.getProduct().getStockQuantity());
            preparedStatement.setInt(4, item.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCartItem(int cartItemId) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM Cart WHERE id = ?");
            preparedStatement.setInt(1, cartItemId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteAllCartItems(String userEmail) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM Cart WHERE user_email = ?");
            preparedStatement.setString(1, userEmail);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
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
            preparedStatement.setFloat(6, user.getCreditLimit());
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
            preparedStatement.setFloat(5, user.getCreditLimit());
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
        ArrayList<User> users = new ArrayList<>();
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
