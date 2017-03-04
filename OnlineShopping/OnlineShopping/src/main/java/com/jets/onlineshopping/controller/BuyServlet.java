/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.Order;
import com.jets.onlineshopping.dto.Product;
import com.jets.onlineshopping.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@WebServlet(name = "BuyServlet", urlPatterns = {"/BuyServlet"})
public class BuyServlet extends HttpServlet {

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
        DBHandler db = new DBHandler();

        HttpSession session = request.getSession(true);
        ArrayList<CartItem> outOfStockProducts = new ArrayList<>();
        //check if user logged in 
        User user = (User) session.getAttribute("logged");
        if (user != null) {    //user logged in 
            //check if there is products on session
            //ArrayList<CartItem> cartProducts = (ArrayList<CartItem>) session.getAttribute("products");
            HashMap<Integer, CartItem> cartPro = (HashMap<Integer, CartItem>) session.getAttribute("products");
//            if (cartProducts != null) {
//                if (cartProducts.size() > 0) { //there're products in the cart 
//                    //add them to db 
//                    for (CartItem cartProduct : cartProducts) {
//                        Product p = db.getProduct(cartProduct.getProduct().getId());
//                        int stockQuantity = p.getStockQuantity();
//                        if (stockQuantity >= cartProduct.getQuantity()) {  //there's enough quantity in stock
//                            //insert products into database
//                            db.insertOrder(new Order(cartProduct.getProduct(), cartProduct.getQuantity(), new Date()), user.getEmail());
//                            //decrease quantity instock in db 
//                            p.setStockQuantity(stockQuantity - cartProduct.getQuantity());
//                            db.updateProduct(p);
//                        } else {  //there's not enough quantity in stock
//                            cartProduct.setQuantity(p.getStockQuantity());
//                            outOfStockProducts.add(cartProduct);
//                        }
//                    }
//
//                    if (outOfStockProducts.size() == 0) {
//                        //remove products from session 
//                        session.removeAttribute("products");
//                        //response.sendRedirect("HomeServlet");
//                        response.sendRedirect("home.jsp");  //Testing Line
//                    } else {
//                        //update array in session 
//                        session.setAttribute("products", outOfStockProducts);
//                        response.sendRedirect("BuyOutOfStockServlet");
//                    }
//                }
//            }
//        } 
            if (cartPro != null) {
                if (!cartPro.isEmpty()) { //there're products in the cart 
                    //add them to db 
                    for (CartItem cartProduct : cartPro.values()) {
                        Product p = db.getProduct(cartProduct.getProduct().getId());
                        int stockQuantity = p.getStockQuantity();
                        if (stockQuantity >= cartProduct.getQuantity()) {  //there's enough quantity in stock
                            //insert products into database
                            db.insertOrder(new Order(cartProduct.getProduct(), cartProduct.getQuantity(), new Date()), user.getEmail());
                            //decrease quantity instock in db 
                            p.setStockQuantity(stockQuantity - cartProduct.getQuantity());
                            db.updateProduct(p);
                        } else {  //there's not enough quantity in stock
                            cartProduct.setQuantity(p.getStockQuantity());
                            outOfStockProducts.add(cartProduct);
                        }
                    }

                    if (outOfStockProducts.size() == 0) {
                        //remove products from session 
                        session.removeAttribute("products");
                        //response.sendRedirect("HomeServlet");
                        response.sendRedirect("home.jsp");  //Testing Line
                    } else {
                        //update array in session 
                        session.setAttribute("products", outOfStockProducts);
                        response.sendRedirect("BuyOutOfStockServlet");
                    }
                }
            }
        }else {  //user not logged in 
            response.sendRedirect("LoginServlet");
        }
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
