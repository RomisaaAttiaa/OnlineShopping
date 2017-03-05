/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.dto;

/**
 *
 * @author Eslam
 */
public class Product {

    private int id;
    private float price;
    private int stockQuantity;
    private String name;
    private String description;
    private String category;
    private String url;

    public Product() {
    }

    public Product(float price, int stockQuantity, String name, String description, String category, String url) {
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
        this.category = category;
        this.url = url;
    }

    public Product(int id, float price, int stockQuantity, String name, String description, String category, String url) {
        this.id = id;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
        this.category = category;
        this.url = url;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(float price, int stockQuantity, String name, String description) {
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
    }

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
