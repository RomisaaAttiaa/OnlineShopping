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
import java.util.HashMap;
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
        String refererUri = request.getParameter("refererUri");

        // Check if request comes from LoginServlet url
        if (refererUri != null) {
            String userEmail = request.getParameter("email");
            User user = db.checkLogin(userEmail, request.getParameter("password"));
            System.out.println(user == null);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("logged", user);

                ArrayList<CartItem> cartItems = db.getCartItems(userEmail);
                HashMap<Integer, CartItem> cartItemsOnSession = new HashMap<>();
                for (CartItem cartItem : cartItems) {
                    cartItemsOnSession.put(cartItem.getProduct().getId(), cartItem);
                }
                session.setAttribute("products", cartItemsOnSession);
                db.deleteAllCartItems(userEmail);
                
                if(refererUri.equals("/login.jsp")){
                    response.sendRedirect("HomeServlet");
                }
                else{
                    request.getRequestDispatcher(request.getParameter("refererUri")).forward(request, response);
                }
            } else {
                // Wrong email or password
                response.sendRedirect("HomeServlet");
            }
        } else {
            response.sendRedirect("HomeServlet");
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
