/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dispenser.ui;

import java.util.List;

/**
 *
 * @author Mega
 */
public class Change {
    
    private List<Breakdown> changeBreakdown;
    private double totalChange;

    /**
     * @return the changeBreakdown
     */
    public List<Breakdown> getChangeBreakdown() {
        return changeBreakdown;
    }

    /**
     * @param changeBreakdown the changeBreakdown to set
     */
    public void setChangeBreakdown(List<Breakdown> changeBreakdown) {
        this.changeBreakdown = changeBreakdown;
    }

    /**
     * @return the totalChange
     */
    public double getTotalChange() {
        return totalChange;
    }

    /**
     * @param totalChange the totalChange to set
     */
    public void setTotalChange(double totalChange) {
        this.totalChange = totalChange;
    }
    
}
