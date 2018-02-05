/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dispenser.svt;

import com.dispenser.ui.Breakdown;
import com.dispenser.ui.Change;
import com.dispenser.ui.ResponseObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mega
 */
public class Calculate extends AbstractServlet {

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
        
        String moneyinStr = request.getParameter("moneyin");
        String chargeStr = request.getParameter("charge");
        
        double moneyin = 0;
        double charge = 0;
        try{
            moneyin = Double.parseDouble(moneyinStr);
            charge = Double.parseDouble(chargeStr);
        } catch (NumberFormatException ex){
            r.setKey("ERROR");
            r.setValue("Please provide valid notes.");
            processRequest(request, response, r);
            return;
        }
        
        if (moneyin != 0.05 && moneyin != 0.10 && moneyin != 0.5 && moneyin != 1 && 
                moneyin != 2 && moneyin != 5 && moneyin != 10 && moneyin != 20 && 
                moneyin != 50 && moneyin != 100){
            r.setKey("ERROR");
            r.setValue("The rand note value is incorrect.");
            processRequest(request, response, r);
            return;
        }
        
        double change = moneyin - charge;
        
        if (change < 0){
            r.setKey("ERROR");
            r.setValue("Notes are too small for required charge.");
            processRequest(request, response, r);
            return;
        }
        
        List<Breakdown> counts = new ArrayList<>();
        
        Breakdown b1 = new Breakdown("R100");
        b1.setCount((int)(change / 100));
        counts.add(b1);
        double remaining = change % 100;
        
        Breakdown b2 = new Breakdown("R50");
        b2.setCount((int)(remaining / 50));
        counts.add(b2);
        double remaining2 = remaining == 0 ? 0 : remaining % 50;
        
        Breakdown b3 = new Breakdown("R20");
        b3.setCount((int)(remaining2 / 20));
        counts.add(b3);
        double remaining3 = remaining2 == 0 ? 0 : remaining2 % 20;
        
        Breakdown b4 = new Breakdown("R10");
        b4.setCount((int)(remaining3 / 10));
        counts.add(b4);
        double remaining4 = remaining3 == 0 ? 0 : remaining3 % 10;
        
        Breakdown b5 = new Breakdown("R5");
        b5.setCount((int)(remaining4 / 5));
        counts.add(b5);
        double remaining5 = remaining4 == 0 ? 0 : remaining4 % 5;
        
        Breakdown b6 = new Breakdown("R2");
        b6.setCount((int)(remaining5 / 1));
        counts.add(b6);
        double remaining6 = remaining5 == 0 ? 0 : remaining5 % 1;
        
        Breakdown b10 = new Breakdown("R1");
        b10.setCount((int)(remaining6 / 1));
        counts.add(b10);
        double remaining10 = remaining6 == 0 ? 0 : remaining6 % 1;
        
        Breakdown b7 = new Breakdown("50c");
        b7.setCount((int)(remaining10 / 0.5));
        counts.add(b7);
        double remaining7 = remaining10 == 0 ? 0 : remaining10 % 0.5;
        
        Breakdown b8 = new Breakdown("20c");
        b8.setCount((int)(remaining7 / 0.2));
        counts.add(b8);
        double remaining8 = remaining7 == 0 ? 0 : remaining7 % 0.2;
        
        Breakdown b9 = new Breakdown("10c");
        b9.setCount((int)(remaining8 / 0.1));
        counts.add(b9);
        double remaining9 = remaining8 == 0 ? 0 : remaining8 % 0.1;
        
        Breakdown b0 = new Breakdown("5c");
        b0.setCount((int)(remaining9 / 0.05));
        counts.add(b0);
        //double remaining0 = remaining9 == 0 ? 0 : remaining9 % 0.05;
        
        Change totalChange = new Change();
        totalChange.setChangeBreakdown(counts);
        totalChange.setTotalChange(change);
        
        r.setKey("OK");
        r.setValue(totalChange);
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
