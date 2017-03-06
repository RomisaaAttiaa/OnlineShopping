/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.CartItem;
import com.jets.onlineshopping.dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ProductDetails", urlPatterns = {"/ProductDetails"})
public class ProductDetails extends HttpServlet {

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
        //get product id from request
        int productId = Integer.parseInt(request.getParameter("pId"));
        //get product details from db 
        Product p =db.getProduct(productId);
        //put it on the response to be used by product_details.jsp
        request.setAttribute("product_details",p);

        //related products  //till now i get all products we need function in db to get certain amount of products or add products on session 
        ArrayList<Product> relatedProductsFromDB = db.getProducts();
        ArrayList<Product> relatedProducts = new ArrayList<>();
        
        int numOfRelatedProducts;
        if(relatedProductsFromDB.size()> 6){
            numOfRelatedProducts = 6;
        }else{
            numOfRelatedProducts = relatedProductsFromDB.size();
        }
        for (int i = 0; i < numOfRelatedProducts; i++) {
            relatedProducts.add(relatedProductsFromDB.get(i));
        }
        request.setAttribute("related_products", relatedProducts);
        //go to jsp page 
        RequestDispatcher rd = request.getRequestDispatcher("product_details.jsp");
        rd.forward(request, response);
        
        
        
        
        
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
