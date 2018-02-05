/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dispenser.svt;

import com.dispenser.ui.ResponseObject;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mega
 */
public class Dispense extends AbstractServlet {

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
        
        ResponseObject r = new ResponseObject();
        
        String snackType = request.getParameter("snack_type");
        
        String price = "";
        if ("chips".equalsIgnoreCase(snackType)){
            price = "6.50";
        }
        if ("coke".equalsIgnoreCase(snackType)){
            price = "11.00";
        }
        if ("nuts".equalsIgnoreCase(snackType)){
            price = "25.50";
        }
        
        r.setValue(price);
        r.setKey("OK");
        
        processRequest(request, response, r);
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
