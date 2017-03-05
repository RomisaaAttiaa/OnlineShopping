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
            float total = Float.parseFloat(request.getParameter("total"));
            if (user.getCreditLimit() < total) {
                request.setAttribute("errorMsg", "No Enough Credit To Buy");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }
            //check if there is products on session
            HashMap<Integer, CartItem> cartPro = (HashMap<Integer, CartItem>) session.getAttribute("products");
            ArrayList<Order> order = new ArrayList<>();
            //this array is used for updating quantity in stock
            ArrayList<Product> stockProducts = new ArrayList<>();
            //error msg
            String errormsg = "";
            if (cartPro != null) {
                if (!cartPro.isEmpty()) { //there're products in the cart 
                    for (CartItem cartProduct : cartPro.values()) {
                        Product p = db.getProduct(cartProduct.getProduct().getId());
                        int stockQuantity = p.getStockQuantity();

                        if (stockQuantity < cartProduct.getQuantity()) {
                            outOfStockProducts.add(cartProduct);
                            errormsg += cartProduct.getProduct().getName() + ",";
                        } else {
                            //updated product stock quantiy
                            p.setStockQuantity(stockQuantity - cartProduct.getQuantity());
                            stockProducts.add(p);
                            order.add(new Order(cartProduct.getProduct(), cartProduct.getQuantity(), new Date()));
                        }
                    }

                    if (outOfStockProducts.size() == 0) {
                        //insert products into database
                        db.insertOrders(order, user.getEmail());
                        //decrease quantity instock in db 
                        db.updateProductsStockQuantity(stockProducts);
                        //decrease user credit limit
                        db.decreaseCreditLimit(user.getEmail(), total);
                        //remove products from session 
                        session.removeAttribute("products");
                        //redirect to home page with success msg
                        request.setAttribute("success", "Order done successfully");
                        request.getRequestDispatcher("HomeServlet").forward(request, response);
                        return;
                    } else {
                        //redirect to cart page again with error msg
                        errormsg += "out of stock";
                        request.setAttribute("errormsg", errormsg);
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } else {  //user not logged in 
            session.setAttribute("errorMsg", "You must be logged in first before buying");
            response.sendRedirect("LoginServlet");
            return;
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
