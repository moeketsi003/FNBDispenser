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
public class ResponseObject {
    
    private String key;
    private Object value;
    
    public String getKey(){
        return key;
    }
    
    public void setKey(String key){
        this.key = key;
    }
    
    public Object getValue(){
        return value;
    }
    
    public void setValue(Object value){
        this.value = value;
    }
    
    
}
