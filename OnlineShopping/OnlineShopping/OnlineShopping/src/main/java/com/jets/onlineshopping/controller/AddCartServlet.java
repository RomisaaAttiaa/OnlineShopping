/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.Product;
import com.jets.onlineshopping.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author toqae
 */
@WebServlet(name = "AddCartServlet", urlPatterns = {"/AddCartServlet"})
public class AddCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        DBHandler db = new DBHandler();
        //get products hashmap from the session
        HashMap<Integer, CartItem> cartPro = (HashMap<Integer, CartItem>) session.getAttribute("products");
        //get product ID from the request
        int pId = Integer.parseInt(request.getParameter("pId"));
        //get product from db
        Product p = db.getProduct(pId);
        //create cart item with details retreived from session 
        CartItem cItem = new CartItem(p, Integer.parseInt(request.getParameter("pQuantity")));
        if (cartPro == null) {   //no such attribute on session
            //intialize HashMap
            cartPro = new HashMap<Integer, CartItem>();
        }
        //add item to array 
        if (!cartPro.containsKey(cItem.getProduct().getId())) { //new product in hashmap 
            cartPro.put(cItem.getProduct().getId(), cItem);
        } else {  //if this product is already in the cart increase only its quantity
            CartItem cartItem = cartPro.get(cItem.getProduct().getId());
            //update its quantity
            cartItem.setQuantity(cartItem.getQuantity() + cItem.getQuantity());
            //replace the old value with the new 
            cartPro.replace(cItem.getProduct().getId(), cartItem);
        }
        db.updateProduct(cItem.getProduct());
        System.out.println(cItem.getQuantity());
        //add array on session 
        session.setAttribute("products", cartPro);
        //To redirect to same page
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
        //response.sendRedirect("home.jsp");  //Testing line
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
