/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dispenser.ui;

/**
 *
 * @author Mega
 */
public class Breakdown {
    
    private String note;
    private int count;
    
    public Breakdown(String note){
        this.note = note;
    }
    
    public String getNote(){
        return note;
    }
    
    public void setNote(String note){
        this.note = note;
    }
    
    public Object getCount(){
        return count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
}
