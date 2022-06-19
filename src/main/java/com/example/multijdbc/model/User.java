package com.example.multijdbc.model;

public class User {
    private Long id;
    private String username;
    private String address;
    
    public void setId(Long id) {
    	this.id = id;
	}
    
    public Long getId() {
    	return id;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getAddress() {
    	return address;
	}
    
    public void setAddress(String address) {
    	this.address = address;
    }
}