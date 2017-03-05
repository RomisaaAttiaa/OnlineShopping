package com.jets.onlineshopping.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eslam
 */
@WebServlet(urlPatterns = {"/admin/home"})
public class adminHome extends HttpServlet {

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
        ArrayList<Product> homeProducts;
        if (request.getParameterMap().containsKey("category")) {
            String category = request.getParameter("category").toLowerCase();
            System.out.println(category);
            switch (category) {
                case "electronics":
                    homeProducts = new DBHandler().getProducts("electronics");
                    break;
                case "clothes":
                    homeProducts = new DBHandler().getProducts("clothes");
                    System.out.println("clothes");
                    break;
                case "books":
                    homeProducts = new DBHandler().getProducts("books");
                    break;
                default:
                        homeProducts = new DBHandler().getProducts();            
            }
        } else {
        homeProducts = new DBHandler().getProducts();            
        }

        request.setAttribute("homeProducts", homeProducts);
        request.getRequestDispatcher("products.jsp").forward(request, response);

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
