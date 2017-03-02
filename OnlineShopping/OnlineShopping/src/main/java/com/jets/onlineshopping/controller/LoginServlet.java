/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aya
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        DBHandler db = new DBHandler();
        String userEmail = reqest.getParameter("email");
        User user = db.checkLogin(userEmail, reqest.getParameter("password"));
        if (user != null) {
            HttpSession session = reqest.getSession();
            session.setAttribute("user", user);
            ArrayList<CartItem> cartItems = db.getCartItems(userEmail);
            Iterator<CartItem> iterator = cartItems.iterator();
            while (iterator.hasNext()) {
                CartItem cartItem = iterator.next();
                session.setAttribute("Cart #" + cartItem.getId(), cartItem);
            }
            db.deleteAllCartItems(userEmail);
            reqest.getRequestDispatcher(reqest.getParameter("url")).forward(reqest, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
