/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.User;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Eslam
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        User logged;
        HashMap<Integer,CartItem> products;
//        ArrayList<CartItem> products;
        DBHandler db = new DBHandler();
        HttpSession session = se.getSession();
        
        if ((products = (HashMap<Integer,CartItem>) session.getAttribute("products")) != null && (logged = (User) session.getAttribute("logged"))!=null) {
            for (CartItem item : products.values()) {
                db.insertCartItem(item, logged.getEmail());
                System.err.println("inserting in DB");
            }
        }
    }
}
