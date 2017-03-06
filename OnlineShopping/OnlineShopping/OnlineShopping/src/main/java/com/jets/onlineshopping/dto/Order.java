/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dto;

import java.util.Date;

/**
 *
 * @author Eslam
 */
public class Order {

    private int id;
    private Product product;
    private int quantity;
    private Date date;

    public Order() {
    }

    public Order(int id, Product product, int quantity, Date date) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

    public Order(Product product, int quantity, Date date) {
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
