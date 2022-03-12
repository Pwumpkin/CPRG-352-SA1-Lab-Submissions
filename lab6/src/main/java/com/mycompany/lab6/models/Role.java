package com.mycompany.lab6.models;

import java.io.Serializable;

/**
 * Represents a Role
 * @author Kevin Bai
 */
public class Role implements Serializable {
    private int id;
    private String name;
    
    public Role(){
        
    }
    
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    
}
