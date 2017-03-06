/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.onlineshopping.admin.controller;

import com.jets.onlineshopping.dao.DBHandler;
import com.jets.onlineshopping.dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toqae
 */
@WebServlet(name = "EditProductButtonServlet", urlPatterns = {"/EditProductButtonServlet"})
public class EditProductButtonServlet extends HttpServlet {

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
        int pId = Integer.parseInt(request.getParameter("pId"));
        String name = request.getParameter("pName");
        String cat = request.getParameter("pCategory");
        float price = Float.parseFloat(request.getParameter("pPrice"));
        int quantity = Integer.parseInt(request.getParameter("pQuantity"));
        String desc = request.getParameter("pDescription");
        if(desc == null){
            desc="No Description";
        }
        //request.getParameter("pURL");
        
        //create product object with details
        Product pro = new Product(pId, price, quantity, name, desc, cat, " ");
        //Create DB handler object 
        DBHandler db = new DBHandler();
        //update product in db
        if(db.updateProduct(pro)){
            response.sendRedirect("/admin/products.jsp");
        }else{
            request.setAttribute("errormsg", "Product is not updated");
            request.getRequestDispatcher("EditProductServlet?pId="+pId).forward(request, response);
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
