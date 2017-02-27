/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dao;

import com.jets.onlineshopping.dto.Coupon;
import com.jets.onlineshopping.dto.Order;
import com.jets.onlineshopping.dto.Product;
import com.jets.onlineshopping.dto.User;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
    //Search in product table
    public ArrayList<Product> searchProductByCategory(String searchString, String category){
        ArrayList<Product> products = new ArrayList<>();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM product WHERE name LIKE ? AND category = ?");
            preparedStatement.setString(1, searchString+"%");
            preparedStatement.setString(2, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                products.add(new Product(resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    
    public ArrayList<Product> searchProductByPrice(String searchString, float minPrice, float maxPrice){
        ArrayList<Product> products = new ArrayList<>();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM product WHERE name LIKE ? AND price BETWEEN ? AND ?");
            preparedStatement.setString(1, searchString+"%");
            preparedStatement.setFloat(2, minPrice);
            preparedStatement.setFloat(3, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                products.add(new Product(resultSet.getFloat("price"), resultSet.getInt("quantity_in_stock"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("category"), resultSet.getString("url")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    //Table User
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
    
    //Table ORDER
    public boolean insertOrder(Order order, String email){
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO `order` (`user_email`, `product_id`, `quantity`, `date`, `time`) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, order.getProduct().getId());
            preparedStatement.setInt(3,order.getQuantity());
            preparedStatement.setDate(4, new java.sql.Date(order.getDate().getTime()));
            preparedStatement.setTime(5, new Time(order.getDate().getTime()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public Order getOrder(int id){
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM `order` WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                Date date = new Date(rs.getDate("date").getTime());
                date.setTime(rs.getTime("time").getTime());
                return new Order(rs.getInt("id"), getProduct(rs.getInt("product_id")), rs.getInt("quantity"), date);
            }
            else
                return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Order> getOrders(String email){
        ArrayList<Order> orders = new ArrayList<>();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM `order` WHERE user_email = ?");
            preparedStatement.setString(1, email.toLowerCase());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Date date = new Date(rs.getDate("date").getTime());
                date.setTime(rs.getTime("time").getTime());
                orders.add(new Order(rs.getInt("id"), getProduct(rs.getInt("product_id")), rs.getInt("quantity"), date));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    //Table COUPON
    public boolean insertCoupon(int credit){
        long random = (long) (new Random().nextDouble()*100000000L);
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO coupon VALUES(?,?)");
            preparedStatement.setLong(1,random);
            preparedStatement.setInt(2, credit);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Coupon> getCoupons(){
        ArrayList<Coupon> coupons = new ArrayList<>();
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM coupon");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
                coupons.add(new Coupon(rs.getLong("id"),rs.getInt("credit")));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coupons;
    }
//    end of Eslam
}
